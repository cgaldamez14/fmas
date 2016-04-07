package dbqueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserQuery extends DatabaseQuery{

	public UserQuery(){}

	public User verifyUser(String username, String password){
		openConnection();

		try {
			String sql = "SELECT * FROM users WHERE email = ? AND password = ?;";

			PreparedStatement ps;

			ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				
				closeConnection();
				return new User(id,name,email,phone,address,city,state,zip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return null;
	}
	
	public void registerUser(User newUser){
		openConnection();

		try {
			Statement stmt = c.createStatement();

			String sql = "INSERT INTO users (name, phone, email, password, address, city, state, zip)" +
					"VALUES (\"" + newUser.getName() + "\",\"" + newUser.getPhoneNumber() + "\","
							+ "\"" + newUser.getEmail() + "\",\"" + newUser.getPassword() + "\","
							+ "\"" + newUser.getAddress() + "\",\"" + newUser.getCity() + "\","
							+ "\"" + newUser.getState() + "\",\"" + newUser.getZip() + "\");";
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
	}
}
