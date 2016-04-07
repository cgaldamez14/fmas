package dbqueries;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Airport;
import queries.Airports;

public class AirportQuery extends DatabaseQuery{

	public AirportQuery(){}

	public Airport getAirportInfo(String f){
		openConnection();
		try {
			String sql = "SELECT * FROM airports WHERE fs = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setString(1, f);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id = rs.getInt("airportId");
				String fs = rs.getString("fs");
				String name = rs.getString("name");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String country = rs.getString("country");
				String lat = rs.getString("latitude");
				String lon = rs.getString("longitude");
				String weather = rs.getString("weatherURL");
				closeConnection();
				return new Airport(id,fs,name,city,state,country,lat,lon,weather);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;

	}
	
	public Airport getAirportById(int i){
		openConnection();
		try {
			String sql = "SELECT * FROM airports WHERE airportId = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id = rs.getInt("airportId");
				String fs = rs.getString("fs");
				String name = rs.getString("name");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String country = rs.getString("country");
				String lat = rs.getString("latitude");
				String lon = rs.getString("longitude");
				String weather = rs.getString("weatherURL");
				closeConnection();
				return new Airport(id,fs,name,city,state,country,lat,lon,weather);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;

	}

	public static void setAirportTable() throws ServletException, SAXException, IOException, ParserConfigurationException{
		openConnection();

		try{
			ArrayList<Map<String,String>> list = new Airports().getAirportList();

			Statement stmt = c.createStatement();

			String sql = "DROP TABLE IF EXISTS airports;";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE airports ("
					+ "airportId int(11) NOT NULL AUTO_INCREMENT,"
					+ "fs varchar(10) NOT NULL,"
					+ "name varchar(100) NOT NULL,"
					+ "city varchar(45) DEFAULT NULL,"
					+ "state varchar(45) DEFAULT NULL,"
					+ "country varchar(45) DEFAULT NULL,"
					+ "latitude varchar(45) NOT NULL,"
					+ "longitude varchar(45) NOT NULL,"
					+ "weatherURL varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`airportId`),"
					+ "UNIQUE KEY `airportId_UNIQUE` (`airportId`),"
					+ "UNIQUE KEY `fs_UNIQUE` (`fs`)"
					+ ")";
			stmt.executeUpdate(sql);

			for(int i = 0; i < list.size();i++){
				sql = "INSERT INTO airports (fs, name, city, state, country, latitude, longitude, weatherURL)" 
						+"VALUES (\"" + list.get(i).get("fs") + "\",\"" + list.get(i).get("name") + "\""
						+ ",\"" + list.get(i).get("city") + "\",\"" + list.get(i).get("stateCode") + "\""
						+ ",\"" + list.get(i).get("countryName") + "\",\"" + list.get(i).get("latitude") + "\""
						+ ",\"" + list.get(i).get("longitude") + "\",\"" + list.get(i).get("weatherUrl") + "\");";
				stmt.executeUpdate(sql);
			}
		}
		catch( SQLException e ){
			throw new ServletException( e );
		}

		closeConnection();
	}
}