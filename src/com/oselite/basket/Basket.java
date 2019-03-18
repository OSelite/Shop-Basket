package com.oselite.basket;

import java.sql.*;

import javax.swing.table.DefaultTableModel;


public class Basket {
	
	private Connection connection;
	private Statement statement;
	private ResultSet result;
	private DefaultTableModel model;

	
	public Basket() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basket", "student" , "student");
		statement = connection.createStatement();
		model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("price");
	}
	
	public DefaultTableModel createModel() throws SQLException {
		while(result.next())
		{
		    String id = result.getString("id");
		    String name = result.getString("name");
		    String price = result.getString("price");
		    model.addRow(new Object[]{id, name, price});
		}
		return model;
	}
	
	public void clearTable() {
		model.setRowCount(0);
	}
	
	public void showAllItems() throws SQLException {
		clearTable();
		result = statement.executeQuery("select * from items");
	}
	
	public void addItem(String name, String price) throws SQLException {
		String query = "insert into items (name, price) values ('"+ name + "', " + price + ")";
		int add = statement.executeUpdate(query);
	}

}
