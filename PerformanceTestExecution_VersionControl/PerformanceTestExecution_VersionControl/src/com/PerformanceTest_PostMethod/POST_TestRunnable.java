package com.PerformanceTest_PostMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.AssertionHandler.ContainsAssertionHandle;
import com.AssertionHandler.ValueAssertionHandle;
import com.View.AddContainsAssertions;
import com.View.AddHeaders;
import com.View.AddValueAssertions;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;

/**
 * author @ Somnath Bhattacharjee
 */

public class POST_TestRunnable implements Runnable{

	public static StringBuilder sb1 = new StringBuilder();
	public static StringBuffer sb2 = new StringBuffer();
	public static StringBuffer sb3 = new StringBuffer();
	public static String successlog = null;
	public static String SuccessLogFile =null;
	public static String ErrorLogFile =null;
	public static int j=0;
	public static String successkey=null; 
	public static String errorkey=null;
	public static long loopduration =0;
	
	/**
	 Static Methods Used By Runnable - Start
	  */
	
	public String readData() throws IOException
	{
		//store JSON Payload in string
		POST_ThreadHandler PTTH1 = new POST_ThreadHandler();
		BufferedReader br1 = new BufferedReader(new FileReader(PTTH1.inputFileName));
	    try {   
	        String line = br1.readLine();
	        while (line != null) {
	            sb1.append(line.trim());
	            sb1.append("\n");
	            line = br1.readLine();
	        }
	    } 
	    finally {
	        br1.close();
	    }
	    return sb1.toString();
	}
	
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
	
	
	public void run() 
	{
		POST_ThreadHandler PTTH = new POST_ThreadHandler();
		HttpPost post = new HttpPost(PerformanceTestExecutor.webURL);
		post.setHeader(AddHeaders.HN1, AddHeaders.HV1);
		post.setHeader(AddHeaders.HN2, AddHeaders.HV2);
		post.setHeader(AddHeaders.HN3, AddHeaders.HV3);
		post.setHeader(AddHeaders.HN4, AddHeaders.HV4);
		post.setHeader(AddHeaders.HN5, AddHeaders.HV5);
		post.setHeader(AddHeaders.HN6, AddHeaders.HV6);
		post.setHeader(AddHeaders.HN7, AddHeaders.HV7);
		post.setHeader(AddHeaders.HN8, AddHeaders.HV8);
		post.setHeader(AddHeaders.HN9, AddHeaders.HV9);
		post.setHeader(AddHeaders.HN10, AddHeaders.HV10);
	    post.setHeader("Username",PerformanceTestExecutor.username);
	    post.setHeader("Password",PerformanceTestExecutor.pwd);
	    PerformanceTestExecutor.startTimeloop = System.currentTimeMillis();
	    
	    try 
	    {
	    	post.setEntity(new StringEntity(readData(), "UTF-8"));
		} 
	    catch (UnsupportedCharsetException e1) 
	    {
			System.out.println("Data Reading Exception: " +e1.toString());
		} 
	    catch (IOException e1) 
	    {
			System.out.println("Data Reading/POSTING: " +e1.toString());
		}
	    
	    while(!PerformanceTestExecutor.timeup)
		{
	    try
	    {	
	    	long startTime = System.currentTimeMillis();
		    HttpClient client = HttpClientBuilder.create().build();
		    HttpResponse httpresponse = client.execute(post);
		    long endTime = System.currentTimeMillis();
		    PerformanceTestExecutor.totRequests = PerformanceTestExecutor.totRequests + 1;
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
				    successkey = "USER ID: "+ PTTH.i + " " +System.currentTimeMillis();
				    successlog = successkey +","+duration +"\n";
				    sb2.append(successlog);
				    }
				    else
				    {
				    	errorkey = "Error(Business) @ USER: " + (PTTH.i) + " " + System.currentTimeMillis();
				    	String serviceErrorLog = errorkey +","+ "Response Does Not Contain: " + ContainsAssertionHandle.errorField_s + "\n";
			            sb3.append(serviceErrorLog);
				    }
			    }
			    if (AddValueAssertions.responseField_ValueSET != null)
			    {	
				    if (ValueAssertionHandle.assertResponse(result) == true)
				    {	
				    successkey = "USER ID: "+ PTTH.i + " " +System.currentTimeMillis();
				    successlog = successkey +","+duration +"\n";
				    sb2.append(successlog);
				    }
				    else
				    {
				    	errorkey = "Error(Business) @ USER: " + (PTTH.i) + " " + System.currentTimeMillis();
				    	String serviceErrorLog = errorkey +","+ "Response Does Not Contain: " + ValueAssertionHandle.errorField_s + "\n";
			            sb3.append(serviceErrorLog);
				    }
			    }
		    }
		    else
		    {
		    	errorkey = "Error @ USER: " + (PTTH.i) + System.currentTimeMillis();
		    	String serviceErrorLog = errorkey +","+ httpresponse.getStatusLine().getStatusCode()+httpresponse.getStatusLine() +"\n";
	            sb3.append(serviceErrorLog);
	        }
		    PerformanceTestExecutor.totTime = PerformanceTestExecutor.totTime + duration;
		    
		    /*Write SuccessLog in a txt File*/
		  
		    BufferedWriter writer1 = null;
	        try
	        {
	        	SuccessLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionSuccessLog_POSTMethod.txt";
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
	        	ErrorLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionErrorLog_POSTMethod.txt";
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
	    	errorkey = "Error @ USER: " + (PTTH.i) + System.currentTimeMillis();
	    	String serverErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(serverErrorLog);
            } 
	    catch (IOException e) {
	    	errorkey = "Error @ USER: " + (PTTH.i) + System.currentTimeMillis();
	    	String ioErrorLog = errorkey +","+ e.toString() +"\n";
        	sb3.append(ioErrorLog);
        	}
	    catch(Exception e){
	    	errorkey = "Error @ USER: " + (PTTH.i) + System.currentTimeMillis();
	    	String clientErrorLog = errorkey +","+ e.toString() +"\n";
            sb3.append(clientErrorLog);
            }
		    try 
	    	{
				Thread.sleep(PerformanceTestExecutor.testDelay);
			} 
	    	catch (InterruptedException e) 
	    	{
	    		errorkey = "Error @ USER: " + (PTTH.i) + " " + System.currentTimeMillis();
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

