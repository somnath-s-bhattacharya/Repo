package com.MWT_LotDetails;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;

import com.JSONtoXMLParser.JSONtoXMLConverter;

public class GetLotDetails {

	
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String jsonPrettyPrintString = null;
	public static StringBuilder sb1 = new StringBuilder();
	
	public static void main(String[] args) throws IOException, JSONException {
		
		HttpGet get = new HttpGet("http://mws.qa.copart.com/mobile-lotDetail-ws/service/lotDetail/anonymous/40905843");
	    get.setHeader("Accept-Encoding","gzip,deflate");
	    get.setHeader("site","copart.com");
	    get.setHeader("Host","mws.qa.copart.com");	    
	    get.setHeader("Connection","Keep-Alive");
//	    get.setHeader("Authorization","Global HTTP Settings");
//	    get.setHeader("username","17331");
//	    get.setHeader("password", "17331");
    
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
					    JSONtoXMLConverter JXC = new JSONtoXMLConverter();
					    String xml = JXC.JSONtoXMLParser(result);
					    
					    /*
				         Write out Response XML file
				         */
				        
				        BufferedWriter writer = null;
				        try
				        {
				        	String responseXML ="Z-Resources/MWT/GetLotDetails/ResponseXML.xml";
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
