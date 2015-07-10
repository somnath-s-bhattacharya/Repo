package com.BlankTag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.JSONtoXMLParser.JSONtoXMLConverter;

/*
author @Somnath Bhattacharjee
*/

public class JSON_BlankTagGenerator 
{	
	/*Variable Declarations*/
	static String inputXMLFile = new File("Z-Resources/InputXMLFile/InputXML.xml").getAbsolutePath();
	static String outputFile = null;
	static List<Cell> cells = new ArrayList<Cell>();
	static String output_xml=null;
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String jsonPrettyPrintString = null;
	public static int filesEdited;
	static String fileNameAppender = null;
	
	
	public static void BlankTagGenerator(String JSONFilePath, String XPathFilePath, String OutputDir) throws Exception { 
		/*Read InputJSON File*/
		JSONtoXMLConverter JXC = new JSONtoXMLConverter();
		JXC.readFile(new File(JSONFilePath).getAbsolutePath());
		JXC.JSONtoXMLParser();
		
		/*Read XPath Excel File*/
	    //test file is located in your project path         
	    FileInputStream fileIn = new FileInputStream(new File(XPathFilePath).getAbsoluteFile());
	    //read file 
	    POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
	    HSSFWorkbook filename = new HSSFWorkbook(fs);
	    //select your worksheet
	    HSSFSheet sheet = filename.getSheet("X-Path");
	    //search for column index containing string "Your Column Name" in the row 0 (which is first row of a worksheet
	    String columnWanted = "SourceXPath";
	    Integer columnNo = null;
	    //output all not null values to the list
	    Row firstRow = sheet.getRow(0);
	    for(Cell cell:firstRow)
	    {
	        if (cell.getStringCellValue().equals(columnWanted))
	        {
	            columnNo = cell.getColumnIndex();
	        }
	    }
	    if (columnNo != null){
	    for (Row row : sheet) {
	       Cell c = row.getCell(columnNo);
	       if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) 
	       {
	          // Nothing in the cell in this row, skip it
	       } else {
	          cells.add(c);
	       }
	    }
	   System.out.println(cells);
	   filesEdited = cells.size()-1;
	   System.out.println(filesEdited);
	   System.out.println("XPath stored in List");
	    }else
	    {
	        System.out.println("could not find column " + columnWanted + " in first row of " + fileIn.toString());
	    }
/*======================================================================================================================================================================= */
	    
	    /*run loop to traverse XPath List
	     *pick one XPath
	     *delete data
	     *create file
	     *move to next XPath 
	     */
	    for (int i=1; i<=cells.size()-1;i++)
	    {
		    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputXMLFile));
		    		    
		    // locate the node(s)
		    XPath xpath = XPathFactory.newInstance().newXPath();
		    String rtXPath = cells.get(i).toString();
		    NodeList nodes = (NodeList)xpath.evaluate(rtXPath, doc, XPathConstants.NODESET);
		    
		    System.out.println("Identified Node:" +cells.get(i)+nodes.getLength());
		    
		    String appender1 = rtXPath.replaceAll("AssignmentEntryData", "");
		    String appender2 = appender1.replaceAll("/", "_");
		    fileNameAppender = appender2.replaceAll("___", "");
		    

		    /*================================================================================================================*/
		    
		    // make the change at selected node
		    for (int idx = 0; idx < nodes.getLength(); idx++) 
		    	{
		    		nodes.item(idx).setTextContent("");
		    		System.out.println("Data removed from Identified Node:" +cells.get(i));
		    	}
		    /*================================================================================================================*/
		    
		   // save the output XML
	      Transformer xformer = TransformerFactory.newInstance().newTransformer();
	      outputFile = new File("Z-Resources/EditedXMLFile/EditedXML_"+i+"_"+fileNameAppender+".xml").getAbsolutePath();
	      xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFile)));
	      System.out.println("Output XML File generated");
	      /*=====================================================================================================================*/
	      
	      //read output XML
	      StringBuilder sb = new StringBuilder();
	      BufferedReader br = new BufferedReader(new FileReader(outputFile));
		    try {   
		        String line = br.readLine();
		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		        System.out.println("XML stored in StringBuilder");
		        output_xml = sb.toString();
		        //System.out.println(output_xml);
		    } 
		    finally 
		    {
		        br.close();
		    }
		    /*=====================================================================================================================*/
	
		    //Parse XML into JSON and save JSON file
			
			try {
	        	JSONObject xmlJSONObj = XML.toJSONObject(output_xml);
	            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	            //System.out.println(jsonPrettyPrintString);  
	            
	            //create JSON file
	            BufferedWriter writer = null;
	            try
	            {
	                writer = new BufferedWriter(new FileWriter(new File(OutputDir+"EditedJSON_" +i+"_"+fileNameAppender+".txt").getAbsoluteFile()));
	                writer.write(jsonPrettyPrintString);
	                System.out.println("Output JSON file saved");
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
			catch (JSONException je1) {
	            System.out.println(je1.toString());
	        }
			/*========================================================================================================================*/
	
	    }
	}
}
