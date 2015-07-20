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
		
	public static String JSONtoXMLParser(String response) throws JSONException, FileNotFoundException {
		String str = response;
		JSONObject json = new JSONObject(str);
		String xml = XML.toString(json);
        return xml;

	}
	

}