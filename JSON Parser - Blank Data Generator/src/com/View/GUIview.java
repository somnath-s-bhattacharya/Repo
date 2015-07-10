package com.View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.BlankTag.JSON_BlankTagGenerator;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.SystemColor;

public class GUIview {

	private JFrame frame;
	private JTextField JSONfileLocator;
	private JTextField Xpath_MaxLFileLocator;
	static String JSONFilePath =null;
	static String XPathFilePath =null;
	static String OutPutDirPath =null;
	private JTextField OutputDirectoryLoc;
	
	/**
	 * author @ Somnath Bhattacharjee
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIview window = new GUIview();
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
	public GUIview() {
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
		
		/*Initialize Input Text Field - JSONFilePath*/
		JSONfileLocator = new JTextField();
		JSONfileLocator.setBounds(143, 152, 334, 20);
		frame.getContentPane().add(JSONfileLocator);
		JSONfileLocator.setColumns(10);
		
		/*Initialize Input Text Field - XPath_MaxLFilePath*/
		Xpath_MaxLFileLocator = new JTextField();
		Xpath_MaxLFileLocator.setColumns(10);
		Xpath_MaxLFileLocator.setBounds(143, 196, 334, 20);
		frame.getContentPane().add(Xpath_MaxLFileLocator);
		
		
		JButton BrowseJSONFile = new JButton("Browse");
		BrowseJSONFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser FC = new FileChooser();
				JSONFilePath = FC.fileChose();
				System.out.println("Input JSONFilePath: " +JSONFilePath);
				JSONfileLocator.setText(JSONFilePath);
			}
		});
		BrowseJSONFile.setBounds(487, 152, 81, 20);
		frame.getContentPane().add(BrowseJSONFile);
		
		JButton BrowseXPathFile = new JButton("Browse");
		BrowseXPathFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileChooser FC = new FileChooser();
				XPathFilePath= FC.fileChose();
				System.out.println("XPathFilePath: " +XPathFilePath);
				Xpath_MaxLFileLocator.setText(XPathFilePath);
			}
		});
		BrowseXPathFile.setBounds(486, 195, 83, 20);
		frame.getContentPane().add(BrowseXPathFile);
		
		JButton btnGenerateData = new JButton("Generate Data");
		btnGenerateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSON_BlankTagGenerator BTG = new JSON_BlankTagGenerator();
				try {
					int JSONfilesEdited = BTG.filesEdited;
					System.out.println(JSONfilesEdited);
					BTG.BlankTagGenerator(JSONFilePath, XPathFilePath, OutPutDirPath);
					JOptionPane.showMessageDialog(frame, " Edited JSON Files have been saved @" +OutPutDirPath);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGenerateData.setBounds(233, 304, 129, 23);
		frame.getContentPane().add(btnGenerateData);
		
		JLabel lblNewLabel = new JLabel("JSON Parser - Bulk Data Editor");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 25, 388, 66);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblJsonFilePath = new JLabel("JSON File Path");
		lblJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJsonFilePath.setBounds(13, 156, 126, 14);
		frame.getContentPane().add(lblJsonFilePath);
		
		JLabel lblXPathFilePath = new JLabel("XPath File Path");
		lblXPathFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXPathFilePath.setBounds(10, 199, 126, 14);
		frame.getContentPane().add(lblXPathFilePath);
		
		JLabel label_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Cognizant_Logo.png")).getImage();
		label_1.setIcon(new ImageIcon(img));
		label_1.setBounds(454, 0, 128, 131);
		frame.getContentPane().add(label_1);
		
		JLabel lblEditedJsonFilePath = new JLabel("Choose Output Folder");
		lblEditedJsonFilePath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEditedJsonFilePath.setBounds(9, 247, 126, 14);
		frame.getContentPane().add(lblEditedJsonFilePath);
		
		OutputDirectoryLoc = new JTextField();
		OutputDirectoryLoc.setColumns(10);
		OutputDirectoryLoc.setBounds(143, 244, 334, 20);
		frame.getContentPane().add(OutputDirectoryLoc);
		
		JButton BrowseJSONOutputDir = new JButton("Browse");
		BrowseJSONOutputDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				FileChooser FC = new FileChooser();
				OutPutDirPath= FC.OutputDirChose();
				System.out.println("OutPutDirPath: " +OutPutDirPath);
				OutputDirectoryLoc.setText(OutPutDirPath);
			}
		});
		BrowseJSONOutputDir.setBounds(487, 244, 80, 20);
		frame.getContentPane().add(BrowseJSONOutputDir);
	}
}
