## Lumino Hub Connection
This resource holds the connection count of the
trusted Hub nodes.


### Endpoints   
#### ```POST /luminoHubConnection ```  
This endpoint adds a new connection to the hub 
with the minimum number of connections, increases the count by 1 
and returns the Hub's url.

- **returns:**
   * **200 OK**: If there is at least on Hub with available connections.  
   Body:  
   ``` { "url": "Hub's url" } ```
   * **404 NOT FOUND**: If there isn't any registered Hub.
   * **409 CONFLICT**: If there isn't a Hub with available connections. 