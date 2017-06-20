package com.somnath.lru_cache;


public class CacheManager {
	
	private static LRUCache cache = new LRUCache(4);
	
	private CacheManager() { }
	
	public static LRUCache getInstance() {
		return cache;	
	}
}
