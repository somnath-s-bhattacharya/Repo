package com.somnath.lru_test;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.somnath.lru_cache.CacheManager;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LruTest {
	
	@Test
	public void get_api_test(){
		String url = "http://localhost:8080/LRU_Cache_API-0.0.1-SNAPSHOT/getcacheservice/";
		Client client = Client.create();
		WebResource resource = client.resource(url+ 35);
		ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus()!=200)
		{
			throw new RuntimeException("Request Failed");
		}	
		String output = response.getEntity(String.class);
		JSONObject json = new JSONObject(output);
		System.out.println(CacheManager.getInstance().returnCacheState());
	}

	@Test
	public void post_api_test() throws InterruptedException, ClientProtocolException, IOException, JSONException
	{
		String url = "http://localhost:8080/LRU_Cache_API-0.0.1-SNAPSHOT/putcacheservice/";
		Client client = Client.create();
		WebResource resource = client.resource(url +5);
		ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus()!=200)
		{
			throw new RuntimeException("Request Failed");
		}	
		String output = response.getEntity(String.class);
		JSONObject json = new JSONObject(output);
		Assert.assertEquals(json.get("cache_response"), "5");
	}

	@Test
	public void lru_cache_get_element_test()  
	{
		CacheManager.getInstance().get_element(4);
		Assert.assertEquals(" ", CacheManager.getInstance().returnCacheState());
	}

	@Test
	public void lru_cache_put_element_test()
	{
		String expected_result = "4 3 2 1";
		CacheManager.getInstance().put_element(1);
		CacheManager.getInstance().put_element(2);
		CacheManager.getInstance().put_element(3);
		CacheManager.getInstance().put_element(4);
		Assert.assertEquals(CacheManager.getInstance().returnCacheState().trim().replace(" ", ""), expected_result.replace(" ", ""));
		
	}
	
	@Test
	public void lru_end_to_end_test()
	{
		String expected_result = "5 90 78 16";
		CacheManager.getInstance().put_element(5);
		CacheManager.getInstance().put_element(16);
		CacheManager.getInstance().put_element(78);
		CacheManager.getInstance().put_element(90);
		CacheManager.getInstance().get_element(5);
		Assert.assertEquals(CacheManager.getInstance().returnCacheState().trim().replace(" ", ""), expected_result.replace(" ", ""));
	}
	
	@AfterTest
	public void teardown(){
		CacheManager.getInstance().emptycache();
	}

}
