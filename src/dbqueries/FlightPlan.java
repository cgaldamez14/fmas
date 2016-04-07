package dbqueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Airport;
import model.Flight;
import model.HotelReservation;
import model.User;

public class FlightPlan extends DatabaseQuery{

	private Flight flight;
	private HotelReservation hotel;
	private User user;

	public FlightPlan(){}
	
	public FlightPlan(User user, Flight flight, HotelReservation hotel){
		this.user = user;
		this.flight = flight;
		this.hotel = hotel;
	}

	public void saveFlightPlan(){
		openConnection();

		try {
			Statement stmt = c.createStatement();

			String sql = "INSERT INTO flightplans (airlineid, departureairportid, arrivalairportid, hotelplanid, flightnumber, departuredate, arrivaldate, departureterminal, arrivalterminal, userid, date)" +
					"VALUES (\"" + flight.getAirline().getId() + "\",\"" + flight.getAirports().get(0).getId() + "\","
					+ "\"" + flight.getAirports().get(1).getId() + "\",\"" + getHotelID() + "\",\"" + flight.getFlightNumber() + "\","
					+ "\"" + flight.getoGDDate() + "\",\"" + flight.getoGADate() + "\","
					+ "\"" + flight.getDepartureTerminal() + "\",\"" + flight.getArrivalTerminal() + "\",\"" 
					+ user.getId()  + "\",\"" + flight.getYear()+"-"+flight.getMonth()+"-"+flight.getDay()+ "\");";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}

	public void saveHotelPlan(){
		openConnection();

		try {
			Statement stmt = c.createStatement();

			String sql = "INSERT INTO hotelplans (confirmationnumber, hotelname, hoteladdress, hotelcity, hotelstate, hotelzip, checkin, checkout, numberofrooms)" +
					"VALUES (\"" + hotel.getConfirmationNumber() + "\",\"" + hotel.getHotelName() + "\","
					+ "\"" + hotel.getHotelAddress() + "\",\"" + hotel.getHotelCity() + "\","
					+ "\"" + hotel.getHotelState() + "\",\"" + hotel.getHotelZip() + "\","
					+ "\"" + hotel.getCheckInDate() + "\",\"" + hotel.getCheckOutDate() + "\","
					+ "\"" + hotel.getNumberOfRooms() + "\");";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}

	private int getHotelID(){
		openConnection();
		int id = -1;		
		try {
			String sql  = "SELECT idhotelplans FROM hotelplans ORDER BY idhotelplans DESC LIMIT 1;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				id = rs.getInt(1);
			}
			
			System.out.println(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return id;
	}
	

	public HotelReservation getHotelByID(int i){
		openConnection();	
		try {
			String sql  = "SELECT * FROM hotelplans WHERE idhotelplans = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				String num = rs.getString("confirmationnumber");
				String name = rs.getString("hotelname");
				String address = rs.getString("hoteladdress");
				String city = rs.getString("hotelcity");
				String state = rs.getString("hotelstate");
				String zip = rs.getString("hotelzip");
				String checkin = rs.getString("checkin");
				String checkout = rs.getString("checkout");
				String rooms = rs.getString("numberofrooms");
				closeConnection();
				return new HotelReservation(num,name,address,city,state,zip,checkin,checkout,rooms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}

		
	public static void deleteFlightPlan(int id, int userId){
		openConnection();

		try {
			Statement stmt = c.createStatement();

			String sql  = "SELECT hotelplanid FROM flightplans WHERE userid = '" + userId + "' AND  id = '"+ id +"';";

			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			int hotelid = 0;
			while(rs.next()){
				hotelid = rs.getInt("hotelplanid");
			}
			
			
			sql = "DELETE FROM flightplans WHERE userid = '" + userId + "' AND id = '" + id + "';";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM hotelplans WHERE userid = '" + userId + "' AND idhotelplans = '" + hotelid + "';";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}

	
	public ArrayList<Flight> getFlightPlans(int id){
		openConnection();
		// Implement code for when hotel id column is null
		ArrayList<Flight> plan = new ArrayList<>();
		Flight f = null;
		try {
			String sql  = "SELECT * FROM flightplans WHERE userid = '" + id + "' AND date >= '"+ new java.sql.Date(new java.util.Date().getTime()).toString()+"' Order BY date ASC;";

			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int planId = rs.getInt("id");
				int airlineID = rs.getInt("airlineid");
				int departureAirportID = rs.getInt("departureairportid");
				int arrivalAirportID = rs.getInt("arrivalairportid");
				String flightNumber = rs.getString("flightnumber");
				String departureDate = rs.getString("departuredate");
				String arrivalDate = rs.getString("arrivaldate");
				
				f = new Flight();
				f.setAirline(new AirlineQuery().getAirlineById(airlineID));
				ArrayList<Airport> airports = new ArrayList<>();
				airports.add(new AirportQuery().getAirportById(departureAirportID));
				airports.add(new AirportQuery().getAirportById(arrivalAirportID));
				f.setAirports(airports);
				f.setFlightNumber(flightNumber);
				f.setoGDDate(departureDate);
				f.setoGADate(arrivalDate);
				f.setId(planId);
				plan.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return plan;

	}
	
	public ArrayList<Flight> getOldFlightPlans(int id){
		openConnection();
		// Implement code for when hotel id column is null
		ArrayList<Flight> plan = new ArrayList<>();
		Flight f = null;
		try {
			String sql  = "SELECT * FROM flightplans WHERE userid = '" + id + "' AND date < '"+ new java.sql.Date(new java.util.Date().getTime()).toString()+"' Order BY date DESC;";

			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int planId = rs.getInt("id");
				int airlineID = rs.getInt("airlineid");
				int departureAirportID = rs.getInt("departureairportid");
				int arrivalAirportID = rs.getInt("arrivalairportid");
				String flightNumber = rs.getString("flightnumber");
				String departureDate = rs.getString("departuredate");
				String arrivalDate = rs.getString("arrivaldate");
				
				f = new Flight();
				f.setAirline(new AirlineQuery().getAirlineById(airlineID));
				ArrayList<Airport> airports = new ArrayList<>();
				airports.add(new AirportQuery().getAirportById(departureAirportID));
				airports.add(new AirportQuery().getAirportById(arrivalAirportID));
				f.setAirports(airports);
				f.setFlightNumber(flightNumber);
				f.setoGDDate(departureDate);
				f.setoGADate(arrivalDate);
				f.setId(planId);
				plan.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return plan;

	}
	
	public ArrayList<Flight> getFlightPlanById(int id){
		openConnection();
		// Implement code for when hotel id column is null
		ArrayList<Flight> plan = new ArrayList<>();
		Flight f = null;
		try {
			String sql  = "SELECT * FROM flightplans WHERE id = '" + id + "';";

			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int planId = rs.getInt("id");
				int airlineID = rs.getInt("airlineid");
				int departureAirportID = rs.getInt("departureairportid");
				int arrivalAirportID = rs.getInt("arrivalairportid");
				String flightNumber = rs.getString("flightnumber");
				String departureDate = rs.getString("departuredate");
				String arrivalDate = rs.getString("arrivaldate");
				
				f = new Flight();
				f.setAirline(new AirlineQuery().getAirlineById(airlineID));
				ArrayList<Airport> airports = new ArrayList<>();
				airports.add(new AirportQuery().getAirportById(departureAirportID));
				airports.add(new AirportQuery().getAirportById(arrivalAirportID));
				f.setAirports(airports);
				f.setFlightNumber(flightNumber);
				f.setoGDDate(departureDate);
				f.setoGADate(arrivalDate);
				f.setId(planId);
				plan.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return plan;

	}
	
	public Map<String,Object> getFlightPlanByID(int id){
		openConnection();
		// Implement code for when hotel id column is null
		Map<String,Object> plan = new HashMap<String,Object>();
		Flight f = null;
		HotelReservation hotel = null;
		try {
			String sql  = "SELECT * FROM flightplans WHERE id = '" + id + "';";

			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int planId = rs.getInt("id");
				int airlineID = rs.getInt("airlineid");
				int departureAirportID = rs.getInt("departureairportid");
				int arrivalAirportID = rs.getInt("arrivalairportid");
				int hotelPlanID = rs.getInt("hotelplanid");
				String flightNumber = rs.getString("flightnumber");
				String departureDate = rs.getString("departuredate");
				String arrivalDate = rs.getString("arrivaldate");
				String departureTerminal = rs.getString("departureterminal");
				String arrivalTerminal = rs.getString("arrivalterminal");
				
				f = new Flight();
				f.setAirline(new AirlineQuery().getAirlineById(airlineID));
				ArrayList<Airport> airports = new ArrayList<>();
				airports.add(new AirportQuery().getAirportById(departureAirportID));
				airports.add(new AirportQuery().getAirportById(arrivalAirportID));
				f.setAirports(airports);
				f.setFlightNumber(flightNumber);
				f.setoGDDate(departureDate);
				f.setoGADate(arrivalDate);
				f.setDepartureTerminal(departureTerminal);
				f.setArrivalTerminal(arrivalTerminal);
				f.setId(planId);
				hotel = getHotelByID(hotelPlanID);
				plan.put("flight", f);
				plan.put("hotel", hotel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return plan;

	}
}
