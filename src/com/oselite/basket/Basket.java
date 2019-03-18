package com.oselite.basket;

import java.sql.*;


public class Basket {
	
	private Connection connection;
	private Statement statement;
	private ResultSet result;
	private String query;
	
	public Basket() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basket", "student" , "student");
		statement = connection.createStatement();
	}
	
	public void showAllItems() throws SQLException {
		result = statement.executeQuery("select * from items");
	}

}
