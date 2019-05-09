Steps to make the configuration of the database for the lumino-explorer-api project

1 - Install the latest server version of mongodb (https://docs.mongodb.com/manual/installation)
2 - Execute the following command by console:
	a) When the mongodb server is installed on the same machine that runs the script:
		
		user:~/mongo  lumino-explorer-api-database-setup.js
		
	b) When the mongo server is installed on another machine and requires authentication
		
		user:~/mongo --host <hostname> -u <username> -p <password>  lumino-explorer-api-database-setup.js
		
		Example:
		user:~/mongo --host 10.10.7.161:27017 -u mongodb -p mongodb  lumino-explorer-api-database-setup.js
		

Execution example:

MongoDB shell version v3.6.3
connecting to: mongodb://127.0.0.1:27017
MongoDB server version: 3.6.3
Switched to db lumino_explorer
Creating a new collection with name event_job_metadata
WriteResult({ "nInserted" : 1 })
New  database with name lumino_explorer is created succesfully
The collections into database are:
event_job_metadata
The count of elements into my event_job_metadata is :
1
All is done, Now you can run the lumino-explorer-api
Bye
		