package com.XPathGen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.json.XML;

public class Launcher {
	
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();

        xr.setContentHandler(new FragmentContentHandler(xr));
        BufferedReader br = new BufferedReader(new FileReader("InputJSON.txt"));
	    try {   
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        System.out.println("JSON stored in StringBuilder");
	        
	    } 
	    finally {
	        br.close();
	    }
	    
	    
	    String str = sb.toString();
		JSONObject json = new JSONObject(str);
		String xml = XML.toString(json);
        
        /*
         Write out XML file
         */
        
        BufferedWriter writer = null;
        try
        {
        	String inputXML ="InputXML.xml";
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
        
        xr.parse(new InputSource(new FileInputStream("InputXML.xml")));
               
     }
    
    
}
