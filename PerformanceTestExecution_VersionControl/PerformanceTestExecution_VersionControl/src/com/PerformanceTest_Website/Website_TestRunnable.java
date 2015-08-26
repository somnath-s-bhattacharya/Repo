package com.PerformanceTest_Website;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.View.AddHeaders;
import com.View.PerformanceTestExecutor;
import com.sun.xml.internal.ws.api.databinding.ClientCallBridge;

public class Website_TestRunnable implements Runnable{
	
public static StringBuilder sb1 = new StringBuilder();
public static StringBuffer sb2 = new StringBuffer();
public static StringBuffer sb3 = new StringBuffer();
public static int j=0;
public static String successlog = null;
public static String ErrorLog = null;
public static String SuccessLogFile =null;
public static String ErrorLogFile =null;
public static String successkey=null;
public static String errorkey=null;
private volatile boolean exit = false;

	
	public void run() {
		
		Website_ThreadHandler WS = new Website_ThreadHandler();
		HttpGet get = new HttpGet(PerformanceTestExecutor.webURL);
		get.setHeader(AddHeaders.HN1, AddHeaders.HV1);
		get.setHeader(AddHeaders.HN2, AddHeaders.HV2);
		get.setHeader(AddHeaders.HN3, AddHeaders.HV3);
		get.setHeader(AddHeaders.HN4, AddHeaders.HV4);
		get.setHeader(AddHeaders.HN5, AddHeaders.HV5);
		get.setHeader(AddHeaders.HN6, AddHeaders.HV6);
		get.setHeader(AddHeaders.HN7, AddHeaders.HV7);
		get.setHeader(AddHeaders.HN8, AddHeaders.HV8);
		get.setHeader(AddHeaders.HN9, AddHeaders.HV9);
		get.setHeader(AddHeaders.HN10, AddHeaders.HV10);
		get.setHeader("Username",PerformanceTestExecutor.username);
		get.setHeader("Password",PerformanceTestExecutor.pwd);
		PerformanceTestExecutor.startTimeloop = System.currentTimeMillis();
		
		while(!PerformanceTestExecutor.timeup)
		{	
	    try
	    {
	    long startTime = System.currentTimeMillis();
	    HttpClient client = HttpClientBuilder.create().disableAutomaticRetries().build();
	    HttpResponse httpresponse = client.execute(get);
	    long endTime = System.currentTimeMillis();
	    long duration = (endTime - startTime);
		    if (httpresponse.getStatusLine().getStatusCode()==200)
		    {
		    successkey = "USER ID: "+ WS.i +" " + System.currentTimeMillis();
		    successlog = successkey +","+duration+ "\n";
		    sb2.append(successlog);
		    }
		    else
		    {
		    	errorkey = "Error @ USER: " + (WS.i) + " " + System.currentTimeMillis();
		    	String serviceErrorLog = errorkey +","+ httpresponse.getStatusLine().getStatusCode()+httpresponse.getStatusLine() +"\n";
		    	sb3.append(serviceErrorLog);
	         }
	    PerformanceTestExecutor.totTime = PerformanceTestExecutor.totTime + duration;
	    
	    /*Write SuccessLog in a txt File*/
	    BufferedWriter writer1 = null;
        try
        {
        	SuccessLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionSuccessLog_Website.txt";
        	writer1 = new BufferedWriter(new FileWriter(SuccessLogFile));
            writer1.write(sb2.toString());
         }
        catch ( IOException e)
        {
        	System.out.println("FileWriting Exception Type 1: " +e.toString());
        }
        finally
        {
            try
            {
                if ( writer1 != null)
                writer1.close( );
            }
            catch ( IOException e)
            {
            	System.out.println("FileWriting Exception Type 2: " +e.toString());
            }
        }
        
        
        /*Write ErrorLog in a txt File*/
		  
	    BufferedWriter writer2 = null;
        try
        {
        	ErrorLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionErrorLog_Website.txt";
            writer2 = new BufferedWriter(new FileWriter(ErrorLogFile));
            writer2.write(sb3.toString());
            
        }
        catch ( IOException e)
        {
        	System.out.println("FileWriting Exception Type 1: " +e.toString());
        }
        finally
        {
            try
            {
                if ( writer2 != null)
                writer2.close( );
            }
            catch ( IOException e)
            {
            	System.out.println("FileWriting Exception Type 2: " +e.toString());
            }
        }
        
	    }   
	    catch (ClientProtocolException e) {
	    	errorkey = "Error @ USER: " + (WS.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
         	} 
	    catch (IOException e) {
        	errorkey = "Error @ USER: " + (WS.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
            }
	    catch (Exception e) {
        	errorkey = "Error @ USER: " + (WS.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
            }
	    	try 
	    	{
				Thread.sleep(PerformanceTestExecutor.testDelay);
			} 
	    	catch (InterruptedException e) 
	    	{
	    		errorkey = "Error @ USER: " + (WS.i) + " " + System.currentTimeMillis();
		    	String interruptedThreadErrorLog = errorkey +","+ e.toString() +"\n";
	            sb3.append(interruptedThreadErrorLog);
			}
	    	if (PerformanceTestExecutor.loopduration < PerformanceTestExecutor.timeLimit*60*1000)
		    {
	    		PerformanceTestExecutor.endTimeloop = System.currentTimeMillis();
	    		PerformanceTestExecutor.loopduration = PerformanceTestExecutor.endTimeloop - PerformanceTestExecutor.startTimeloop;
			}
		    else
		    {
		    	PerformanceTestExecutor.timeup = true;
		    }
		}
		
	}
	
}
