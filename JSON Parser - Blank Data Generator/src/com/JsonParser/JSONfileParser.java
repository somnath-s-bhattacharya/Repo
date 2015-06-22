package com.JsonParser;


import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
/**
 * @author Somnath Bhattacharjee
 */
 
public class JSONfileParser {
 
    @SuppressWarnings("unchecked")
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
            Object obj = parser.parse(new FileReader("D://Somnath//Workspace//JSON Parser - Blank Data Generator//Z-Resources//sample.txt"));
            JSONObject jsonObject = (JSONObject) obj;
 
            //String name = (String) jsonObject.get("assignmentType");
            //String author = (String) jsonObject.get("copartCompanyCode");
            JSONArray vehicleInfo = (JSONArray) jsonObject.get("");
 
//            System.out.println("assignmentType: " + name);
//            System.out.println("copartCompanyCode: " + author);
//            System.out.println("\nCompany List:");
            Iterator<String> iterator = vehicleInfo.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}