package com.View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import com.PerformanceTest_PostMethod.POST_ThreadHandler;
import com.ThreadSelection.WebMethodSelector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PerformanceTestExecutor {

	private JFrame frmPerformanceTestExecutor;
	public static JTextField JSONfileLocator;
	public static JTextField URL;
	public static String JSONFilePath =null;
	public static String webURL =null;
	public static int concUsers=0;
	public static int rampUpTime=0;
	public static int webMethodIndex =0;
	public static String username;
	public static String pwd;
	public static int testDelaytime;
	public static int totalExecutionTime;
	
	private JTextField NumberOfUsers;
	private JTextField UserName;
	private JPasswordField passwordField;
	private JTextField RampUpTime;
	private JTextField TestDelayTime;
	private JTextField totalExecTime;
	
	/**
	 * author @ Somnath Bhattacharjee
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerformanceTestExecutor window = new PerformanceTestExecutor();
					window.frmPerformanceTestExecutor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PerformanceTestExecutor() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		/*Set Up the Base Frame*/
		frmPerformanceTestExecutor = new JFrame();
		frmPerformanceTestExecutor.getContentPane().setBackground(new Color(204, 204, 255));
		frmPerformanceTestExecutor.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 12));
		frmPerformanceTestExecutor.setBounds(400, 250, 600, 400);
		frmPerformanceTestExecutor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerformanceTestExecutor.getContentPane().setLayout(null);
		frmPerformanceTestExecutor.setTitle("Performance Test Executor");
		

		JLabel lblNewLabel = new JLabel("Performance Test Executor");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 25, 388, 66);
		frmPerformanceTestExecutor.getContentPane().add(lblNewLabel);
		
				
		/*Initialize Input Text Field - JSONFilePath*/
		JLabel lblJsonFilePath = new JLabel("Input File Path");
		lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJsonFilePath.setBounds(46, 126, 87, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblJsonFilePath);
		
		JSONfileLocator = new JTextField();
		JSONfileLocator.setBounds(143, 123, 334, 20);
		frmPerformanceTestExecutor.getContentPane().add(JSONfileLocator);
		JSONfileLocator.setColumns(10);
		
		JButton BrowseJSONFile = new JButton("Browse");
		BrowseJSONFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser FC = new FileChooser();
				JSONFilePath = FC.fileChose();
				JSONfileLocator.setText(JSONFilePath);
			}
		});
		BrowseJSONFile.setBounds(488, 123, 81, 20);
		frmPerformanceTestExecutor.getContentPane().add(BrowseJSONFile);
		
		
		/*Initialize Input Text Field - URL*/
		JLabel lblXPathFilePath = new JLabel("URL");
		lblXPathFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXPathFilePath.setBounds(111, 167, 22, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblXPathFilePath);
		
		URL = new JTextField();
		URL.setColumns(10);
		URL.setBounds(143, 164, 426, 20);
		frmPerformanceTestExecutor.getContentPane().add(URL);
		
		/*Get Number Of Users*/
		JLabel lblEditedJsonFilePath = new JLabel("Number Of Users");
		lblEditedJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEditedJsonFilePath.setBounds(39, 208, 94, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblEditedJsonFilePath);
		
		NumberOfUsers = new JTextField();
		NumberOfUsers.setColumns(10);
		NumberOfUsers.setBounds(143, 205, 164, 20);
		frmPerformanceTestExecutor.getContentPane().add(NumberOfUsers);
		
		
		JLabel label_logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Cognizant_Logo.png")).getImage();
		label_logo.setIcon(new ImageIcon(img));
		label_logo.setBounds(454, 0, 128, 131);
		frmPerformanceTestExecutor.getContentPane().add(label_logo);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(75, 289, 58, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblUsername);
		
		UserName = new JTextField();
		UserName.setColumns(10);
		UserName.setBounds(143, 286, 164, 20);
		frmPerformanceTestExecutor.getContentPane().add(UserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(331, 289, 61, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(393, 286, 176, 20);
		frmPerformanceTestExecutor.getContentPane().add(passwordField);
		
		JComboBox webMethodSelector = new JComboBox();
		webMethodSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				webMethodIndex = webMethodSelector.getSelectedIndex();
			}
		});
		
		webMethodSelector.setFont(new Font("Tahoma", Font.BOLD, 12));
		webMethodSelector.setModel(new DefaultComboBoxModel(new String[] {"GET", "POST", "PUT", "DELETE"}));
		webMethodSelector.setSelectedIndex(0);
		webMethodSelector.setBounds(144, 86, 58, 20);
		frmPerformanceTestExecutor.getContentPane().add(webMethodSelector);
		
		JLabel lblMethodType = new JLabel("Method Type");
		lblMethodType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMethodType.setBounds(52, 90, 81, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblMethodType);
		
		JLabel lblRampUpTime = new JLabel("Ramp Up");
		lblRampUpTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRampUpTime.setBounds(331, 208, 58, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblRampUpTime);
		
		RampUpTime = new JTextField();
		RampUpTime.setColumns(10);
		RampUpTime.setBounds(393, 205, 176, 20);
		frmPerformanceTestExecutor.getContentPane().add(RampUpTime);
		
		JLabel lblTestDelay = new JLabel("Test Delay");
		lblTestDelay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestDelay.setBounds(73, 247, 60, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblTestDelay);
		
		TestDelayTime = new JTextField();
		TestDelayTime.setColumns(10);
		TestDelayTime.setBounds(143, 244, 164, 20);
		frmPerformanceTestExecutor.getContentPane().add(TestDelayTime);
		
		JLabel lblTotalTime = new JLabel("Total Time");
		lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalTime.setBounds(323, 247, 60, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblTotalTime);
		
		totalExecTime = new JTextField();
		totalExecTime.setColumns(10);
		totalExecTime.setBounds(393, 244, 176, 20);
		frmPerformanceTestExecutor.getContentPane().add(totalExecTime);
				
		
		JButton PerfTestExecutor = new JButton("Execute");
		PerfTestExecutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebMethodSelector WMS = new WebMethodSelector();
				POST_ThreadHandler PTTH = new POST_ThreadHandler();
				try {
					webURL=URL.getText();
					concUsers=Integer.parseInt(NumberOfUsers.getText().trim());
					rampUpTime = Integer.parseInt(RampUpTime.getText().trim());
					testDelaytime = Integer.parseInt(TestDelayTime.getText().trim());
					totalExecutionTime = Integer.parseInt(totalExecTime.getText().trim());
					username = UserName.getText();
					pwd = passwordField.getText();
					WMS.webMethodSelector(webMethodIndex,rampUpTime);
					JOptionPane.showMessageDialog(frmPerformanceTestExecutor, "Execution Log Generated");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		PerfTestExecutor.setBounds(255, 328, 129, 23);
		frmPerformanceTestExecutor.getContentPane().add(PerfTestExecutor);
		
	}
}
