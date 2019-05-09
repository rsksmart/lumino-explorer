databaseName = "lumino_explorer";
db = db.getSiblingDB(databaseName); //Switch databases, run the use statement shell. If the database doesn't already exist, it will be created
print('Switched to db ' + databaseName);
print('Creating a new collection with name event_job_metadata'); //After create colletion and insert data in is then a database is created
print(db.event_job_metadata.insert({ _id: "1",  lastSyncBlockChannels:"0", lastSyncBlockTokens: "0", periodOfSchedule: "500", _class: "org.rif.lumino.explorer.models.documents.EventJobMetadata"}));
print("New  database with name" + databaseName + "is created succesfully");
print("The collections into database are:");
print(db.getCollectionNames()); // prints all collection names in respective database
print("The count of elements into my event_job_metadata is :");
print(db.event_job_metadata.count()); // prints the count of event_job_metadata collection.
print("All is done, Now you can run the  lumino-explorer-api");
print("Bye");