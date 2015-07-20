package com.Common_Assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;

import com.JSONtoXMLParser.JSONtoXMLConverter;

public class Common_Assignment {
	
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String jsonPrettyPrintString = null;
	public static StringBuilder sb1 = new StringBuilder();
	
	public static void main(String[] args) throws IOException, JSONException {
		
		//store JSON Payload in string
        BufferedReader br1 = new BufferedReader(new FileReader("Z-Resources/Common-Assignment/Payload/InputJSON.txt"));
	    try {   
	        String line = br1.readLine();
	        while (line != null) {
	            sb1.append(line.trim());
	            sb1.append("\n");
	            line = br1.readLine();
	        }
	        System.out.println("JSON stored in Payload StringBuilder");
	    } 
	    finally {
	        br1.close();
	    }
	    
	    HttpPost post = new HttpPost("http://ieddws001.ied.copart.com:10090/common-Assignment/assignmentEntry/");
//	    post.setHeader("Accept-Encoding","gzip,deflate");
//	    post.setHeader("source","WOM");
//	    post.setHeader("countryCode","DE");
//	    post.setHeader("language","en");
//	    post.setHeader("userEmail","rachna.sharma@copart.com");
//	    post.setHeader("Content-Type","application/json");
//	    post.setHeader("Host","iediws002.ied.copart.com:10090");	    
//	    post.setHeader("Connection","Keep-Alive");
//	    post.setHeader("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	    
	    post.setEntity(new StringEntity(sb1.toString(), "UTF-8"));
	    
	    try
	    {
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse httpresponse = client.execute(post);
	    System.out.println("Http Response Code: " +httpresponse.getStatusLine().getStatusCode());
	    
		    if (httpresponse.getStatusLine().getStatusCode() != 404)
		    {
		    	if (httpresponse.getStatusLine().getStatusCode() != 400)
		    	{	
		    		if (httpresponse.getStatusLine().getStatusCode() != 500)
		    		{
					    HttpEntity entity = httpresponse.getEntity();
					    InputStream stream = entity.getContent();
					    String result = convertStreamToString(stream);
					    System.out.println(result);
					    System.out.println("Record Creation Successfull");
					    JSONtoXMLConverter JXC = new JSONtoXMLConverter();
					    String xml = JXC.JSONtoXMLParser(result);
					    
					    /*
				         Write out Response XML file
				         */
				        
				        BufferedWriter writer = null;
				        try
				        {
				        	String responseXML ="Z-Resources/Common-Assignment/Response/ResponseXML.xml";
				            writer = new BufferedWriter(new FileWriter(responseXML));
				            writer.write(xml);
				            System.out.println("Response XML file prepared");
				        }
				        catch ( IOException e)
				        {
				        }
				        finally
				        {
				            try
				            {
				                if ( writer != null)
				                writer.close( );
				            }
				            catch ( IOException e)
				            {
				            }
				        }
		    		}
		    		else
		    		{
		    			System.out.println("Server side error. Please try again Later");
		    		}
		    	}
		    	else
		    	{
		    		System.out.println("Invalid Request. Modify Request or Add Headers");
		    	}
		    }
		    else
		    {
		    	System.out.println("Service Not Available");
		    }
	    }
	    catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        }
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
	
}

