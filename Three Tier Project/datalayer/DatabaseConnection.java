package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private static Connection conn;
	
	public static Connection getConnection() throws SQLException {
		
		if (conn == null || conn.isClosed()) {
			conn = establishConnection();
		}
		
		return conn;
		
	}
	
	private static Connection establishConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/", "root", "password");
			Statement statement = conn.createStatement();
			statement.executeUpdate("create database if not exists fighters;");
			statement.executeUpdate("use fighters;");
			statement.executeUpdate("create table if not exists fighter("
					+ "name varChar(50) primary key, " + "nickname varChar(50), "
					+ "powerRating integer, " + "healthRating integer, "
					+ "mobilityRating integer, " + "techniquesRating integer, "
					+ "rangeRating integer);");
			statement.close();
			
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
		return null;
		
	}
	
}
