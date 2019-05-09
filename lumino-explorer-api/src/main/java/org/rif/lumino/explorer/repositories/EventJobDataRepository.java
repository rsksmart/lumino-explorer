package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.EventJobMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventJobDataRepository extends MongoRepository<EventJobMetadata, String> {}
