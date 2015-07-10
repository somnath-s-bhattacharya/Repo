package com.XPathGen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/*
 author @ Somnath Bhattacharjee
*/

public class FragmentContentHandler extends DefaultHandler {
	
	public static  String XPathwriteOut = null;
    private String xPath = "/";
    private XMLReader xmlReader;
    private FragmentContentHandler parent;
    private StringBuilder characters = new StringBuilder();
    private Map<String, Integer> elementNameCount = new HashMap<String, Integer>();
    
        
    public FragmentContentHandler(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    private FragmentContentHandler(String xPath, XMLReader xmlReader, FragmentContentHandler parent) {
        this(xmlReader);
        this.xPath = xPath;
        this.parent = parent;
                
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        
    	Integer count = elementNameCount.get(qName);
        if(null == count) 
        {
            count = 1;
        } 
        else 
        {
            count++;
        }
        elementNameCount.put(qName, count);
        
        String childXPath = xPath + "/" + qName + "[" + count + "]";
        
        int attsLength = atts.getLength();
        for(int x=0; x<attsLength; x++) 
        {
        	XPathwriteOut = childXPath;
        	System.out.println(childXPath);
        	//System.out.println(childXPath + "[@" + atts.getQName(x) + "='" + atts.getValue(x) + ']');
        }

        FragmentContentHandler child = new FragmentContentHandler(childXPath, xmlReader, this);
        xmlReader.setContentHandler(child);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	String value = characters.toString().trim();
        if(value.length() > 0) 
        {
        	XPathwriteOut = xPath;
        	System.out.println(XPathwriteOut);
        	//System.out.println(xPath + "='" + characters.toString() + "'");
        	
        }
        xmlReader.setContentHandler(parent);
        
    }

   
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        characters.append(ch, start, length);
    }
    
    
    	
} 
