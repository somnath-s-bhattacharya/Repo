package com.copart;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {
  
  static StringBuilder sb1 = new StringBuilder();
  static StringBuilder sb2 = new StringBuilder();
  public static void main(String[] args) {

    try {

        Client client = Client.create();
        WebResource webResource = client.resource("http://iediws002.ied.copart.com:10090/assignment-ws/assignment/");
        //webResource.header(
        
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
	    
	  //store message header in String
	    BufferedReader br2 = new BufferedReader(new FileReader("header.txt"));
	    try {   
	        String line = br2.readLine();
	        while (line != null) {
	            //sb2.append(line);
	            //sb2.append("\n");
	        	
	            
	        	if(line.contains(":")){
	        		webResource.setProperty(line.substring(0,  line.indexOf(":")), (line.substring(line.indexOf(":") + 1)).trim());
	        		line = br2.readLine();
	        	}
	        }
	        System.out.println("JSON stored in header StringBuilder");
	    } 
	    finally {
	        br2.close();
	    }
	    
	    //String input_header = sb2.toString();
	    //
	    System.out.println(webResource.getProperties());
	    
	    String input_payload = sb1.toString();
        System.out.println(input_payload);
       // String input =  input_header+'\n' +input_payload;
        //System.out.println(input);
        
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input_payload);
        //System.out.println(response);
		/*
        if (response.getStatus() != 400) 
        {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        */

        System.out.println("Output from Server .... \n");
        String output = response.toString();
        System.out.println(output);

      } catch (Exception e) {

        e.printStackTrace();

      }

    }
}