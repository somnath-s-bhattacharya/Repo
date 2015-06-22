package JSONtoXMLParser;

import java.io.IOException;

import org.json.JSONException;

public class ExecutorClass {

	public static void main(String[] args) throws IOException, JSONException 
	{
		JSONtoXMLConverter JXC = new JSONtoXMLConverter();
		JXC.readFile("D://Code//Repo//JSON Parser - Blank Data Generator//Z-Resources//sample.txt");
		JXC.JSONtoXMLParser();
	}

}
