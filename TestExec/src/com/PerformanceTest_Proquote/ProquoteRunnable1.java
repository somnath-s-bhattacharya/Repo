package com.PerformanceTest_Proquote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

class ProquoteRunnable1 implements Runnable{

	public static StringBuilder sb1 = new StringBuilder();
	public static StringBuilder sb2 = new StringBuilder();
	public static String log = null;
	
	public static String readData() throws IOException
	{
		//store JSON Payload in string
        BufferedReader br1 = new BufferedReader(new FileReader("Z-Resources/PerformanceTestData/Proquote_History/InputJSON.txt"));
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
	
	
	public void run() {
		String URL = "http://lvqc2r001:10081/automobile/history?sortBy=SELLER&sortType=ASC&pageNo=1&pageSize=10"; 		
		HttpPost post = new HttpPost(URL);
	    post.setHeader("Connection", "Keep-Alive");
	    post.setHeader("Content-Type","application/json");
	    post.setHeader("Accept-Encoding","gzip,deflate");
	    post.setHeader("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	    post.setHeader("partnerCode","en");
	    post.setHeader("source","mobile");
	    post.setHeader("countryCode","US");
	    post.setHeader("Host","lvqc2r001:10081");
	    post.setHeader("Authorization","Basic REVNTzp3ZWxjb21lNQ==");
	    
	    try {
	    	post.setEntity(new StringEntity(readData(), "UTF-8"));
		} catch (UnsupportedCharsetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try
	    {	
	    	PerformanceTestExample PTE = new PerformanceTestExample();
	    	@SuppressWarnings("static-access")
			int j = PTE.i;
	    	long startTime = System.currentTimeMillis();
		    HttpClient client = HttpClientBuilder.create().build();
		    HttpResponse httpresponse = client.execute(post);
		    long endTime = System.currentTimeMillis();
		    long duration = (endTime - startTime);
		    log = "User- "+ (j+1) +"\t\t"+"Http Response Code\t" +httpresponse.getStatusLine().getStatusCode() +"\t\t" + "Time Elapsed" + "\t" + duration+ "\n";
		    System.out.println(log);
		    sb2.append(log);
		    
		    /*Write Log in a txt File*/
		    BufferedWriter writer = null;
	        try
	        {
	        	String LogFile ="Z-Resources/PerformanceTestData/Proquote_History/ExecutionLog_ProquoteHistory.txt";
	            writer = new BufferedWriter(new FileWriter(LogFile));
	            writer.write(sb2.toString());
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
	    
	    catch (ClientProtocolException e) {
            // writing exception to log
            //e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch(Exception e){
			//e.printStackTrace();
		}
	  }
			
}

