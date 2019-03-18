package com.oselite.main;

import java.awt.EventQueue;
import com.oselite.basket.*;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Application {

	private JFrame frame;
	private JTable table;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JButton btnDelete;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Application() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(6, 6, 218, 229);
		frame.getContentPane().add(table);
		
		Basket myBasket = new Basket();
		
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					myBasket.showAllItems();
					table.setModel(myBasket.createModel());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(52, 243, 117, 29);
		frame.getContentPane().add(btnLoad);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(314, 24, 130, 26);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(314, 63, 130, 26);
		frame.getContentPane().add(priceTextField);
		priceTextField.setColumns(10);
		
		JButton btnAddButton = new JButton("ADD");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				
				try {
					myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basket", "student" , "student");
					
					System.out.println("Database connection successful!\n");
					
					// 2. Create a statement
					myStatement = myConnection.createStatement();
					
					String name = textField.getText();
					String price = textField_1.getText();
					String query = "insert into items (name, price) values ('"+ name + "', " + price + ")";
										
					int added = myStatement.executeUpdate(query);
					
					model.setRowCount(0);
					
					myResult = myStatement.executeQuery("select * from items");
					
					while(myResult.next())
					{
					    String id = myResult.getString("id");
					    String n = myResult.getString("name");
					    String p = myResult.getString("price");
					    model.addRow(new Object[]{id, n, p});
					}
					
					table.setModel(model);
					
				*/
			}
		});
		btnAddButton.setBounds(327, 115, 117, 29);
		frame.getContentPane().add(btnAddButton);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(241, 29, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(241, 68, 61, 16);
		frame.getContentPane().add(lblPrice);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
					myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basket", "student" , "student");
					
					System.out.println("Database connection successful!\n");
					
					// 2. Create a statement
					myStatement = myConnection.createStatement();
					
										
					int row = table.getSelectedRow();
					String cell = table.getModel().getValueAt(row, 0).toString();
					
					String query = "delete from items where id=" + cell;

					
					int deleted = myStatement.executeUpdate(query);
					
					model.setRowCount(0);
					
					myResult = myStatement.executeQuery("select * from items");
					
					while(myResult.next())
					{
					    String id = myResult.getString("id");
					    String n = myResult.getString("name");
					    String p = myResult.getString("price");
					    model.addRow(new Object[]{id, n, p});
					}
					
					table.setModel(model);
					
				*/
				
			}
		});
		btnDelete.setBounds(327, 143, 117, 29);
		frame.getContentPane().add(btnDelete);
	}
}
