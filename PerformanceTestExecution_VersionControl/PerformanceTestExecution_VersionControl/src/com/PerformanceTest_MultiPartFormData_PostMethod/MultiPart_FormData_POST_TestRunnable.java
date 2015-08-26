package com.PerformanceTest_MultiPartFormData_PostMethod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.AssertionHandler.ContainsAssertionHandle;
import com.AssertionHandler.ValueAssertionHandle;
import com.View.AddContainsAssertions;
import com.View.AddHeaders;
import com.View.AddValueAssertions;
import com.View.Attachments;
import com.View.MultiPartData_RequestForm;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;


/**
 * author @ Somnath Bhattacharjee
 */

public class MultiPart_FormData_POST_TestRunnable implements Runnable{

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
		MultiPart_FormData_POST_ThreadHandler PTTH = new MultiPart_FormData_POST_ThreadHandler();
		CloseableHttpClient httpClient = HttpClients.createDefault();
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
	    
	    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	    if(MultiPartData_RequestForm.HN1 !=null && MultiPartData_RequestForm.HV1 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN1, MultiPartData_RequestForm.HV1, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN2 !=null && MultiPartData_RequestForm.HV2 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN2, MultiPartData_RequestForm.HV2, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN3 !=null && MultiPartData_RequestForm.HV3 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN3, MultiPartData_RequestForm.HV3, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN4 !=null && MultiPartData_RequestForm.HV4 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN4, MultiPartData_RequestForm.HV4, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN5 !=null && MultiPartData_RequestForm.HV5 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN5, MultiPartData_RequestForm.HV5, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN6 !=null && MultiPartData_RequestForm.HV6 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN6, MultiPartData_RequestForm.HV6, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN7 !=null && MultiPartData_RequestForm.HV7 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN7, MultiPartData_RequestForm.HV7, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN8 !=null && MultiPartData_RequestForm.HV8 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN8, MultiPartData_RequestForm.HV8, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN9 !=null && MultiPartData_RequestForm.HV9 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN9, MultiPartData_RequestForm.HV9, ContentType.APPLICATION_JSON);}
	    if(MultiPartData_RequestForm.HN10 !=null && MultiPartData_RequestForm.HV10 !=null)
	    {builder.addTextBody(MultiPartData_RequestForm.HN10, MultiPartData_RequestForm.HV10, ContentType.APPLICATION_JSON);}
	    
	    if(Attachments.filename1 != null)
	    {builder.addBinaryBody("file1", new File(Attachments.filename1), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename1.substring(Attachments.filename1.lastIndexOf(File.separator), Attachments.filename1.length()));}
	    if(Attachments.filename2 != null)
	    {builder.addBinaryBody("file2", new File(Attachments.filename2), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename2.substring(Attachments.filename2.lastIndexOf(File.separator), Attachments.filename2.length()));}
	    if(Attachments.filename3 != null)
	    {builder.addBinaryBody("file3", new File(Attachments.filename3), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename3.substring(Attachments.filename3.lastIndexOf(File.separator), Attachments.filename3.length()));}
	    if(Attachments.filename4 != null)
	    {builder.addBinaryBody("file4", new File(Attachments.filename4), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename4.substring(Attachments.filename4.lastIndexOf(File.separator), Attachments.filename4.length()));}
	    if(Attachments.filename5 != null)
	    {builder.addBinaryBody("file5", new File(Attachments.filename5), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename5.substring(Attachments.filename5.lastIndexOf(File.separator), Attachments.filename5.length()));}
	    if(Attachments.filename6 != null)
	    {builder.addBinaryBody("file6", new File(Attachments.filename6), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename6.substring(Attachments.filename6.lastIndexOf(File.separator), Attachments.filename6.length()));}
	    if(Attachments.filename7 != null)
	    {builder.addBinaryBody("file7", new File(Attachments.filename7), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename7.substring(Attachments.filename7.lastIndexOf(File.separator), Attachments.filename7.length()));}
	    if(Attachments.filename8 != null)
	    {builder.addBinaryBody("file8", new File(Attachments.filename8), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename8.substring(Attachments.filename8.lastIndexOf(File.separator), Attachments.filename8.length()));}
	    if(Attachments.filename9 != null)
	    {builder.addBinaryBody("file9", new File(Attachments.filename9), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename9.substring(Attachments.filename9.lastIndexOf(File.separator), Attachments.filename9.length()));}
	    if(Attachments.filename10 != null)
	    {builder.addBinaryBody("file10", new File(Attachments.filename10), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename10.substring(Attachments.filename10.lastIndexOf(File.separator), Attachments.filename10.length()));}
	    if(Attachments.filename11 != null)
	    {builder.addBinaryBody("file11", new File(Attachments.filename11), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename11.substring(Attachments.filename11.lastIndexOf(File.separator), Attachments.filename11.length()));}
	    if(Attachments.filename12 != null)
	    {builder.addBinaryBody("file12", new File(Attachments.filename12), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename12.substring(Attachments.filename12.lastIndexOf(File.separator), Attachments.filename12.length()));}
	    if(Attachments.filename13 != null)
	    {builder.addBinaryBody("file13", new File(Attachments.filename13), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename13.substring(Attachments.filename13.lastIndexOf(File.separator), Attachments.filename13.length()));}
	    if(Attachments.filename14 != null)
	    {builder.addBinaryBody("file14", new File(Attachments.filename14), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename14.substring(Attachments.filename14.lastIndexOf(File.separator), Attachments.filename14.length()));}
	    if(Attachments.filename15 != null)
	    {builder.addBinaryBody("file15", new File(Attachments.filename15), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename15.substring(Attachments.filename15.lastIndexOf(File.separator), Attachments.filename15.length()));}
	    if(Attachments.filename16 != null)
	    {builder.addBinaryBody("file16", new File(Attachments.filename16), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename16.substring(Attachments.filename16.lastIndexOf(File.separator), Attachments.filename16.length()));}
	    if(Attachments.filename17 != null)
	    {builder.addBinaryBody("file17", new File(Attachments.filename17), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename17.substring(Attachments.filename17.lastIndexOf(File.separator), Attachments.filename17.length()));}
	    if(Attachments.filename18 != null)
	    {builder.addBinaryBody("file18", new File(Attachments.filename18), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename18.substring(Attachments.filename18.lastIndexOf(File.separator), Attachments.filename18.length()));}
	    if(Attachments.filename19 != null)
	    {builder.addBinaryBody("file19", new File(Attachments.filename19), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename19.substring(Attachments.filename19.lastIndexOf(File.separator), Attachments.filename19.length()));}
	    if(Attachments.filename20 != null)
	    {builder.addBinaryBody("file20", new File(Attachments.filename20), ContentType.APPLICATION_OCTET_STREAM, Attachments.filename20.substring(Attachments.filename20.lastIndexOf(File.separator), Attachments.filename20.length()));}
	    
	    HttpEntity multipart = builder.build();
	    post.setEntity(multipart);
	    
	    while(!PerformanceTestExecutor.timeup)
		{
	    try
	    {	
	    	long startTime = System.currentTimeMillis();
	    	CloseableHttpResponse httpresponse = httpClient.execute(post);
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
	        	SuccessLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionSuccessLog_MULTIPARTFORM-DATA(POST)_Method.txt";
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
	        	ErrorLogFile =PerformanceTestExecutor.OutPutDirPath +"ExecutionErrorLog_MULTIPARTFORM-DATA(POST)_Method.txt";
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

