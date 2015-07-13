package com.copart;

import java.io.BufferedReader;
import java.io.FileReader;
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


public class Execution {
	
	static StringBuilder sb1 = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		//store JSON Payload in string
        BufferedReader br1 = new BufferedReader(new FileReader("InputJSON.txt"));
	    try {   
	        String line = br1.readLine();
	        while (line != null) {
	            sb1.append(line.trim());
	            //sb1.append("\n");
	            line = br1.readLine();
	        }
	        System.out.println("JSON stored in Payload StringBuilder");
	    } 
	    finally {
	        br1.close();
	    }
	    
	    HttpPost post = new HttpPost("http://iediws002.ied.copart.com:10090/assignment-ws/assignment/");
	    post.setHeader("Content-type", "application/json");
	    post.setHeader("Accept-Encoding", "gzip,deflate");
	    post.setHeader("source", "WOM");
	    post.setHeader("countryCode", "DE");
	    post.setHeader("language", "en");
	    post.setHeader("userEmail", "rachna.sharma@copart.com");
	    post.setHeader("Host", "iediws002.ied.copart.com:10090");
	    post.setHeader("Connection", "Keep-Alive");
	    post.setHeader("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
	    
	    post.setEntity(new StringEntity(sb1.toString(), "UTF-8"));
	    try
	    
	    {
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse httpresponse = client.execute(post);
	    HttpEntity entity = httpresponse.getEntity();
	    InputStream stream = entity.getContent();
	    String result = convertStreamToString(stream);
	    System.out.println(result);
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
