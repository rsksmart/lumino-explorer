# RIF Lumino Explorer API

![Lumino Network](Lumino.png?raw=true "RIF Lumino Network")


## Pre requisites

1. Access to a synched RSK node. You can do this in a variety of ways:
   * Run your own node on TestNet or MainNet, see [https://github.com/rsksmart/rskj/wiki/Install-RskJ-and-join-the-RSK-Orchid-Mainnet-Beta]()
   * Compile and run a RSK node locally, see [https://github.com/rsksmart/rskj/wiki/Compile-and-run-a-RSK-node-locally]()
2. Mongo Database Server (Version 3.6.x)
3. Java 8 (Lastest Release)
4. Maven (Version 3.6.x)


## Build RIF Lumino Explorer API from code

1. Get the code by cloning this repo
2. Go to the path you downloaded or cloned Lumino Explorer API code (lets call this path 
   `$RIF_LUMINO_EXPLORER_API_PATH`)
3. Go to the `$RIF_LUMINO_EXPLORER_API_PATH/src/main/resources/application.properties` 
   file and set the `spring.profiles.active` to the profile of your preference. For Mainnet leave it as-is.
4. Now edit the properties file of your selected profile (Ex: if we use the profile called `dev`,
   the file to edit is `$RIF_LUMINO_EXPLORER_API_PATH/src/main/resources/application-dev.properties`).
5. Set the `lumino.contract.tokenNetworkRegistry` property on that file. 
   The value with your Lumino Token Network Registry.
   - For TestNet that property has to be like this: `lumino.contract.tokenNetworkRegistry=0x47E5b7d85Da2004781FeD64aeEe414eA9CdC4f17`
   - For MainNet that property has to be like this: `lumino.contract.tokenNetworkRegistry=0x060B81E90894E1F38A625C186CB1F4f9dD86A2B5`
6. Go to `$RIF_LUMINO_EXPLORER_API_PATH` again and install project dependencies with the following command:

``` mvn install```

## Set Up Mongo Database

1. Execute the mongo database setup script, to do this you have 2 options:
   - If you are working on your local machine, you have to run:
     - ```mongo $RIF_LUMINO_EXPLORER_API_PATH/src/main/resources/database/lumino-explorer-api-database-setup.js```
   - Otherwise, you have to specify a host, port and authentication credentials:
     - ```mongo --host <hostname> -u <username> -p <password> $RIF_LUMINO_EXPLORER_API_PATH/src/main/resources/database/lumino-explorer-api-database-setup.js```

2. After running the mongo script you will see a lot of script messages, at the end it should appear `All is done, Now you can run the lumino-explorer-api`.

## Reinstalling the Project
If you have an already installed project version, you need to this steps to update it:

1. Stop the API .
2. Git pull from the branch you want to update (ex: git pull origin master).
3. Maven install to update the libraries.
4. Setup Mongo Database (this is to reset the db).
5. Start the API again.

## Start your RIF Lumino Explorer API

### Preconditions
Lumino Explorer API uses `eth_getLogs` rpc to get the information about events, therefore the RSK node must respond in a reasonable 
timeframe (< 30s)

Since the `eth_getLogs` result is cached, it will take a long time for this call to finish the first time it is executed after the RSK node is started. This will happen each time the RSK node boots.
After this, each call should be finished in a reasonable time.

Use this curl to test the `eth_getLogs` response:
```
curl -X POST http://localhost:4444 -H 'Content-Type: application/json' -d '{"jsonrpc":"2.0","method":"eth_getLogs","params":[{"address":"0xde2D53e8d0E673A4b1D9054cE83091777F2fd8Ce","fromBlock":"0x0","toBlock":"latest"}],"id":74}'
```

### Start the application
1. Go to `$RIF_LUMINO_EXPLORER_API_PATH`
2. Run the following command:

```
 mvn spring-boot:run
```

 3. Now you can check if api is running, going to `http://localhost:8080/api/v1/dashboard`. http://localhost:8080 is the default host and port if you run it locally.

## Trusted Hub nodes
In order to set up the load balancing endpoint, you first need to configure a list of trusted Hub Nodes.

1. You have to edit your profile properties file (Ex: `$RIF_LUMINO_EXPLORER_API_PATH/src/main/resources/application-dev.properties`):
- To make the explorer api start with these hub nodes you have to set the property `lumino.explorer.hub.useDefaults` to `true`. If false
  the explorer will start without trusted hubs.
- Add the following properties for each trusted hub that you want to add:
  ```yaml
  lumino.explorer.hub.url.$id=$url
  lumino.explorer.hub.infiniteCapacity.$id=$infiniteCapacity
  lumino.explorer.hub.maxConnections.$id=$maxConnections
  ```
  Where the values:
  * **$id**: it's an arbitrary identifier for the hub node. 
  * **$url**: it's the url for the hub node to be exposed to the clients.
  * **$infiniteCapacity**: it's the boolean flag that says if a hub has or not infinite capacity.
  * **$maxConnections**: it's the maximum of allowed connections on that hub node.
    

   
## API Documentation
* [REST API Documentation](docs/api/v1/index.md)

## Additional help

The following sections are created using an Ubuntu 18.04.2 LTS

### Install Maven

(source: [https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/))

Start by updating the package index:

```$ sudo apt update ```

Next, install Maven by typing the following command:

```$ sudo apt install maven```

Verify the installation by running the `mvn -version` command:

```
$ mvn -version
```
The output should look something like this:

```
output
Apache Maven $MAVEN_VERSION
Maven home: $YOUR_MAVEN_HOME
Java version: $JAVA_VERSION
Java home: $JAVA_HOME
Default locale: $YOUR_LOCALE
OS name: $YOUR_OS_VERSION
```
## Useful Links
* [RIF Lumino Network documentation](https://www.rifos.org/rif-lumino-network/)
* [http://explorer.lumino.rifos.org/]()
* [RIF Lumino Contracts](https://github.com/rsksmart/lumino-contracts) 
* [RIF Lumino Web](https://github.com/rsksmart/lumino-web) 
* [RIF Lumino Network](https://github.com/rsksmart/lumino) 
