databaseName = "lumino_explorer";
db = db.getSiblingDB(databaseName);
print('Deleting any existing database');
db.dropDatabase();
print('Creating collections');
db.event_job_metadata.insert({ _id: "1",  lastSyncBlockChannels:"0", lastSyncBlockTokens: "0", periodOfSchedule: "500", _class: "org.rif.lumino.explorer.models.documents.EventJobMetadata"});
collectionNames = db.getCollectionNames();
if (collectionNames.indexOf("event_job_metadata") === -1) {
    print("An error has occurred trying to create lumino-explorer-api database: event_job_metadata was not created");
}
eventJobMetadataCount = db.event_job_metadata.count();
if (eventJobMetadataCount === 1) {
    print("All is done, Now you can run the  lumino-explorer-api");
} else {
    print("An error has occurred trying to create lumino-explorer-api database: event_job_metadata has not a correct count number");
}