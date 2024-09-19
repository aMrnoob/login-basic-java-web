package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {

	private static final String URL = "jdbc:sqlserver://DESKTOP-0Q6FFSV:1433;databaseName=UserDB;encrypt=false;trustServerCertificate=true;";

	public static Connection getConnection() throws SQLException {
		
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(URL, "root", "12345");
			System.out.println("connect successfully!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("SQL Server Driver not found");
		} catch (SQLException e) {
			System.out.println("Connection failed to database: " + URL);
			System.out.println("Error message: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return con;
	}
}
