package com.PerformanceTest_Proquote_getLotDetails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.PerformanceTest_Proquote_getLotDetails.PerformanceTestExample;

class GetLotDetailRunnable implements Runnable{
	
public static StringBuilder sb1 = new StringBuilder();
public static StringBuilder sb2 = new StringBuilder();
public static String log = null;	
	public void run() {
		
		HttpGet get = new HttpGet("https://sws.qa.copart.com/lotdetail-ws/lot/48295985");
	    get.setHeader("Connection", "Keep-Alive");
	    get.setHeader("Content-Type","application/json");
	    get.setHeader("User-Agent","Apache-HttpClient/4.1.1 (java 1.5)");
	    get.setHeader("language","en");
	    get.setHeader("source","mobile");
	    get.setHeader("countryCode","US");
	    get.setHeader("Host","sws.qa.copart.com");
	    get.setHeader("Accept-Encoding","gzip,deflate");
	    get.setHeader("Authorization","Basic ZGVtbzp3ZWxjb21lNQ==");
	    get.setHeader("username","demo");
	    get.setHeader("password","welcome5");
	    
	    
	    try
	    {
	    PerformanceTestExample PTE = new PerformanceTestExample();
	    @SuppressWarnings("static-access")
		int j = PTE.i;
	    long startTime = System.currentTimeMillis();
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpResponse httpresponse = client.execute(get);
	    long endTime = System.currentTimeMillis();
	    long duration = (endTime - startTime);
	    log = "User- "+ (j+1) +"\t\t"+"Http Response Code\t" +httpresponse.getStatusLine().getStatusCode() +"\t\t" + "Time Elapsed" + "\t" + duration+ "\n";
	    System.out.println(log);
	    sb2.append(log); 
	    
	    /*Write Log in a txt File*/
	    BufferedWriter writer = null;
        try
        {
        	String LogFile ="Z-Resources/PerformanceTestData/Proquote_GetLotDetails/ExecutionLog.txt";
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
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
