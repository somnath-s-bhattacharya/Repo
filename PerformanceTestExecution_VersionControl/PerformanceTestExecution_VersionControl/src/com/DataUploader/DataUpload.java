package com.DataUploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.PerformanceTest_GetMethod.GET_TestRunnable;
import com.PerformanceTest_MultiPartFormData_PostMethod.MultiPart_FormData_POST_TestRunnable;
import com.PerformanceTest_PostMethod.POST_TestRunnable;
import com.PerformanceTest_Website.Website_TestRunnable;
import com.ThreadSelection.WebMethodSelector;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;



public class DataUpload 
{
	public static StringBuilder sb1 = null;
	public static StringBuilder sb2 = null;
	public static BufferedReader br = null;
	
	public static String SuccessKey = null;
	public static String timeElapsed = null;
	public static String ErrorKey = null;
	public static String errorReason = null;
	
	public static void successLogLoader() throws IOException
	{
		if (WebMethodSelector.methodselectorIndex == 2)
		{
			POST_TestRunnable PTR = new POST_TestRunnable();
			br = new BufferedReader(new FileReader(PTR.SuccessLogFile));
			String next = br.readLine();
			if (next!=null) {
			try {
				PerformanceTestExecutor.totSuccessResponse =1;
		        String line = br.readLine();
		        while (line != null) {
		        	PerformanceTestExecutor.totSuccessResponse = PerformanceTestExecutor.totSuccessResponse + 1;
		        	SuccessKey = line.substring(0, line.indexOf(","));
		        	timeElapsed = line.substring(line.indexOf(",")+1,line.length());
		        	CouchLogger.testData("Time Elapsed", timeElapsed, SuccessKey);
		        	line = br.readLine();
		        }
		    } 
		    finally {
		        br.close();
		    }
			}
			else{
				PerformanceTestExecutor.totSuccessResponse = 0;
				}
		}
		else if (WebMethodSelector.methodselectorIndex == 1)
			{
			GET_TestRunnable GTH = new GET_TestRunnable();
			br = new BufferedReader(new FileReader(GTH.SuccessLogFile));
			String next = br.readLine();
			if (next!=null) {
			try {
				PerformanceTestExecutor.totSuccessResponse = 1;
		        String line = br.readLine();
		        while (line != null) {
		        	PerformanceTestExecutor.totSuccessResponse = PerformanceTestExecutor.totSuccessResponse + 1;
		        	SuccessKey = line.substring(0, line.indexOf(","));
		        	timeElapsed = line.substring(line.indexOf(",")+1,line.length());
		        	CouchLogger.testData("Time Elapsed", timeElapsed, SuccessKey);
		        	line = br.readLine();
		        }
		    } 
		    finally {
		        br.close();
		    }
			}
			else{
				PerformanceTestExecutor.totSuccessResponse = 0;
			}
			}
			else if (WebMethodSelector.methodselectorIndex == 3)
				{	
					System.out.println("Functionality Under Dev");
					//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
					//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
				}	
				else if (WebMethodSelector.methodselectorIndex == 4)
					{
						System.out.println("Functionality Under Dev");
						//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
						//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
					}
					else if (WebMethodSelector.methodselectorIndex == 5)
					{
						Website_TestRunnable WS = new Website_TestRunnable();
						br = new BufferedReader(new FileReader(WS.SuccessLogFile));
						String next = br.readLine();
						if (next!=null) {
						try {
							PerformanceTestExecutor.totSuccessResponse = 1;
					        String line = br.readLine();
					        while (line != null) {
					        	PerformanceTestExecutor.totSuccessResponse = PerformanceTestExecutor.totSuccessResponse + 1;
					        	SuccessKey = line.substring(0, line.indexOf(","));
					        	timeElapsed = line.substring(line.indexOf(",")+1,line.length());
					        	CouchLogger.testData("Time Elapsed", timeElapsed, SuccessKey);
					        	line = br.readLine();
					        }
					    } 
					    finally {
					        br.close();
					    }
						}
						else{
							PerformanceTestExecutor.totSuccessResponse = 0;
						}
					}
					else if (WebMethodSelector.methodselectorIndex == 6)/**Multi Part Logc*/
					{
						Website_TestRunnable WS = new Website_TestRunnable();
						br = new BufferedReader(new FileReader(WS.SuccessLogFile));
						String next = br.readLine();
						if (next!=null) {
						try {
							PerformanceTestExecutor.totSuccessResponse = 1;
					        String line = br.readLine();
					        while (line != null) {
					        	PerformanceTestExecutor.totSuccessResponse = PerformanceTestExecutor.totSuccessResponse + 1;
					        	SuccessKey = line.substring(0, line.indexOf(","));
					        	timeElapsed = line.substring(line.indexOf(",")+1,line.length());
					        	CouchLogger.testData("Time Elapsed", timeElapsed, SuccessKey);
					        	line = br.readLine();
					        }
					    } 
					    finally {
					        br.close();
					    }
						}
						else{
							PerformanceTestExecutor.totSuccessResponse = 0;
						}
					}
					else
					{
						
					}
	}
	
	public static void errorLogLoader() throws IOException
	{
		if (WebMethodSelector.methodselectorIndex == 2)
		{
			POST_TestRunnable PTR = new POST_TestRunnable();
			br = new BufferedReader(new FileReader(PTR.ErrorLogFile));
			String next = br.readLine();
			if (next!=null) {
	          try {   
	        	  PerformanceTestExecutor.totErrorResponse = 1;  
	        	  String line = br.readLine();
	        	  while (line != null) {
		        	PerformanceTestExecutor.totErrorResponse = PerformanceTestExecutor.totErrorResponse + 1;
		        	ErrorKey = line.substring(0, line.indexOf(","));
		        	errorReason = line.substring(line.indexOf(",")+1,line.length());
		        	CouchLogger.testData("Error Reason", errorReason, ErrorKey);
		        	line = br.readLine();
		        }
		        
		    }
			
		    finally {
		        br.close();
		    }
			}
			else{
				PerformanceTestExecutor.totErrorResponse = 0;
			}
		}
		else if (WebMethodSelector.methodselectorIndex == 1)
			{	
			GET_TestRunnable GTH = new GET_TestRunnable();
			br = new BufferedReader(new FileReader(GTH.ErrorLogFile));
			String next = br.readLine();
			if (next!=null) {
			try { 
				PerformanceTestExecutor.totErrorResponse = 1;
				String line = br.readLine();
		        while (line != null) {
		        	PerformanceTestExecutor.totErrorResponse = PerformanceTestExecutor.totErrorResponse + 1;
		        	ErrorKey = line.substring(0, line.indexOf(","));
		        	errorReason = line.substring(line.indexOf(",")+1,line.length());
		        	CouchLogger.testData("Error Reason", errorReason, ErrorKey);
		        	line = br.readLine();
		        }
		        
		    }
			
		    finally {
		        br.close();
		    }
			}
			else{
				PerformanceTestExecutor.totErrorResponse = 0;
			}
			}
			else if (WebMethodSelector.methodselectorIndex == 3)
				{	
					System.out.println("Functionality Under Dev");
					//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
					//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
				}	
				else if (WebMethodSelector.methodselectorIndex == 4)
					{
						System.out.println("Functionality Under Dev");
						//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
						//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
					}
					else if (WebMethodSelector.methodselectorIndex == 5)
					{
						Website_TestRunnable WS = new Website_TestRunnable();
						br = new BufferedReader(new FileReader(WS.ErrorLogFile));
						String next = br.readLine();
						if (next!=null) {
						try { 
							PerformanceTestExecutor.totErrorResponse = 1;
							String line = br.readLine();
					        while (line != null) {
					        	PerformanceTestExecutor.totErrorResponse = PerformanceTestExecutor.totErrorResponse + 1;
					        	ErrorKey = line.substring(0, line.indexOf(","));
					        	errorReason = line.substring(line.indexOf(",")+1,line.length());
					        	CouchLogger.testData("Error Reason", errorReason, ErrorKey);
					        	line = br.readLine();
					        }
					        
					    }
						
					    finally {
					        br.close();
					    }
						}
						else{
							PerformanceTestExecutor.totErrorResponse = 0;
						}
					}
					else if (WebMethodSelector.methodselectorIndex == 6)
					{
						MultiPart_FormData_POST_TestRunnable MP = new MultiPart_FormData_POST_TestRunnable();
						br = new BufferedReader(new FileReader(MP.ErrorLogFile));
						String next = br.readLine();
						if (next!=null) {
						try { 
							PerformanceTestExecutor.totErrorResponse = 1;
							String line = br.readLine();
					        while (line != null) {
					        	PerformanceTestExecutor.totErrorResponse = PerformanceTestExecutor.totErrorResponse + 1;
					        	ErrorKey = line.substring(0, line.indexOf(","));
					        	errorReason = line.substring(line.indexOf(",")+1,line.length());
					        	CouchLogger.testData("Error Reason", errorReason, ErrorKey);
					        	line = br.readLine();
					        }
					        
					    }
						
					    finally {
					        br.close();
					    }
						}
						else
						{
							PerformanceTestExecutor.totErrorResponse = 0;
						}
	
					}
	}
		

}
