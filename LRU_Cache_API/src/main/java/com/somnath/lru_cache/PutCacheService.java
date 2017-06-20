package com.somnath.lru_cache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/putcacheservice")
public class PutCacheService{
	
	@Path("{c}")
	@GET
	@Produces("application/json")
	public String put_into_cache(@PathParam("c") int c) { 
		CacheManager.getInstance().put_element(c);
		String response = CacheManager.getInstance().returnCacheState();
		return "{\n \"cache_response\": \"" + response.trim()  + "\"\n}";
	}
	
}