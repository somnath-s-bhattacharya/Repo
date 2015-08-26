package com.logging;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;



public class CouchLogger {

	public static final int testdata=1;
	public static final int validation=2;
	public static final int debug=3;
	public static final int info=4;
	public static final int error=5;
	private static String _id="";
	private static String _rev="";
	private static DataFetcher df=new DataFetcher();
	private static String logFilter="12";
	
	
	public static Map<String,Object> logdata;
	
	public static int defaultLevel=4;
	
	public static CouchDbClient client;
	
	
	static{
		try{
			File pfile=new File("couchdb_log.properties");
			if(pfile.exists())
			{
				Properties props=new Properties();
				FileReader fr=new FileReader(pfile);
				props.load(fr);
				
				client=new CouchDbClient(props.getProperty("couchdb.name"), Boolean.parseBoolean(props.getProperty("couchdb.createdb.if-not-exist")),
						props.getProperty("couchdb.protocol"), props.getProperty("couchdb.host"), 
						Integer.parseInt(props.getProperty("couchdb.port")), props.getProperty("couchdb.username"), props.getProperty("couchdb.password"));
				
				if(props.containsKey("defaultLogLevel"))
				{
					String defaultLvlString=props.getProperty("defaultLogLevel");
					//System.out.println("Default Log Level:"+defaultLvlString);
				
				if(defaultLvlString.equalsIgnoreCase("debug"))
					logFilter="12345";//All
				if(defaultLvlString.equalsIgnoreCase("info"))
					logFilter="1245";//validation info testdata error
				if(defaultLvlString.equalsIgnoreCase("error"))
					logFilter="125";//validation testdata error
				}
				
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void info(Object key, Object val, String page)
	{
		int level=4;
		log(key,val,level,page);
	}
	public static void error(Object key, Object val, String page)
	{
		int level=5;
		log(key,val,level,page);
	}
	public static void debug(Object key, Object val, String page)
	{
		int level=3;
		log(key,val,level,page);
	}
	public static void testData(Object key, Object val, String page)
	{
		int level=1;
		log(key,val,level,page);
	}
	
	public static void validation(Object key, Object val, String page)
	{
		int level=2;
		log(key,val,level,page);
	}
	
	public static void log(Object key, Object val, int level, String page)
	{
		if(logFilter.contains(defaultLevel+""))
		{
			if(_id==null||_id.length()==0)
			{
				logdata=new HashMap<String, Object>();
			}
			else
			{
				logdata=df.getData("performancetest_logs", _id, "performancetest/logs_id", null);
				logdata=(Map)logdata.get("value");
				//System.out.println("GOT>>"+logdata+"<<");
				if(logdata.containsKey("_id"))
					_id=logdata.get("_id").toString();
				if(logdata.containsKey("_rev"))
					_rev=logdata.get("_rev").toString();
			}
			//
			if(logdata.containsKey(page))
			{
				if(logdata.get(page) instanceof Map)
				{
					((Map)logdata.get(page)).put(key, val);
					
				}
				else
				{
					System.out.println("Problem with logs, got "+page+" as another value other than Map");
				}
			}
			else
			{
			//logdata.put(key, val);
				Map tmpMap=new HashMap();
				tmpMap.put(key, val);
				logdata.put(page, tmpMap);
			}
			
			Response res=null;
			System.out.println(">ID:"+_id+", REV:"+_rev+"<");
			if(_id==null||_id.length()==0)
				{
				res=client.save(logdata);
				_id=res.getId();
				}
			else
			{
				
				System.out.println(logdata);
				res=client.update(logdata);
				_rev=res.getRev();
			}
			//System.out.println(">>>>>>>>>Logging done"+res);
		}
	}
	
	public static void couchLog(int type, Map info, String page)// for test data
	{
		Set keys=info.keySet();
		for(Object key:keys)
		{
			Object val=info.get(key);
			log(key.toString(), val.toString(), type, page);
		}
	}
	
	public static void couchLog(int type, Object key, Object val, String page)
	{
		log(key, val, type, page);
	}
	
	public static void save()
	{

		_id="";
	}
}