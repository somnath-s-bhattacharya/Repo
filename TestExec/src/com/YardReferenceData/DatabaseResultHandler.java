package com.YardReferenceData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DatabaseResultHandler 
{
	public static String filewriter = null;
	public static void main(String[] args) {
		
		BufferedWriter writer = null;
		try
        {
        	String DatabaseResultSet ="Z-Resources/YardReferenceData/DB ResultSet/DatabaseResultSet.xml";
            writer = new BufferedWriter(new FileWriter(DatabaseResultSet));
            
		try {
			String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
			String URL = "jdbc:as400://LVQDB001.copart.com;naming=system;errors=full;date format=usa";
			
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,"xmluser", "modica");
			String query = "select GTGLFTYP, GTGLFDES, GTGLFCAT from MISPRDOBJ.GLTYPEGT where GTGLFCAT in ('PBT','DEF','XFR','AFL','BUY','DMV','PUR','AP','RA','AR','GEN','PA','PAA','FX','ADV','SFE','RH','BY','CSH','SAF','COG','SAL')";
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			filewriter = "<resultObject>";
			writer.write(filewriter);
			while (rs.next()) 
			{
				String GTGLFCAT = rs.getString("GTGLFCAT");
				String GTGLFTYP = rs.getString("GTGLFTYP");
				String GTGLFDES = rs.getString("GTGLFDES");
									
				filewriter	 =  "<"+GTGLFCAT+">"
								+ "\r"	+"<code>" + GTGLFTYP  +"</code>" +"\n" 	
								+ "\r"	+"<description>" + GTGLFDES  +"</description>"  + "\n"
								+ "\r"	+"<type>" + GTGLFCAT  +"</type>"  + "\n"
								+ "\r"  +"<url>" +"http://qa2.copart.com/Content/BackOffice/US/EN/Service-Order/"+GTGLFCAT+"-"+GTGLFTYP+".html"+"</url>"+"\n"
								+ "\r"	+ "</"+GTGLFCAT+">"	  + "\n"+"\r";					
								
				writer.write(filewriter);
		        
			}
			filewriter = "</resultObject>";
			writer.write(filewriter);
			statement.close();
			connection.close();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DatabaseResultSet file prepared");	
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
