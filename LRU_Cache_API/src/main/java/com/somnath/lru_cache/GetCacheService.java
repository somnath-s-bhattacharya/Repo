package com.somnath.lru_cache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/getcacheservice")
public class GetCacheService{
	
	
	@Path("{c}")
	@GET
	@Produces("application/json")
	public String get_from_cache(@PathParam("c") int c) {
		CacheManager.getInstance().get_element(c);	
		String response = CacheManager.getInstance().returnCacheState();
		return "{\n \"cache_response\": \"" + response.trim()  + "\"\n}";
	}
}