package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabaseJDBC {
	private static String DB_URL = "jdbc:mysql://localhost:3306/quanlysanpham";
	private static String USER_NAME = "root";
	private static String PASSWORD = "Thien@003";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("Connect Succcessfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Connect Faillure!");
			e.printStackTrace();
		}
		
		return connection;
		
	}
	public static void main(String[] args) {
		Connection c = getConnection();
		System.out.println(c);
	}
}
