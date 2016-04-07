package dbqueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.HotelReservation;

public class HotelQuery extends DatabaseQuery {

	public HotelQuery(){}
	
	public HotelReservation getHotelInfo(int ID, String confirmationNumber){
		System.out.println(confirmationNumber);
		openConnection();

		try {
			String sql = "SELECT * FROM hotelplans WHERE userid = ? AND confirmationnumber = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, ID);
			ps.setString(2, confirmationNumber);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				String number = rs.getString("confirmationnumber");
				String name = rs.getString("hotelname");
				String address = rs.getString("hoteladdress");
				String city = rs.getString("hotelcity");
				String state = rs.getString("hotelstate");
				String zip = rs.getString("hotelzip");
				String in = rs.getString("checkin");
				String out = rs.getString("checkout");
				String rooms = rs.getString("numberofrooms");
				
				closeConnection();
				return new HotelReservation(number,name,address,city,state,zip,in,out,rooms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}

}
