package JSONtoXMLParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JSONtoXMLConverter 
{
	static StringBuilder sb = new StringBuilder();
	//String str1 =null;
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {   
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        //String str1 = sb.toString();
	        //System.out.println(str1);
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	    
	   
	}
	public static void JSONtoXMLParser() throws JSONException, FileNotFoundException {
		String str = sb.toString();
		//System.out.println(str);
		JSONObject json = new JSONObject(str);
        String xml = XML.toString(json);
        //System.out.println(xml);

        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter("D://Code//Repo//JSON Parser - Blank Data Generator//Z-Resources//Output XMLFile//sampleXML.xml"));
            writer.write( xml);
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
	

}
