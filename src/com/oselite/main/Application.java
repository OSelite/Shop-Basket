package com.oselite.main;

import java.awt.EventQueue;
import com.oselite.basket.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.JOptionPane;

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 453, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(6, 32, 233, 182);
		frame.getContentPane().add(table);
		
		JLabel lblTotalPrice = new JLabel("0");
		lblTotalPrice.setBounds(79, 215, 61, 16);
		frame.getContentPane().add(lblTotalPrice);
		
		JLabel lblTotalAmount = new JLabel("0");
		lblTotalAmount.setBounds(152, 215, 61, 16);
		frame.getContentPane().add(lblTotalAmount);
		
		Basket myBasket = new Basket();
		
		myBasket.showAllItems();
		table.setModel(myBasket.createModel());
		myBasket.sumOfPrice(lblTotalPrice);
		myBasket.sumOfItems(lblTotalAmount);
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds(327, 24, 117, 26);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(327, 63, 117, 26);
		frame.getContentPane().add(priceTextField);
		priceTextField.setColumns(10);
		
		JButton btnAddButton = new JButton("ADD");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = nameTextField.getText();
				String price = priceTextField.getText();
				String amount = amountTextField.getText();

				try {
					if(Pattern.matches("\\d", amount) && Pattern.matches("\\d*\\.\\d{2}", price)) {
						myBasket.addItem(name, price, amount);
						myBasket.showAllItems();
						table.setModel(myBasket.createModel());
						myBasket.sumOfPrice(lblTotalPrice);
						myBasket.sumOfItems(lblTotalAmount);
						nameTextField.setText("");
						priceTextField.setText("");
						amountTextField.setText("");
					} else
						JOptionPane.showMessageDialog(null, "Data is incorrect!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "You have already this item!");
				}	
			}
		});
		btnAddButton.setBounds(327, 137, 117, 29);
		frame.getContentPane().add(btnAddButton);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(251, 29, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(251, 68, 61, 16);
		frame.getContentPane().add(lblPrice);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				try {
					
					if(row != -1) {
						String cell = table.getModel().getValueAt(row, 0).toString();
						myBasket.deleteItem(cell);
						myBasket.showAllItems();
						table.setModel(myBasket.createModel());
						myBasket.sumOfPrice(lblTotalPrice);
						myBasket.sumOfItems(lblTotalAmount);
					} else
						JOptionPane.showMessageDialog(null, "Choose item first!");
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
					myBasket.sumOfPrice(lblTotalPrice);
					myBasket.sumOfItems(lblTotalAmount);
					JOptionPane.showMessageDialog(null, "List is clear!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(6, 243, 108, 29);
		frame.getContentPane().add(btnNewButton);
		
		amountTextField = new JTextField();
		amountTextField.setBounds(327, 99, 117, 26);
		frame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(251, 104, 61, 16);
		frame.getContentPane().add(lblAmount);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 16, 61, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblPrice2 = new JLabel("Price");
		lblPrice2.setBounds(79, 16, 61, 16);
		frame.getContentPane().add(lblPrice2);
		
		JLabel lblAmountResult = new JLabel("Amount");
		lblAmountResult.setBounds(152, 16, 61, 16);
		frame.getContentPane().add(lblAmountResult);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(6, 215, 61, 16);
		frame.getContentPane().add(lblTotal);
		
		
	}
}
