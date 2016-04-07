package dbqueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Photobook;

public class PhotobookQuery extends DatabaseQuery{


	public PhotobookQuery(){}

	public ArrayList<Photobook> getPhotobooks(int uid){
		ArrayList<Photobook> photobooks =  new ArrayList<>();
		openConnection();
		try {
			String sql = "SELECT * FROM photobooks WHERE userid = ? AND flightplanid IN"
					+ " (SELECT id FROM flightplans WHERE date < '" 
					+ new java.sql.Date(new java.util.Date().getTime()).toString() 
					+  "' Order BY date DESC) Order BY flightplanid DESC;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String lat = rs.getString("latitude");
				String lon = rs.getString("longitude");
				String url = rs.getString("url");

				photobooks.add(new Photobook(id,name,description,lat,lon,url));
			}

			closeConnection();
			return photobooks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}
	
	public ArrayList<Photobook> getAllPhotobooks(int uid){
		ArrayList<Photobook> photobooks =  new ArrayList<>();
		openConnection();
		try {
			String sql = "SELECT * FROM photobooks WHERE userid = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String lat = rs.getString("latitude");
				String lon = rs.getString("longitude");
				String url = rs.getString("url");

				photobooks.add(new Photobook(id,name,description,lat,lon,url));
			}

			closeConnection();
			return photobooks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}
}
