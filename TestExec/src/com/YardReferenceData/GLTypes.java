package com.YardReferenceData;

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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import java.sql.*;

import com.JSONtoXMLParser.JSONtoXMLConverter;

public class GLTypes {
	
	public static StringBuilder sb1 = new StringBuilder();
	
	public static void main(String[] args) throws IOException, JSONException {
		
		HttpGet get = new HttpGet("http://services.qa.copart.com/referencedata-ws/gltypes");
	    get.setHeader("Accept-Encoding","gzip,deflate");
	    get.setHeader("site","copart.com");
	    get.setHeader("source", "mobile");
	    get.setHeader("partnerCode", "en");
	    get.setHeader("countryCode", "US");
	    get.setHeader("Host", "services.qa.copart.com");
	    get.setHeader("Connection", "Keep-Alive");
	    get.setHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
	    
	    try
	    {
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse httpresponse = client.execute(get);
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
					    BufferedWriter writer = null;
					    
					    /*
				         Write out Response XML file
				         */
				        				        
				        try
				        {
				        	
				        	String responseXML ="Z-Resources/YardReferenceData/Response/ResponseXML.xml";
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