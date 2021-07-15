package Conenctivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	
	public Connection getConnection() {
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		System.out.println("Main Connection Established");
		return con;
		
	}

}
