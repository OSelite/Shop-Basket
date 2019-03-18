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
	private JTextField textField;
	private JTextField textField_1;
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
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("price");
		
		table = new JTable(model);
		table.setBounds(6, 6, 218, 229);
		frame.getContentPane().add(table);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				model.setRowCount(0);
				try {
					myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basket", "student" , "student");
					
					System.out.println("Database connection successful!\n");
					
					// 2. Create a statement
					myStatement = myConnection.createStatement();
					
					// 3. Execute SQL query
					
					myResult = myStatement.executeQuery("select * from items");
					
					while(myResult.next())
					{
					    String id = myResult.getString("id");
					    String name = myResult.getString("name");
					    String price = myResult.getString("price");
					    model.addRow(new Object[]{id, name, price});
					}
					
					table.setModel(model);
					
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
			}
		});
		btnLoad.setBounds(52, 243, 117, 29);
		frame.getContentPane().add(btnLoad);
		
		textField = new JTextField();
		textField.setBounds(314, 24, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(314, 63, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddButton = new JButton("ADD");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					
				}catch (Exception exc) {
					exc.printStackTrace();
				}
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
				
				try {
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
					
				}catch (Exception exc) {
					exc.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(327, 143, 117, 29);
		frame.getContentPane().add(btnDelete);
	}
}
