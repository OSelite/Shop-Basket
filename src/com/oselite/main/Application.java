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
	private JTextField amountTextField;
	

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
		btnLoad.setBounds(0, 243, 108, 29);
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
				
				String name = nameTextField.getText();
				String price = priceTextField.getText();
				int amount = Integer.parseInt(amountTextField.getText());
	
				try {
					myBasket.addItem(name, price, amount);
					myBasket.showAllItems();
					table.setModel(myBasket.createModel());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnAddButton.setBounds(327, 137, 117, 29);
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
					int row = table.getSelectedRow();
					String cell = table.getModel().getValueAt(row, 0).toString();
					myBasket.deleteItem(cell);
					myBasket.showAllItems();
					table.setModel(myBasket.createModel());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
			}
		});
		btnDelete.setBounds(327, 168, 117, 29);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton = new JButton("CLEAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					myBasket.deleteAllItems();
					myBasket.showAllItems();
					table.setModel(myBasket.createModel());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(116, 243, 108, 29);
		frame.getContentPane().add(btnNewButton);
		
		amountTextField = new JTextField();
		amountTextField.setBounds(314, 99, 130, 26);
		frame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(241, 104, 61, 16);
		frame.getContentPane().add(lblAmount);
	}
}
