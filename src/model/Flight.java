package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Flight {

	private int id;
	private ArrayList<Airport> airports;
	private Airline airline;
	private Calendar departure = null;
	private Calendar arrival = null;
	private String oGDDate;
	private String oGADate;
	private Status status;
	private String flightNumber;
	private String departureTerminal = "not found";
	private String arrivalTerminal = "not found";
	private String day;
	
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private String month;
	private String year;

	public Flight(){}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(String departureTerminal) {
		if(departureTerminal != null)
			this.departureTerminal = departureTerminal;
	}

	public String getArrivalTerminal() {
		return arrivalTerminal;
	}

	public void setArrivalTerminal(String arrivalTerminal) {
		if(arrivalTerminal != null)
			this.arrivalTerminal = arrivalTerminal;
	}

	public String getDepartureTime() {
		return new SimpleDateFormat("hh:mm a").format(departure.getTime());
	}

	public String getArrivalTime() {
		return new SimpleDateFormat("hh:mm a").format(arrival.getTime());
	}

	public String getDepartureDate(){
		return new SimpleDateFormat("EEEE, MMMMM dd, yyyy").format(departure.getTime());
	}

	public String getArrivalDate(){
		return new SimpleDateFormat("EEEE, MMMMM dd, yyyy").format(arrival.getTime());
	}

	public GregorianCalendar setDate(String t) {
		if(!t.equals(null)){

			String[] dateTime = t.split("T");
			String[] date = dateTime[0].split("-");
			String[] time = dateTime[1].split(":");
			this.year= date[0];
			this.month= date[1];
			this.day= date[2];
			return new GregorianCalendar(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),00);

		}
		return null;
	}

	public Calendar getDeparture() {
		return departure;
	}

	public Calendar getArrival() {
		return arrival;
	}

	public void setAirports(ArrayList<Airport> airports) {
		this.airports = airports;
	}

	public ArrayList<Airport> getAirports(){
		return airports;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getoGDDate() {
		return oGDDate;
	}

	public void setoGDDate(String oGDDate) {
		this.oGDDate = oGDDate;
		departure = setDate(this.oGDDate);
	}

	public String getoGADate() {
		return oGADate;
	}

	public void setoGADate(String oGADate) {
		this.oGADate = oGADate;
		arrival = setDate(this.oGADate);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
