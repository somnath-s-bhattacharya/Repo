package com.AssertionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.View.AddContainsAssertions;
import com.View.AddValueAssertions;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

public class ValueAssertionHandle 
{
	public static boolean assertion = true;
	public static String AssertionValue =null;
	public static String jsonPath = null;
	public static String value = null;
	public static String errorField_s = null;
	
	public static boolean assertResponse(String json)
	{
		if(AddValueAssertions.responseField_ValueSET != null)
		{
			if(AddValueAssertions.responseField_ValueSET.contains(","))
			{
				List<String> list = new ArrayList<String>(Arrays.asList(AddValueAssertions.responseField_ValueSET.split(",")));
				for(int i=0; i<list.size(); i++)
				{		
					
						String index= list.get(i).toString();
						List<String> list1 = new ArrayList<String>(Arrays.asList(index.split("=")));
						List<String> errorlist = new ArrayList<String>(list1.size());
						jsonPath = list1.get(0).toString();
						value = list1.get(1).toString();
						try
						{
							if (value.equals(JsonPath.read(json, jsonPath)))
							{
								AssertionValue = jsonPath + ": "+ JsonPath.read(json, jsonPath);
								
							}
							else
							{
								assertion = false;
								errorlist.add(jsonPath);
								errorField_s = errorlist.toString();	
							}
						}
						catch(InvalidPathException e)
						{
							assertion = false;
							errorlist.add(jsonPath);
							errorField_s = errorlist.toString();	
						}
						
				}
			}
			else
			{	
				List<String> list1 = new ArrayList<String>(Arrays.asList(AddValueAssertions.responseField_ValueSET.split("=")));
				List<String> errorlist = new ArrayList<String>(list1.size());
				jsonPath = list1.get(0).toString();
				value = list1.get(1).toString();
				try
				{
					if (value.equals(JsonPath.read(json, jsonPath)))
					{
						AssertionValue = jsonPath + ": "+ JsonPath.read(json, jsonPath);
					}
					else
					{
						assertion = false;
						errorlist.add(jsonPath);
						errorField_s = errorlist.toString();	
					}
				}
				catch(InvalidPathException e)
				{
					assertion = false;
					errorlist.add(jsonPath);
					errorField_s = errorlist.toString();
				}
			}
		}
		else
		{
			assertion = true;
		}
		return assertion;
	}
}
