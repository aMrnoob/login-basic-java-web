package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

public class UserDAO {
	
	public void addUser(String fullName, String email, String numberPhone, String address, String accountName, String password) throws SQLException {
		String sql = "INSERT INTO [dbo].[user] (userID, fullName, email, phone, address, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String userID = "User" + (countUsers() + 1);

        try (Connection connection = connectDB.getConnection();
        		
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userID); 
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setString(4, numberPhone);
            statement.setString(5, address);
            statement.setString(6, accountName);
            statement.setString(7, password);

            statement.executeUpdate();
        } catch (SQLException e) {      	
			 e.printStackTrace(); throw new SQLException("Error! Cant add user"); 	 
        }
    }
	
	public void updateUser(String email, String password) throws SQLException
	{
		 String sql = "UPDATE [dbo].[user] SET password = ? WHERE email = ?";

		    try (Connection connection = connectDB.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {

		        statement.setString(1, password); 
		        statement.setString(2, email);  

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Cập nhật mật khẩu thành công.");
		        } else {
		            throw new SQLException("Không tìm thấy người dùng với email: " + email);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new SQLException("Error! Unable to update user password.");
		    }
	}
	
	public int countUsers() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM [dbo].[user]";

	    try (Connection connection = connectDB.getConnection();	 
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            count = resultSet.getInt(1); 
	        }
	    } catch (SQLException e) {
			 e.printStackTrace(); 
	    }
	    return count;
	}
	
	public boolean checkDuplicate(String column, String value) {
	    String query = "SELECT COUNT(*) FROM [dbo].[user] WHERE " + column + " = ?";
	    try (Connection conn = connectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, value);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public User getInforUser(String username)
	{
		String sql = "SELECT userID, fullName, email, phone, address, username, password FROM [dbo].[user] WHERE username = ?";
	    User user = null;

	    try (Connection connection = connectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, username);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String userID = resultSet.getString("userID");
	            String fullName = resultSet.getString("fullName");
	            String email = resultSet.getString("email");
	            String phone = resultSet.getString("phone");
	            String address = resultSet.getString("address");
	            String userName = resultSet.getString("username");
	            String password = resultSet.getString("password");

	            user = new User(userID, userName, password, fullName, email, phone, address);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
}
