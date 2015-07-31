package com.PerformanceTest_PostMethod;

import java.io.IOException;
import java.util.Scanner;

import com.PerformanceTest_PostMethod.PerformanceTestRunnable;
import com.View.PerformanceTestExecutor;

public class POST_ThreadHandler {
	
	public static int i;	
	public static String URL;
	public static String inputFileName;
		
	public void ThreadHandler(int users, int rampUpTime, String url, String inputFile) throws IOException, InterruptedException {
		
		URL=url;
		inputFileName = inputFile;
		PerformanceTestRunnable PTR1 = new PerformanceTestRunnable();
		Thread[] threads = new Thread[users];
		for (i = 0; i < threads.length; i++) 
		{
			threads[i] = new Thread(PTR1);
		    threads[i].start();
		    threads[i].sleep(rampUpTime);
		}
	    			
	}

}
