package com.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
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

	private JFrame frame;
	public static JTextField JSONfileLocator;
	public static JTextField URL;
	public static String JSONFilePath =null;
	public static String webURL =null;
	public static int concUsers=0;
	public static int rampUpTime=0;
	public static int webMethodIndex =0;
	private JTextField NumberOfUsers;
	private JTextField UserName;
	public static String username;
	private JPasswordField passwordField;
	public static String pwd;
	private JTextField RampUpTime;
	
	/**
	 * author @ Somnath Bhattacharjee
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerformanceTestExecutor window = new PerformanceTestExecutor();
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
	public PerformanceTestExecutor() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		/*Set Up the Base Frame*/
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 12));
		frame.setBounds(400, 250, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("JSON Parser - Data Generator");
		

		JLabel lblNewLabel = new JLabel("Performance Test Executor");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 25, 388, 66);
		frame.getContentPane().add(lblNewLabel);
		
				
		/*Initialize Input Text Field - JSONFilePath*/
		JLabel lblJsonFilePath = new JLabel("Input File Path");
		lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJsonFilePath.setBounds(46, 145, 87, 14);
		frame.getContentPane().add(lblJsonFilePath);
		
		JSONfileLocator = new JTextField();
		JSONfileLocator.setBounds(143, 142, 334, 20);
		frame.getContentPane().add(JSONfileLocator);
		JSONfileLocator.setColumns(10);
		
		JButton BrowseJSONFile = new JButton("Browse");
		BrowseJSONFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser FC = new FileChooser();
				JSONFilePath = FC.fileChose();
				JSONfileLocator.setText(JSONFilePath);
			}
		});
		BrowseJSONFile.setBounds(488, 142, 81, 20);
		frame.getContentPane().add(BrowseJSONFile);
		
		
		/*Initialize Input Text Field - URL*/
		JLabel lblXPathFilePath = new JLabel("URL");
		lblXPathFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXPathFilePath.setBounds(111, 182, 22, 14);
		frame.getContentPane().add(lblXPathFilePath);
		
		URL = new JTextField();
		URL.setColumns(10);
		URL.setBounds(143, 179, 334, 20);
		frame.getContentPane().add(URL);
		
		/*Get Number Of Users*/
		JLabel lblEditedJsonFilePath = new JLabel("Number Of Users");
		lblEditedJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEditedJsonFilePath.setBounds(46, 220, 94, 14);
		frame.getContentPane().add(lblEditedJsonFilePath);
		
		NumberOfUsers = new JTextField();
		NumberOfUsers.setColumns(10);
		NumberOfUsers.setBounds(143, 217, 164, 20);
		frame.getContentPane().add(NumberOfUsers);
		
		
//		JLabel label_logo = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/Cognizant_Logo.png")).getImage();
//		label_logo.setIcon(new ImageIcon(img));
//		label_logo.setBounds(454, 0, 128, 131);
//		frame.getContentPane().add(label_logo);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(76, 263, 58, 14);
		frame.getContentPane().add(lblUsername);
		
		UserName = new JTextField();
		UserName.setColumns(10);
		UserName.setBounds(143, 260, 164, 20);
		frame.getContentPane().add(UserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(331, 263, 61, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(393, 260, 176, 20);
		frame.getContentPane().add(passwordField);
		
		JComboBox webMethodSelector = new JComboBox();
		webMethodSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				webMethodIndex = webMethodSelector.getSelectedIndex();
			}
		});
		
		webMethodSelector.setFont(new Font("Tahoma", Font.BOLD, 12));
		webMethodSelector.setModel(new DefaultComboBoxModel(new String[] {"GET", "POST", "PUT", "DELETE"}));
		webMethodSelector.setSelectedIndex(0);
		webMethodSelector.setBounds(143, 102, 58, 20);
		frame.getContentPane().add(webMethodSelector);
		
		JLabel lblMethodType = new JLabel("Method Type");
		lblMethodType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMethodType.setBounds(52, 106, 81, 14);
		frame.getContentPane().add(lblMethodType);
		
		JLabel lblRampUpTime = new JLabel("Ramp Up");
		lblRampUpTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRampUpTime.setBounds(331, 220, 58, 14);
		frame.getContentPane().add(lblRampUpTime);
		
		RampUpTime = new JTextField();
		RampUpTime.setColumns(10);
		RampUpTime.setBounds(393, 217, 176, 20);
		frame.getContentPane().add(RampUpTime);
				
		
		JButton PerfTestExecutor = new JButton("Execute");
		PerfTestExecutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebMethodSelector WMS = new WebMethodSelector();
				POST_ThreadHandler PTTH = new POST_ThreadHandler();
				try {
					webURL=URL.getText();
					concUsers=Integer.parseInt(NumberOfUsers.getText().trim());
					rampUpTime = Integer.parseInt(RampUpTime.getText().trim());
					username = UserName.getText();
					pwd = passwordField.getText();
					WMS.webMethodSelector(webMethodIndex,rampUpTime);
					JOptionPane.showMessageDialog(frame, "Execution Log Generated");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		PerfTestExecutor.setBounds(255, 328, 129, 23);
		frame.getContentPane().add(PerfTestExecutor);
		
		
		
	
	}
}
