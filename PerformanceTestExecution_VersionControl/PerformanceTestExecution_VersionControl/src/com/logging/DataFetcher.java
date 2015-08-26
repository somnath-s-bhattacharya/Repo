package com.logging;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.lightcouch.CouchDbClient;

public class DataFetcher {

	public Map getData(String db, String region,String view, Map<String,String> searches)
	{
		Map retval=null;
		Properties props=new Properties();
		
		try{
			FileReader fr=new FileReader("couchdb_log.properties");
			props.load(fr);
		}catch(Exception e){e.printStackTrace();}
		
		CouchDbClient client = new CouchDbClient(db, true, props.getProperty("couchdb.protocol"), props.getProperty("couchdb.host")
				, Integer.parseInt(props.getProperty("couchdb.port")), props.getProperty("couchdb.username"), props.getProperty("couchdb.password"));
		List<Map> records=client.view(view).key(region).query(Map.class);
		if(searches==null)
			return records.get(0);
		Set<String> skeys=searches.keySet(); 
		outer: for(Map recordWrap:records)
		{
			Map record=(Map)recordWrap.get("value");
			
			for(String skey:skeys)
			{
				if(record.containsKey(skey))
				{
					System.out.println(record.get(skey) + searches.get(skey) + skey);
					if(record.get(skey).equals(searches.get(skey)))
						continue;
					else
						continue outer;
				}
				else
					continue outer;
				
			}
			return record;
			
		}
		
		
		return retval;
	}
	
	
	
	public List<Map> getMultiData(String db, String keyNmae,String view, Map<String,String> searches)
	{
		List<Map> retval=new ArrayList<Map>();
		Properties props=new Properties();
		
		try{
			FileReader fr=new FileReader("src/test/resources/ProductLaunch/couchdb_config.properties");
			props.load(fr);
		}catch(Exception e){e.printStackTrace();}
		
		CouchDbClient client = new CouchDbClient(db, true, props.getProperty("couchdb.protocol"), props.getProperty("couchdb.host")
				, Integer.parseInt(props.getProperty("couchdb.port")), props.getProperty("couchdb.username"), props.getProperty("couchdb.password"));
		List<Map> records=client.view(view).key(keyNmae).query(Map.class);
		if(searches==null)
			return records;
		
		Set<String> skeys=searches.keySet(); 
		outer: for(Map recordWrap:records)
		{
			Map record=(Map)recordWrap.get("value");
			
			for(String skey:skeys)
			{
				if(record.containsKey(skey))
				{
					if(record.get(skey).equals(searches.get(skey)))
						continue;
					else
						continue outer;
				}
				else
					continue outer;
				
			}
			retval.add(record);
			
		}
		
		return retval;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*CouchDbClient addressClient = new CouchDbClient("ocp_executions", true, "http", "127.0.0.1", 5984, "admin", "admin");
		List<Map> addresses=addressClient.view("ocp/exec_region").query(Map.class);
		//System.out.println(addresses);
		for(Map record:addresses)
		{
			//System.out.println(record);
			System.out.println("key="+record.get("key"));
			System.out.println("val="+record.get("value"));
		}
		*/
		DataFetcher df=new DataFetcher();
		Map execQuery=new HashMap();
		execQuery.put("ExecuteScript", "Yes");
		System.out.println(df.getData("ocp_executions", "US","ocp/exec_region",execQuery));
	}

}