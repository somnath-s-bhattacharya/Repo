package com.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * author @ Somnath Bhattacharjee
 */

public class AddContainsAssertions extends JFrame {

	private JPanel contentPane;
	
	public static String responseFieldSET = null;
	
	/**
	 * Launch the application.
	 */
	
	public static void addContainsAssertions() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContainsAssertions frame = new AddContainsAssertions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public AddContainsAssertions() {
		setBackground(new Color(153, 255, 255));
		setTitle("Add Contains Assertions");
		setBounds(425, 275, 451, 264);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField Assertions = new JTextField();
		Assertions.setBounds(30, 51, 396, 107);
		contentPane.add(Assertions);
		Assertions.setColumns(10);
		
		JButton btnApplyHeaders = new JButton("Apply Assertion");
		btnApplyHeaders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				responseFieldSET = Assertions.getText().trim();
				dispose();
			}
		});
		btnApplyHeaders.setBackground(SystemColor.activeCaption);
		btnApplyHeaders.setFont(new Font("Calibri", Font.BOLD, 14));
		btnApplyHeaders.setBounds(153, 182, 151, 23);
		contentPane.add(btnApplyHeaders);
	}
}
