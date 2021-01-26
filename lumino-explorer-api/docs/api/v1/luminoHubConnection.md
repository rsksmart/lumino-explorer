## Lumino Hub Connection
This resource manages the available hubs to connect to.
 
It also acts as a load balancing endpoint, providing the API 
Client with the url of the most appropriate Hub to join to.

### Endpoints   

- **POST**: `/api/v1/luminoHubConnection/register_client/{address}`: register a LC on one of the available hubs, the one with less conected clients or otherwise it shows an error in case no hub available is found.
- **POST**: `/api/v1/luminoHubConnection/unregister_client/{address}`: this removes a LC from the hub connection list if any has that address, otherwise it throws an error.
- **POST**: `/api/v1/luminoHubConnection?url=http://localhost:5000&maxConnectionsAllowed=1&infiniteCapacity=false`: it creates a hub with the url as http://localhost:5000 and a max of available connections of 1.
- **PUT**: `/api/v1/luminoHubConnection/{id}?newUrl=http://localhost:5001&newMaxConnectionsAllowed=2&newInfiniteCapacity=true`: this updates a hub with the new parameters, the parameters are optional but at least one has to be specified to be changed. The {id} is a path param referencing the id of the existent hub.
- **GET**: `/api/v1/luminoHubConnection`: get all the available hubs in the database
- **GET**: `/api/v1/luminoHubConnection/{id}`: get a hub with id = {id}
- **DELETE**: `/api/v1/luminoHubConnection/{id}`: it deletes the hub with id = {id}

### Some return codes
   * **200 OK**: If there is at least on Hub with available connections.
   * **404 NOT FOUND**: If there isn't any registered Hub in database.
   * **409 CONFLICT**: If there isn't a Hub with available connections. 