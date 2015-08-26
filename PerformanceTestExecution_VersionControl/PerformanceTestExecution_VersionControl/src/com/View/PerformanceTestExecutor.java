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
import com.DataUploader.DataUpload;
import com.PerformanceTest_PostMethod.POST_ThreadHandler;
import com.ThreadSelection.WebMethodSelector;
import com.logging.CouchLogger;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PerformanceTestExecutor {

	private JFrame frmPerformanceTestExecutor;
	public static String JSONFilePath =null;
	public static String webURL =null;
	public static int concUsers=0;
	public static int rampUpTime=0;
	public static int webMethodIndex =0;
	public static String username;
	public static String pwd;
	public static String OutPutDirPath;
	public static long totTime =0;
	public static int totRequests=0;
	public static int totSuccessResponse=0;
	public static int totErrorResponse=0;
	public static long avgTime;
	public static int timeLimit;
	public static int testDelay;
	public static long loopduration = 0; 
	public static long endTimeloop = 0;
	public static long startTimeloop =0;
	public static boolean timeup = false;
	
	public static JTextField JSONfileLocator;
	public static JButton BrowseJSONFile;
	public static JTextField URL;
	private JTextField NumberOfUsers;
	private JTextField UserName;
	private JPasswordField passwordField;
	private JTextField RampUpTime;
	public static JTextField HeaderName;
	public static JTextField HeaderValue;
	private static JTextField zipCode;
	private JTextField OutputDirectoryLoc;
	private JTextField testdelay;
	private JTextField totaltime;
	
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
		frmPerformanceTestExecutor.setBounds(400, 250, 650, 450);
		frmPerformanceTestExecutor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerformanceTestExecutor.getContentPane().setLayout(null);
		frmPerformanceTestExecutor.setTitle("Performance Test Executor");
		

		JLabel lblNewLabel = new JLabel("Performance Test Executor");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 21));
		lblNewLabel.setBounds(15, 10, 388, 66);
		frmPerformanceTestExecutor.getContentPane().add(lblNewLabel);
		
				
		/*Initialize Input Text Field - JSONFilePath*/
		JLabel lblJsonFilePath = new JLabel("Input File Path");
		lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJsonFilePath.setBounds(55, 126, 85, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblJsonFilePath);
		
		JSONfileLocator = new JTextField();
		JSONfileLocator.setBounds(143, 123, 400, 20);
		frmPerformanceTestExecutor.getContentPane().add(JSONfileLocator);
		JSONfileLocator.setColumns(10);
		
		BrowseJSONFile = new JButton("Browse");
		BrowseJSONFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser FC = new FileChooser();
				JSONFilePath = FC.fileChose();
				JSONfileLocator.setText(JSONFilePath);
			}
		});
		BrowseJSONFile.setBounds(555, 123, 81, 20);
		frmPerformanceTestExecutor.getContentPane().add(BrowseJSONFile);
		
		
		/*Initialize Input Text Field - URL*/
		JLabel lblXPathFilePath = new JLabel("URL");
		lblXPathFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXPathFilePath.setBounds(118, 203, 22, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblXPathFilePath);
		
		URL = new JTextField();
		URL.setColumns(10);
		URL.setBounds(143, 199, 493, 20);
		frmPerformanceTestExecutor.getContentPane().add(URL);
		
		/*Get Number Of Users*/
		JLabel lblEditedJsonFilePath = new JLabel("Number Of Users");
		lblEditedJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEditedJsonFilePath.setBounds(46, 244, 94, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblEditedJsonFilePath);
		
		NumberOfUsers = new JTextField();
		NumberOfUsers.setColumns(10);
		NumberOfUsers.setBounds(143, 240, 195, 20);
		frmPerformanceTestExecutor.getContentPane().add(NumberOfUsers);
		
		
		JLabel label_logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/osi_standard_logo.png")).getImage();
		label_logo.setIcon(new ImageIcon(img));
		label_logo.setBounds(562, 0, 87, 108);
		frmPerformanceTestExecutor.getContentPane().add(label_logo);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(82, 285, 58, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblUsername);
		
		UserName = new JTextField();
		UserName.setColumns(10);
		UserName.setBounds(143, 281, 195, 20);
		frmPerformanceTestExecutor.getContentPane().add(UserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(377, 285, 55, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(437, 281, 199, 20);
		frmPerformanceTestExecutor.getContentPane().add(passwordField);
		
		JComboBox webMethodSelector = new JComboBox();
		webMethodSelector.setFont(new Font("Tahoma", Font.BOLD, 12));
		webMethodSelector.setModel(new DefaultComboBoxModel(new String[] {"Select a Method","GET", "POST", "PUT", "DELETE", "WEBSITE", "MULTIPART/FORM-DATA(POST)"}));
		webMethodSelector.setSelectedIndex(0);
		webMethodSelector.setBounds(144, 86, 156, 20);
		frmPerformanceTestExecutor.getContentPane().add(webMethodSelector);
		
		JLabel lblMethodType = new JLabel("Method Type");
		lblMethodType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMethodType.setBounds(52, 90, 81, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblMethodType);
		
		JLabel lblRampUpTime = new JLabel("Ramp Up(ms)");
		lblRampUpTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRampUpTime.setBounds(356, 244, 81, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblRampUpTime);
		
		RampUpTime = new JTextField();
		RampUpTime.setColumns(10);
		RampUpTime.setBounds(437, 240, 199, 20);
		frmPerformanceTestExecutor.getContentPane().add(RampUpTime);
				
		JButton addHeaders = new JButton("Add Headers");
		addHeaders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddHeaders frmAddHeaders = new AddHeaders();
				frmAddHeaders.setVisible(true);
				
			}
		});
		addHeaders.setBounds(385, 390, 130, 29);
		frmPerformanceTestExecutor.getContentPane().add(addHeaders);
		
		JLabel lblZipCode = new JLabel("ZIP CODE");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblZipCode.setBounds(356, 88, 55, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblZipCode);
		
		zipCode = new JTextField();
		zipCode.setColumns(10);
		zipCode.setBounds(413, 85, 130, 20);
		frmPerformanceTestExecutor.getContentPane().add(zipCode);
		
		/*Output Directory Choose*/
		JLabel lblOutputDirectory = new JLabel("Output Directory");
		lblOutputDirectory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOutputDirectory.setBounds(46, 165, 94, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblOutputDirectory);
		
		OutputDirectoryLoc = new JTextField();
		OutputDirectoryLoc.setColumns(10);
		OutputDirectoryLoc.setBounds(143, 160, 400, 20);
		frmPerformanceTestExecutor.getContentPane().add(OutputDirectoryLoc);
		
		JButton BrowseExecLogDirectory = new JButton("Browse");
		BrowseExecLogDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooser FC = new FileChooser();
				OutPutDirPath= FC.OutputDirChose();
				System.out.println("OutPutDirPath: " +OutPutDirPath);
				OutputDirectoryLoc.setText(OutPutDirPath);
			}
		});
		
		BrowseExecLogDirectory.setBounds(555, 160, 81, 20);
		frmPerformanceTestExecutor.getContentPane().add(BrowseExecLogDirectory);
		
		testdelay = new JTextField();
		testdelay.setColumns(10);
		testdelay.setBounds(143, 322, 195, 20);
		frmPerformanceTestExecutor.getContentPane().add(testdelay);
		
		totaltime = new JTextField();
		totaltime.setColumns(10);
		totaltime.setBounds(437, 322, 199, 20);
		frmPerformanceTestExecutor.getContentPane().add(totaltime);
		
		JLabel lblTestDelay = new JLabel("Test Delay(ms)");
		lblTestDelay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestDelay.setBounds(55, 326, 85, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblTestDelay);
		
		JLabel lblTotalTime = new JLabel("Total Time(min)");
		lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalTime.setBounds(346, 326, 91, 14);
		frmPerformanceTestExecutor.getContentPane().add(lblTotalTime);
		
		JButton addContainsAssertion = new JButton("Add Contains Assertions");
		addContainsAssertion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AddContainsAssertions frmAddAssertions = new AddContainsAssertions();
			frmAddAssertions.setVisible(true);
			}
		});
		addContainsAssertion.setBounds(188, 390, 185, 29);
		frmPerformanceTestExecutor.getContentPane().add(addContainsAssertion);
		
		
		JButton addValueAssertion = new JButton("Add Value Assertions");
		addValueAssertion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddValueAssertions frmAddValueAssertions = new AddValueAssertions();
				frmAddValueAssertions.setVisible(true);
			}
		});
		addValueAssertion.setBounds(6, 390, 185, 29);
		frmPerformanceTestExecutor.getContentPane().add(addValueAssertion);
		
		
		JButton PerfTestExecutor = new JButton("Execute");
		PerfTestExecutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebMethodSelector WMS = new WebMethodSelector();
				POST_ThreadHandler PTTH = new POST_ThreadHandler();
				try {
					webMethodIndex = webMethodSelector.getSelectedIndex();
					webURL=URL.getText();
					concUsers=Integer.parseInt(NumberOfUsers.getText().trim());
					rampUpTime = Integer.parseInt(RampUpTime.getText().trim());
					username = UserName.getText();
					pwd = passwordField.getText();
					testDelay = Integer.parseInt(testdelay.getText().trim());
					timeLimit = Integer.parseInt(totaltime.getText().trim());
					WMS.webMethodSelector(webMethodIndex,rampUpTime);
					
					
					Thread.sleep((timeLimit+1)*60*1000);
					
					DataUpload DU = new DataUpload();
				    DU.successLogLoader();
				    DU.errorLogLoader();
				    
				    totRequests = totSuccessResponse + totErrorResponse;
					CouchLogger.testData("URL", webURL, "TestData");
					CouchLogger.testData("Ramp Up Time(ms)", rampUpTime, "TestData");
				    CouchLogger.testData("Total Time of Execution(min)", timeLimit, "TestData");
				    CouchLogger.testData("Total requests", totRequests, "TestData");
					CouchLogger.testData("Total Successful Response", totSuccessResponse, "TestData");
				    CouchLogger.testData("Total Error Response", totErrorResponse, "TestData");
				    avgTime = (totTime)/(totRequests);
				    CouchLogger.testData("Mean Time", avgTime, "TestData");
				    JOptionPane.showMessageDialog(frmPerformanceTestExecutor, "Execution Log Generated and Uploaded");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		PerfTestExecutor.setBounds(514, 390, 130, 29);
		frmPerformanceTestExecutor.getContentPane().add(PerfTestExecutor);
		
		JButton btnMultipartDataRequest = new JButton("MultiPart Request Form");
		btnMultipartDataRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiPartData_RequestForm frmMultiPartData_RequestForm = new MultiPartData_RequestForm();
				frmMultiPartData_RequestForm.setVisible(true);
			}
		});
		btnMultipartDataRequest.setBounds(6, 354, 185, 29);
		frmPerformanceTestExecutor.getContentPane().add(btnMultipartDataRequest);
		
		
		
		
	}
}
