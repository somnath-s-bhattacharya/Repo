# LRU Cache Service Documentation

## Assumptions taken:

1. Data type to be stored in the cache in integer.

2. Since this is a server side service so I have assumed that cache size would be limited and is thus hard coded at 4(128 bits)

(This hard coded value is provided at the CacheManager.java)

3. Have not exposed the emptycache() method which resets the cache. However this is used as an internal method to facilitate testing.

4. Since this service needs to be thread safe so have used ConcurrentHashMap for referencing to the Doubly Linked List

## LRU Algorithm Implemented:

1. put_element Method Algorithm
   
   Step : Check if element exists in cache
           
           Step 1a: Yes - Move element to the head of the cache 
           
           Step 1b: No - 
                    
                    Step 1b.1 - Check if cache is full
                    
                    Step 1b.2 - Remove element from tail and move all elements to next node
           
           Step 1c: Add element to head and add reference to concurrenthashmap.
 
2. get_element Method Algorithm
   
   Step : Check if element exists in cache
           
           Step 1a: Yes - Move element to the head of the cache 

3. returnCacheState method
   
   Step : Iterate over current cache and append data to stringbuilder object and return stringbuilder object.


## Time Complexity:

1. Insert to Doubly Linked List
   
   Generally the time complexity of insertion to a Doubly Linked is O(n). But here since we are inserting only to the head
   the time complexity becomes O(1).

2. Remove from Doubly Linked List
   
   Generally the time complexity of removal from a Doubly Linked is O(n). But here since we are removing only from the tail
   the time complexity becomes O(1).

3. Search in ConcurrentHashMap
   
   The time complexity of search from a Map via key is O(1).

## API Design:

Due to lack of time, I created 2 GET methods which accept path parameters to expose the get_element and put_element methods.

Have used jersey-bundle dependency to create the exposed resources. To keep a static instance of the LRU_Cache class,

have used a singleton CacheManager class to expose the methods via the Get resources.

Unit and API test:

1. Have created 3 unit tests for testing the LRU_Cache class.

   Have used the CacheManager class here to test the methods.

2. Have created 2 api tests using jersey client to assert reponse payloads.

3. Have not been able to write multi-threaded tests for the api resources. 
   
   Please find my other project which has multi-threading implementation at [API Performance Test Tool with Multi-Threading Implementation](https://github.com/somnath-s-bhattacharya/Repo/tree/master/PerformanceTestExecution_VersionControl)
   
## Deployment and Usage

Deploy [war file](https://github.com/somnath-s-bhattacharya/Repo/blob/master/LRU_Cache_API/target/LRU_Cache_API-0.0.1-SNAPSHOT.war) on Tomcat 7 and use any rest client extensions to access the api resources at:
1. http://localhost:8080/LRU_Cache_API-0.0.1-SNAPSHOT/getcacheservice/{param}
2. http://localhost:8080/LRU_Cache_API-0.0.1-SNAPSHOT/putcacheservice/{param}
