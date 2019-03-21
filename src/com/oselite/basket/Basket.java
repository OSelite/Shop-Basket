package com.oselite.basket;

import java.sql.*;

import javax.swing.JLabel;
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
		model.addColumn("name");
		model.addColumn("price");
		model.addColumn("amount");
	}
	
	public void showAllItems() throws SQLException {
		clearTable();
		this.result = statement.executeQuery("select * from items");
	}
	
	public void deleteAllItems() throws SQLException {
		int delete = statement.executeUpdate("DELETE FROM items");
		
	}
	
	public void addItem(String name, String price, int amount) throws SQLException {
		String query = "insert into items (name, price, amount) values ('"+ name + "', " + price + "," + amount + ")";
		int add = statement.executeUpdate(query);
	}
	
	public void deleteItem(String cell) throws SQLException {
		String query = "DELETE FROM items WHERE name='" + cell + "'";
		int delete = statement.executeUpdate(query);
	}
	
	public void sumOfPrice(JLabel lblPrice) throws SQLException {
		double sum = 0;
		
		result = statement.executeQuery("select * from items");
				
		while(result.next())
		{
		    sum += result.getDouble("price") * result.getInt("amount");
		}
		
		String txt = Double.toString(sum);
		lblPrice.setText(txt);
	}
	
	public void sumOfItems(JLabel lblAmount) throws SQLException {
		int sum = 0;
		
		result = statement.executeQuery("select * from items");
				
		while(result.next())
		{
		    sum += result.getInt("amount");
		}
		
		String txt = Integer.toString(sum);
		lblAmount.setText(txt);	
	}
	
	/** TABLE METHODS */
	public DefaultTableModel createModel() throws SQLException {
		while(result.next())
		{
		    String name = result.getString("name");
		    String price = result.getString("price");
		    int amount = result.getInt("amount");
		    model.addRow(new Object[]{name, price, amount});
		}
		return model;
	}
	
	public void clearTable() {
		model.setRowCount(0);
	}

}
