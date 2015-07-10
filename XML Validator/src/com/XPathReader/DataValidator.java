package com.XPathReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.JSONtoXMLParser.JSONtoXMLConverter;


public class DataValidator {
	
	/*Variable Declarations*/
	static String inputXMLFile = new File("Z-Resources/SourceXMLFile/InputXML.xml").getAbsolutePath();
	
	static List<Cell> sor_cells = new ArrayList<Cell>();
	static List<Cell> tar_cells = new ArrayList<Cell>();
	protected static String SourceVal = null;
	protected static String TargetVal = null;
	protected static String sor_rtXPath =null;
	protected static String tar_rtXPath = null;
	
	public static void DataValidation(String JSONFilePath, String XPathFilePath, String OutputDir,String ResponseXMLFilePath) 
			throws IOException, JSONException, SAXException, ParserConfigurationException, XPathExpressionException{
		
		
		
		JSONtoXMLConverter JXC = new JSONtoXMLConverter();
		JXC.readFile(new File(JSONFilePath).getAbsolutePath());
		JXC.JSONtoXMLParser();
		
		String datetimeStamp = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
        String res_filename = OutputDir + datetimeStamp+ "_XPathResults.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet res_sheet = workbook.createSheet("X-Path Result");  
                
        HSSFRow rowhead = res_sheet.createRow((short)0);
        rowhead.createCell(0).setCellValue("SourceXPath");
        rowhead.createCell(1).setCellValue("SourceValue");
        rowhead.createCell(2).setCellValue("TargetXPath");
        rowhead.createCell(3).setCellValue("TargetValue");
        rowhead.createCell(4).setCellValue("Status");
		
	/*=========================================Handle Source XPath and Source Data============================================*/
		
		
	    //test file is located in your project path         
	    FileInputStream fileIn = new FileInputStream(new File(XPathFilePath).getAbsoluteFile());
	    //read file 
	    POIFSFileSystem fs = new POIFSFileSystem(fileIn); 
	    HSSFWorkbook filename = new HSSFWorkbook(fs);
	    //select your worksheet
	    HSSFSheet sheet = filename.getSheet("X-Path");
	    
	    
	    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	    
	    /*Read Source XPath Column from Excel File*/
	    //search for column index containing string "Your Column Name" in the row 0 (which is first row of a worksheet
	    String sor_columnWanted = "SourceXPath";
	    Integer columnNo = null;
	    //output all not null values to the list
	    Row sor_firstRow = sheet.getRow(0);
	    
	    for(Cell cell:sor_firstRow)
	    {
	        if (cell.getStringCellValue().equals(sor_columnWanted))
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
	          sor_cells.add(c);
	       }
	    }
	   System.out.println(sor_cells);
	   System.out.println("Source XPath stored in List");
	    }else
	    {
	        System.out.println("could not find column " + sor_columnWanted + " in first row of " + fileIn.toString());
	    }
	    
	    
	    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	    
	    /*Read Source XPath Column from Excel File*/
	    String tar_columnWanted = "TargetXPath";
	    
	    //output all not null values to the list
	    Row tar_firstRow = sheet.getRow(0);
	    for(Cell cell:tar_firstRow)
	    {
	        if (cell.getStringCellValue().equals(tar_columnWanted))
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
	          tar_cells.add(c);
	       }
	    }
	   System.out.println(tar_cells);
	   System.out.println("Target XPath stored in List");
	    }else
	    {
	        System.out.println("could not find column " + tar_columnWanted + " in first row of " + fileIn.toString());
	    }
	    
	    
	    for (int i=1; i<=sor_cells.size()-1;i++)
	    {
		    Document INdoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputXMLFile));
		    		    
		    // locate the node(s) in source
		    XPath sor_xpath = XPathFactory.newInstance().newXPath();
		    sor_rtXPath = sor_cells.get(i).toString();
		    NodeList sor_nodes = (NodeList)sor_xpath.evaluate(sor_rtXPath, INdoc, XPathConstants.NODESET);
		    System.out.println("Identified Node:" +sor_cells.get(i)+sor_nodes.getLength());
		   	
		    /*================================================================================================================*/
		    
		    // read data at selected node
		    for (int sor_idx = 0; sor_idx < sor_nodes.getLength(); sor_idx++) 
		    	{
		    		SourceVal = sor_nodes.item(sor_idx).getTextContent();
		    		System.out.println("Data at" +sor_cells.get(i) +"=" + SourceVal);
		    		
				    Document OUTdoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(ResponseXMLFilePath));
				    // locate the node(s) in target
				    XPath tar_xpath = XPathFactory.newInstance().newXPath();
				    tar_rtXPath = tar_cells.get(i).toString();
				    NodeList tar_nodes = (NodeList)tar_xpath.evaluate(tar_rtXPath, OUTdoc, XPathConstants.NODESET);
				    
				    System.out.println("Identified Node:" +tar_cells.get(i)+tar_nodes.getLength());
				   	
				    /*================================================================================================================*/
				    
				    // read data at selected node
				    for (int idx = 0; idx < tar_nodes.getLength(); idx++) 
				    	{
				    		TargetVal = tar_nodes.item(idx).getTextContent();
				    		System.out.println("Data at" +tar_cells.get(i) +"=" + TargetVal);
				    	}
				    HSSFRow row = res_sheet.createRow((short)i);
				    row.createCell(0).setCellValue(sor_rtXPath);
		            row.createCell(1).setCellValue(SourceVal);
		    		row.createCell(2).setCellValue(tar_rtXPath);
		            row.createCell(3).setCellValue(TargetVal);
		                if(SourceVal!=null)
			            {
			            	if(TargetVal!=null)
			            	{	
			            		row.createCell(4).setCellFormula("EXACT(B"+(i+1)+",D"+(i+1)+")");
			            	}
			            	else
			            	{
			            		row.createCell(4).setCellValue("Target Value Not Found");
			            	}
			            }
			            else
			            {
			            	row.createCell(4).setCellValue("Source Value Not Found");
			            }
		            
		            FileOutputStream fileOut = new FileOutputStream(res_filename);
		            workbook.write(fileOut);
		            fileOut.close();
		         } 
		    
	    }
	    
	
	}
	
	
}
