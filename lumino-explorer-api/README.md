# lumino-explorer-api

1. Install latest mongodb server version: https://docs.mongodb.com/manual/installation.            
2. We recommend install Studio 3T for explore mongodb databases.
2. Import proyect as maven proyect (File > New > Proyect from existing sources > Then select the .pom file
3. Run test CreateJobMetadata (This is for create a first document of metadata event job)
4. Add a new Maven run configuration with the command line: spring-org.rif.lumino.explorer.boot:run
5. Go to the application.properties and set the lumino.contract.tokenNetworkRegistry property, set the value with your Token Network registry. The token network registry is generated when you deployed smart contracts in lumino.
6. Go to Studio 3T, open event_job_metadata document and set in zero the following fields: lastSyncBlockChannels, lastSyncBlockChannels
5. Run it
7. If you have a SocketTimeOutException, try run postman get rsk logs first 
8. If you want run project in debug mode, then go to Application.java do rigth click and select Debug, this allows you to debug the application.
9. Open localhost:8080/poc for test an endpoint

# Code style 

This project uses Googl Java style. You can download it from the plugin center of IntelliJ and bind the format action to Ctrl + S

https://github.com/google/google-java-format. 



# Using web3j to generate smart contract wrappers

In order to interact with contracts, a java wrapper was created, based on the ABI: 


```
marcos@marcos-rsk:~/rsk/lumino-explorer-api/web3j-4.2.0/bin$ ./web3j solidity generate -a=/home/marcos/rsk/lumino-explorer-api/src/main/resources/contracts/TokenNetwork.abi -o=/home/marcos/rsk/lumino-explorer-api/src/main/java/contracts -p="generated"

              _      _____ _     _        
             | |    |____ (_)   (_)       
__      _____| |__      / /_     _   ___  
\ \ /\ / / _ \ '_ \     \ \ |   | | / _ \ 
 \ V  V /  __/ |_) |.___/ / | _ | || (_) |
  \_/\_/ \___|_.__/ \____/| |(_)|_| \___/ 
                         _/ |             
                        |__/              

Generating generated.TokenNetwork ... File written to /home/marcos/rsk/lumino-explorer-api/src/main/java/contracts

```




# Using web3j to generate accounts

To interact with contracts an account was needed, the account was generated using web3j binaries: 

```
marcos@marcos-rsk:~/rsk/lumino-explorer-api/web3j-4.2.0/bin$ ./web3j wallet create

              _      _____ _     _        
             | |    |____ (_)   (_)       
__      _____| |__      / /_     _   ___  
\ \ /\ / / _ \ '_ \     \ \ |   | | / _ \ 
 \ V  V /  __/ |_) |.___/ / | _ | || (_) |
  \_/\_/ \___|_.__/ \____/| |(_)|_| \___/ 
                         _/ |             
                        |__/              

Please enter a wallet file password: 
Please re-enter the password: 
Please enter a destination directory location [/home/marcos/.ethereum/testnet/keystore]: /home/marcos/rsk/lumino-explorer-api/src/main/resources
Wallet file UTC--2019-04-19T15-07-00.568000000Z--034000b5f2862d114e4b3474f79fc64aad0cb742.json successfully created in: /home/marcos/rsk/lumino-explorer-api/src/main/resources
```