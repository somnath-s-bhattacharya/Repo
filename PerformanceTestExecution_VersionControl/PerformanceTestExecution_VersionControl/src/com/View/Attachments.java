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

public class Attachments extends JFrame {

	private JPanel contentPane;
	
	public static String filename1=null;
	public static String filename2=null;
	public static String filename3=null;
	public static String filename4=null;
	public static String filename5=null;
	public static String filename6=null;
	public static String filename7=null;
	public static String filename8=null;
	public static String filename9=null;
	public static String filename10=null;
	public static String filename11=null;
	public static String filename12=null;
	public static String filename13=null;
	public static String filename14=null;
	public static String filename15=null;
	public static String filename16=null;
	public static String filename17=null;
	public static String filename18=null;
	public static String filename19=null;
	public static String filename20=null;
	
	private JTextField file11;
	private JTextField file12;
	private JTextField file13;
	private JTextField file14;
	private JTextField file15;
	private JTextField file16;
	private JTextField file17;
	private JTextField file18;
	private JTextField file19;
	private JTextField file20;

	/**
	 * Launch the application.
	 */
	
	public static void Attachments() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attachments frame = new Attachments();
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
	
	public Attachments() {
		setBackground(new Color(153, 255, 255));
		setTitle("Add Attachments");
		setBounds(425, 275, 550, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField file1 = new JTextField();
		file1.setBounds(30, 51, 360, 20);
		contentPane.add(file1);
		file1.setColumns(10);
		
		JTextField file2 = new JTextField();
		file2.setColumns(10);
		file2.setBounds(30, 82, 360, 20);
		contentPane.add(file2);
		
		JTextField file3 = new JTextField();
		file3.setColumns(10);
		file3.setBounds(30, 113, 360, 20);
		contentPane.add(file3);
		
		JTextField file4 = new JTextField();
		file4.setColumns(10);
		file4.setBounds(30, 144, 360, 20);
		contentPane.add(file4);
		
		JTextField file5 = new JTextField();
		file5.setColumns(10);
		file5.setBounds(30, 175, 360, 20);
		contentPane.add(file5);
		
		JTextField file6 = new JTextField();
		file6.setColumns(10);
		file6.setBounds(30, 206, 360, 20);
		contentPane.add(file6);
		
		JTextField file7 = new JTextField();
		file7.setColumns(10);
		file7.setBounds(30, 237, 360, 20);
		contentPane.add(file7);
		
		JTextField file8 = new JTextField();
		file8.setColumns(10);
		file8.setBounds(30, 268, 360, 20);
		contentPane.add(file8);
		
		JTextField file10 = new JTextField();
		file10.setColumns(10);
		file10.setBounds(30, 332, 360, 20);
		contentPane.add(file10);
		
		JTextField file9 = new JTextField();
		file9.setColumns(10);
		file9.setBounds(30, 301, 360, 20);
		contentPane.add(file9);
		
		file11 = new JTextField();
		file11.setColumns(10);
		file11.setBounds(30, 363, 360, 20);
		contentPane.add(file11);
		
		file12 = new JTextField();
		file12.setColumns(10);
		file12.setBounds(30, 394, 360, 20);
		contentPane.add(file12);
		
		file13 = new JTextField();
		file13.setColumns(10);
		file13.setBounds(30, 425, 360, 20);
		contentPane.add(file13);
		
		file14 = new JTextField();
		file14.setColumns(10);
		file14.setBounds(30, 456, 360, 20);
		contentPane.add(file14);
		
		file15 = new JTextField();
		file15.setColumns(10);
		file15.setBounds(30, 487, 360, 20);
		contentPane.add(file15);
		
		file16 = new JTextField();
		file16.setColumns(10);
		file16.setBounds(30, 518, 360, 20);
		contentPane.add(file16);
		
		file17 = new JTextField();
		file17.setColumns(10);
		file17.setBounds(30, 549, 360, 20);
		contentPane.add(file17);
		
		file18 = new JTextField();
		file18.setColumns(10);
		file18.setBounds(30, 580, 360, 20);
		contentPane.add(file18);
		
		file19 = new JTextField();
		file19.setColumns(10);
		file19.setBounds(30, 611, 360, 20);
		contentPane.add(file19);
		
		file20 = new JTextField();
		file20.setColumns(10);
		file20.setBounds(30, 642, 360, 20);
		contentPane.add(file20);
		
		JButton btnApplyHeaders = new JButton("Attach");
		btnApplyHeaders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filename1 = file1.getText().trim();
				filename2 = file2.getText().trim();
				filename3 = file3.getText().trim();
				filename4 = file4.getText().trim();
				filename5 = file5.getText().trim();
				filename6 = file6.getText().trim();
				filename7 = file7.getText().trim();
				filename8 = file8.getText().trim();
				filename9 = file9.getText().trim();
				filename10 = file10.getText().trim();
				filename11 = file11.getText().trim();
				filename12 = file12.getText().trim();
				filename13 = file13.getText().trim();
				filename14 = file14.getText().trim();
				filename15 = file15.getText().trim();
				filename16 = file16.getText().trim();
				filename17 = file17.getText().trim();
				filename18 = file18.getText().trim();
				filename19 = file19.getText().trim();
				filename20 = file20.getText().trim();
				
				dispose();
				
			}
		});
		btnApplyHeaders.setBackground(SystemColor.activeCaption);
		btnApplyHeaders.setFont(new Font("Calibri", Font.BOLD, 14));
		btnApplyHeaders.setBounds(206, 693, 128, 23);
		contentPane.add(btnApplyHeaders);
		
		JButton Browse1 = new JButton("Browse");
		Browse1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename1 = FC.fileChose();
				file1.setText(filename1);
			}
		});
		Browse1.setBounds(402, 52, 117, 20);
		contentPane.add(Browse1);
		
		JButton Browse2 = new JButton("Browse");
		Browse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename2 = FC.fileChose();
				file2.setText(filename2);
			}
		});
		Browse2.setBounds(402, 83, 117, 20);
		contentPane.add(Browse2);
		
		JButton Browse3 = new JButton("Browse");
		Browse3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename3 = FC.fileChose();
				file3.setText(filename3);
			}
		});
		Browse3.setBounds(402, 115, 117, 20);
		contentPane.add(Browse3);
		
		JButton Browse4 = new JButton("Browse");
		Browse4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename4 = FC.fileChose();
				file4.setText(filename4);
			}
		});
		Browse4.setBounds(402, 145, 117, 20);
		contentPane.add(Browse4);
		
		JButton Browse5 = new JButton("Browse");
		Browse5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename5 = FC.fileChose();
				file5.setText(filename5);
			}
		});
		Browse5.setBounds(402, 175, 117, 20);
		contentPane.add(Browse5);
		
		JButton Browse6 = new JButton("Browse");
		Browse6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename6 = FC.fileChose();
				file6.setText(filename6);
			}
		});
		Browse6.setBounds(402, 206, 117, 20);
		contentPane.add(Browse6);
		
		JButton Browse7 = new JButton("Browse");
		Browse7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename7 = FC.fileChose();
				file7.setText(filename7);
				
			}
		});
		Browse7.setBounds(402, 237, 117, 20);
		contentPane.add(Browse7);
		
		JButton Browse8 = new JButton("Browse");
		Browse8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename8 = FC.fileChose();
				file8.setText(filename8);
			}
		});
		Browse8.setBounds(402, 268, 117, 20);
		contentPane.add(Browse8);
		
		JButton Browse9 = new JButton("Browse");
		Browse9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename9 = FC.fileChose();
				file9.setText(filename9);
			}
		});
		Browse9.setBounds(402, 301, 117, 20);
		contentPane.add(Browse9);
		
		JButton Browse10 = new JButton("Browse");
		Browse10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename10 = FC.fileChose();
				file10.setText(filename10);
			}
		});
		Browse10.setBounds(402, 332, 117, 20);
		contentPane.add(Browse10);
		
		JButton Browse11 = new JButton("Browse");
		Browse11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename11 = FC.fileChose();
				file11.setText(filename11);
			}
		});
		Browse11.setBounds(402, 363, 117, 20);
		contentPane.add(Browse11);
		
		JButton Browse12 = new JButton("Browse");
		Browse12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename12 = FC.fileChose();
				file12.setText(filename12);
			}
		});
		Browse12.setBounds(402, 394, 117, 20);
		contentPane.add(Browse12);
		
		JButton Browse13 = new JButton("Browse");
		Browse13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename13 = FC.fileChose();
				file12.setText(filename13);
			}
		});
		Browse13.setBounds(402, 426, 117, 20);
		contentPane.add(Browse13);
		
		JButton Browse14 = new JButton("Browse");
		Browse14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename14 = FC.fileChose();
				file14.setText(filename14);
			}
		});
		Browse14.setBounds(402, 456, 117, 20);
		contentPane.add(Browse14);
		
		JButton Browse15 = new JButton("Browse");
		Browse15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename15 = FC.fileChose();
				file15.setText(filename15);
			}
		});
		Browse15.setBounds(402, 487, 117, 20);
		contentPane.add(Browse15);
		
		JButton Browse16 = new JButton("Browse");
		Browse16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename16 = FC.fileChose();
				file16.setText(filename16);
			}
		});
		Browse16.setBounds(402, 519, 117, 20);
		contentPane.add(Browse16);
		
		JButton Browse17 = new JButton("Browse");
		Browse17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename17 = FC.fileChose();
				file17.setText(filename17);
			}
		});
		Browse17.setBounds(402, 551, 117, 20);
		contentPane.add(Browse17);
		
		JButton Browse18 = new JButton("Browse");
		Browse18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename18 = FC.fileChose();
				file18.setText(filename18);
			}
		});
		Browse18.setBounds(402, 583, 117, 20);
		contentPane.add(Browse18);
		
		JButton Browse19 = new JButton("Browse");
		Browse19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename19 = FC.fileChose();
				file19.setText(filename19);
			}
		});
		Browse19.setBounds(402, 611, 117, 20);
		contentPane.add(Browse19);
		
		JButton Browse20 = new JButton("Browse");
		Browse20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				filename20 = FC.fileChose();
				file20.setText(filename20);
			}
		});
		Browse20.setBounds(402, 642, 117, 20);
		contentPane.add(Browse20);
	}
}
