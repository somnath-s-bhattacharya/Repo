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

public class AddHeaders extends JFrame {

	private JPanel contentPane;
	
	public static String HN1="test";
	public static String HV1="test";
	public static String HN2="test";
	public static String HV2="test";
	public static String HN3="test";
	public static String HV3="test";
	public static String HN4="test";
	public static String HV4="test";
	public static String HN5="test";
	public static String HV5="test";
	public static String HN6="test";
	public static String HV6="test";
	public static String HN7="test";
	public static String HV7="test";
	public static String HN8="test";
	public static String HV8="test";
	public static String HN9="test";
	public static String HV9="test";
	public static String HN10="test";
	public static String HV10="test";

	/**
	 * Launch the application.
	 */
	
	public static void addHeaders() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHeaders frame = new AddHeaders();
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
	
	public AddHeaders() {
		setBackground(new Color(153, 255, 255));
		setTitle("Add Headers");
		setBounds(425, 275, 550, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeaderName = new JLabel("Header Name");
		lblHeaderName.setFont(new Font("Calibri", Font.BOLD, 14));
		lblHeaderName.setBounds(87, 26, 100, 14);
		contentPane.add(lblHeaderName);
		
		JLabel lblHeaderValue = new JLabel("Header Value");
		lblHeaderValue.setFont(new Font("Calibri", Font.BOLD, 14));
		lblHeaderValue.setBounds(347, 26, 100, 14);
		contentPane.add(lblHeaderValue);
		
		JTextField HeaderName1 = new JTextField();
		HeaderName1.setBounds(30, 51, 214, 20);
		contentPane.add(HeaderName1);
		HeaderName1.setColumns(10);
		
		JTextField HeaderValue1 = new JTextField();
		HeaderValue1.setColumns(10);
		HeaderValue1.setBounds(290, 51, 214, 20);
		contentPane.add(HeaderValue1);
		
		JTextField HeaderName2 = new JTextField();
		HeaderName2.setColumns(10);
		HeaderName2.setBounds(30, 82, 214, 20);
		contentPane.add(HeaderName2);
		
		JTextField HeaderValue2 = new JTextField();
		HeaderValue2.setColumns(10);
		HeaderValue2.setBounds(290, 82, 216, 20);
		contentPane.add(HeaderValue2);
		
		JTextField HeaderName3 = new JTextField();
		HeaderName3.setColumns(10);
		HeaderName3.setBounds(30, 113, 214, 20);
		contentPane.add(HeaderName3);
		
		JTextField HeaderValue3 = new JTextField();
		HeaderValue3.setColumns(10);
		HeaderValue3.setBounds(290, 113, 216, 20);
		contentPane.add(HeaderValue3);
		
		JTextField HeaderName4 = new JTextField();
		HeaderName4.setColumns(10);
		HeaderName4.setBounds(30, 144, 214, 20);
		contentPane.add(HeaderName4);
		
		JTextField HeaderValue4 = new JTextField();
		HeaderValue4.setColumns(10);
		HeaderValue4.setBounds(290, 144, 216, 20);
		contentPane.add(HeaderValue4);
		
		JTextField HeaderName5 = new JTextField();
		HeaderName5.setColumns(10);
		HeaderName5.setBounds(30, 175, 214, 20);
		contentPane.add(HeaderName5);
		
		JTextField HeaderValue5 = new JTextField();
		HeaderValue5.setColumns(10);
		HeaderValue5.setBounds(290, 175, 216, 20);
		contentPane.add(HeaderValue5);
		
		JTextField HeaderName6 = new JTextField();
		HeaderName6.setColumns(10);
		HeaderName6.setBounds(30, 206, 214, 20);
		contentPane.add(HeaderName6);
		
		JTextField HeaderValue6 = new JTextField();
		HeaderValue6.setColumns(10);
		HeaderValue6.setBounds(290, 206, 216, 20);
		contentPane.add(HeaderValue6);
		
		JTextField HeaderName7 = new JTextField();
		HeaderName7.setColumns(10);
		HeaderName7.setBounds(30, 237, 214, 20);
		contentPane.add(HeaderName7);
		
		JTextField HeaderValue7 = new JTextField();
		HeaderValue7.setColumns(10);
		HeaderValue7.setBounds(290, 237, 216, 20);
		contentPane.add(HeaderValue7);
		
		JTextField HeaderName8 = new JTextField();
		HeaderName8.setColumns(10);
		HeaderName8.setBounds(30, 268, 214, 20);
		contentPane.add(HeaderName8);
		
		JTextField HeaderValue8 = new JTextField();
		HeaderValue8.setColumns(10);
		HeaderValue8.setBounds(290, 268, 216, 20);
		contentPane.add(HeaderValue8);
		
		JTextField HeaderName10 = new JTextField();
		HeaderName10.setColumns(10);
		HeaderName10.setBounds(30, 332, 214, 20);
		contentPane.add(HeaderName10);
		
		JTextField HeaderName9 = new JTextField();
		HeaderName9.setColumns(10);
		HeaderName9.setBounds(30, 301, 214, 20);
		contentPane.add(HeaderName9);
		
		JTextField HeaderValue9 = new JTextField();
		HeaderValue9.setColumns(10);
		HeaderValue9.setBounds(290, 301, 216, 20);
		contentPane.add(HeaderValue9);
		
		JTextField HeaderValue10 = new JTextField();
		HeaderValue10.setColumns(10);
		HeaderValue10.setBounds(290, 332, 216, 20);
		contentPane.add(HeaderValue10);
		
		JButton btnApplyHeaders = new JButton("Apply Headers");
		btnApplyHeaders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HN1 = HeaderName1.getText().trim();
				HN2 = HeaderName2.getText().trim();
				HN3 = HeaderName3.getText().trim();
				HN4 = HeaderName4.getText().trim();
				HN5 = HeaderName5.getText().trim();
				HN6 = HeaderName6.getText().trim();
				HN7 = HeaderName7.getText().trim();
				HN8 = HeaderName8.getText().trim();
				HN9 = HeaderName9.getText().trim();
				HN10 = HeaderName10.getText().trim();
				
				HV1 = HeaderValue1.getText().trim();
				HV2 = HeaderValue2.getText().trim();
				HV3 = HeaderValue3.getText().trim();
				HV4 = HeaderValue4.getText().trim();
				HV5 = HeaderValue5.getText().trim();
				HV6 = HeaderValue6.getText().trim();
				HV7 = HeaderValue7.getText().trim();
				HV8 = HeaderValue8.getText().trim();
				HV9 = HeaderValue9.getText().trim();
				HV10 = HeaderValue10.getText().trim();
				
				dispose();
				
			}
		});
		btnApplyHeaders.setBackground(SystemColor.activeCaption);
		btnApplyHeaders.setFont(new Font("Calibri", Font.BOLD, 14));
		btnApplyHeaders.setBounds(203, 378, 128, 23);
		contentPane.add(btnApplyHeaders);
	}
}
