package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<Feed, String> {

}
