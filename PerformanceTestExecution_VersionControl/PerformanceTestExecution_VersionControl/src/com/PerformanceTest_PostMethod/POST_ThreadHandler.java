package com.PerformanceTest_PostMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.PerformanceTest_PostMethod.POST_TestRunnable;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;

/**
 * author @ Somnath Bhattacharjee
 */
public class POST_ThreadHandler {
	
	public static int i;	
	public static String URL;
	public static String inputFileName;
	
	public void ThreadHandler(int users, int rampUpTime, String url, String inputFile) throws IOException, InterruptedException {
		
		URL=url;
		inputFileName = inputFile;
		POST_TestRunnable PTR1 = new POST_TestRunnable();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date startDate=new Date();
		CouchLogger.testData("StartTime", sdf.format(startDate), "TestData");
		
		Thread[] threads = new Thread[users];
		
		for (i = 0; i < threads.length; i++) 
		{
			threads[i] = new Thread(PTR1);
			threads[i].setDaemon(true);
		    threads[i].start();
		    threads[i].sleep(rampUpTime);
		      
		}
		
	}

}
