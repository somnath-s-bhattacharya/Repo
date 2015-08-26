package com.PerformanceTest_MultiPartFormData_PostMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.logging.CouchLogger;

/**
 * author @ Somnath Bhattacharjee
 */
public class MultiPart_FormData_POST_ThreadHandler {
	
	public static int i;	
	public static String URL;
	
	public void ThreadHandler(int users, int rampUpTime, String url) throws IOException, InterruptedException {
		
		URL=url;
		MultiPart_FormData_POST_TestRunnable PTR1 = new MultiPart_FormData_POST_TestRunnable();
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
