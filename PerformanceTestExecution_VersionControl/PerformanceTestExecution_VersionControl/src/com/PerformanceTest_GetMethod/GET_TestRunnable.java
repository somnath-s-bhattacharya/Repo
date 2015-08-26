package com.PerformanceTest_GetMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.AssertionHandler.ContainsAssertionHandle;
import com.AssertionHandler.ValueAssertionHandle;
import com.PerformanceTest_GetMethod.GET_ThreadHandler;
import com.PerformanceTest_PostMethod.POST_ThreadHandler;
import com.View.AddContainsAssertions;
import com.View.AddHeaders;
import com.View.AddValueAssertions;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;

/**
 * author @ Somnath Bhattacharjee
 */

public class GET_TestRunnable implements Runnable{
	
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
public static long loopduration =0;


/**
Static Methods Used By Runnable - Start
 */

public static String convertStreamToString(InputStream is)
{
   BufferedReader reader = new BufferedReader(new InputStreamReader(is));
   StringBuilder sb = new StringBuilder();
   String line = null;
   try
   {
       while ((line = reader.readLine()) != null)
       {
               sb.append(line + "\n");
       } 
   }
   catch (IOException e)
   {
       e.printStackTrace();
   }
   finally
   {
       try
       {
           is.close();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
   }
   return sb.toString();
}

/**
Static Methods Used By Runnable - End
 */

	public void run() {
		
		
		GET_ThreadHandler GTH = new GET_ThreadHandler();
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
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse httpresponse = client.execute(get);
	    PerformanceTestExecutor.totRequests = PerformanceTestExecutor.totRequests + 1;
	    long endTime = System.currentTimeMillis();
	    long duration = (endTime - startTime);
	    if (httpresponse.getStatusLine().getStatusCode()==200)
	    {
	    	HttpEntity entity = httpresponse.getEntity();
		    InputStream stream = entity.getContent();
		    String result = convertStreamToString(stream);
		    if(AddContainsAssertions.responseFieldSET != null)
		    {	
			    if (ContainsAssertionHandle.assertResponse(result) == true)
			    {	
			    successkey = "USER ID: "+ GTH.i + " " +System.currentTimeMillis();
			    successlog = successkey +","+duration +"\n";
			    sb2.append(successlog);
			    }
			    else
			    {
			    	errorkey = "Error(Business) @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
			    	String serviceErrorLog = errorkey +","+ "Response Does Not Contain: " + ContainsAssertionHandle.errorField_s + "\n";
		            sb3.append(serviceErrorLog);
			    }
		    }
		    if (AddValueAssertions.responseField_ValueSET != null)
		    {	
			    if (ValueAssertionHandle.assertResponse(result) == true)
			    {	
			    successkey = "USER ID: "+ GTH.i + " " +System.currentTimeMillis();
			    successlog = successkey +","+duration +"\n";
			    sb2.append(successlog);
			    }
			    else
			    {
			    	errorkey = "Error(Business) @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
			    	String serviceErrorLog = errorkey +","+ "Value Does Not Match for: " + ValueAssertionHandle.errorField_s + "\n";
		            sb3.append(serviceErrorLog);
			    }
		    }  
	    }
	    else
	    {
	    	errorkey = "Error @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
	    	String serviceErrorLog = errorkey +","+ httpresponse.getStatusLine().getStatusCode()+httpresponse.getStatusLine() +"\n";
            sb3.append(serviceErrorLog);
         }
	    PerformanceTestExecutor.totTime = PerformanceTestExecutor.totTime + duration;
	    
	    /*Write SuccessLog in a txt File*/
	    BufferedWriter writer1 = null;
        try
        {
        	SuccessLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionSuccessLog_GETMethod.txt";
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
        	ErrorLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionErrorLog_GETMethod.txt";
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
	    	errorkey = "Error @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
         	} 
	    catch (IOException e) {
        	errorkey = "Error @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
            }
	    catch(Exception e){
			errorkey = "Error @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
          	}
		    
	    try 
	    	{
				Thread.sleep(PerformanceTestExecutor.testDelay);
			} 
	    	catch (InterruptedException e) 
	    	{
	    		errorkey = "Error @ USER: " + (GTH.i) + " " + System.currentTimeMillis();
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
