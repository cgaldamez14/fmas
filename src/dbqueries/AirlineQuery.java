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

import model.Airline;
import queries.Airlines;

public class AirlineQuery extends DatabaseQuery{

	public AirlineQuery(){}

	public Airline getAirlineInfo(String n){
		openConnection();
		try {
			String sql;
			if(n.length() >= 3)
				sql = "SELECT * FROM airlines WHERE name = ?;";
			else
				sql = "SELECT * FROM airlines WHERE fs = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setString(1, n);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id = rs.getInt("airlineId");
				String fs = rs.getString("fs");
				String name = rs.getString("name");
				String phone = rs.getString("phoneNumber");
				closeConnection();
				return new Airline(id,fs,name,phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}

	public Airline getAirlineById(int n){
		openConnection();
		try {
			String sql;

			sql = "SELECT * FROM airlines WHERE airlineId = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, n);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id = rs.getInt("airlineId");
				String fs = rs.getString("fs");
				String name = rs.getString("name");
				String phone = rs.getString("phoneNumber");
				closeConnection();
				return new Airline(id,fs,name,phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}

	public static void setAirlineTable() throws ServletException, SAXException, IOException, ParserConfigurationException{
		openConnection();

		try{
			ArrayList<Map<String,String>> list = new Airlines().getAirlineList();

			Statement stmt = c.createStatement();

			String sql = "DROP TABLE IF EXISTS airlines;";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE airlines ("
					+ "airlineId int(11) NOT NULL AUTO_INCREMENT,"
					+ "fs varchar(10) NOT NULL,"
					+ "name varchar(100) NOT NULL,"
					+ "phoneNumber varchar(45) DEFAULT NULL,"
					+ "PRIMARY KEY (`airlineId`),"
					+ "UNIQUE KEY `airlineId_UNIQUE` (`airlineId`),"
					+ "UNIQUE KEY `fs_UNIQUE` (`fs`)"
					+ ") ";
			stmt.executeUpdate(sql);

			for(int i = 0; i < list.size();i++){
				sql = "INSERT INTO airlines (fs, name)" +
						"VALUES (\"" + list.get(i).get("fs") + "\",\"" + list.get(i).get("name") + "\");";
				stmt.executeUpdate(sql);
			}
		}
		catch( SQLException e ){
			throw new ServletException( e );
		}

		closeConnection();
	}
}
