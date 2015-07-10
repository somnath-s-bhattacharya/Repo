package com.JSONtoXMLParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
	/*
	 author @Somnath Bhattacharjee
	 */

public class JSONtoXMLConverter 
{
	
	static StringBuilder sb = new StringBuilder();
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {   
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        System.out.println("JSON stored in StringBuilder");
	        return sb.toString();
	    } 
	    finally {
	        br.close();
	    }
	 }
	
	public static String JSONtoXMLParser() throws JSONException, FileNotFoundException {
		String str = sb.toString();
		JSONObject json = new JSONObject(str);
		String xml = XML.toString(json);
        
        /*
         Write out XML file
         */
        
        BufferedWriter writer = null;
        try
        {
        	String inputXML ="Z-Resources/SourceXMLFile/InputXML.xml";
            writer = new BufferedWriter(new FileWriter(inputXML));
            writer.write( xml);
            System.out.println("Input XML file prepared");
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
        return xml;
	}
	

}