package com.AssertionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.View.AddContainsAssertions;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;

/**
 * author @ Somnath Bhattacharjee
 */

public class ContainsAssertionHandle 
{	
	public static boolean assertion = true;
	public static String AssertionValue =null;
	public static String jsonPath = null;
	public static String errorField_s = null;
	
	public static boolean assertResponse(String json)
	{
		if(AddContainsAssertions.responseFieldSET != null)
		{
			if(AddContainsAssertions.responseFieldSET.contains(","))
			{
				List<String> list = new ArrayList<String>(Arrays.asList(AddContainsAssertions.responseFieldSET.split(",")));
				for(int i=0; i<list.size(); i++)
				{
					List<String> errorlist = new ArrayList<String>(list.size());
					jsonPath = list.get(i);
					try
					{
						if (JsonPath.read(json, jsonPath) != null)
						{
							AssertionValue = jsonPath + ": "+ JsonPath.read(json, jsonPath);
						}
						else
						{
							assertion = false;
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
				jsonPath = AddContainsAssertions.responseFieldSET;
				try
				{
					if (JsonPath.read(json, jsonPath) != null)
					{
						AssertionValue = jsonPath + ": "+ JsonPath.read(json, jsonPath);
					}
					else
					{
						assertion = false;
					}
				}
				catch(InvalidPathException e)
				{
					assertion = false;
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
