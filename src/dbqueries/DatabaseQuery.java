package dbqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseQuery {

	protected static Connection c = null;
	
	public DatabaseQuery(){}
	
	public static Connection openConnection(){
		try{
			String url = "jdbc:mysql://localhost/fmas?useSSL=false";
			String username = "root";
			String password = "password";

			c = DriverManager.getConnection( url, username, password );
			return c;
		}catch( SQLException e ){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void closeConnection(){
		try{
			if( c != null ) c.close();
		}
		catch( SQLException e ){
			e.printStackTrace();;
		}
	}
}