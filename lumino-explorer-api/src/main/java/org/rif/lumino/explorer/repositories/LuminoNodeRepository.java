package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LuminoNodeRepository extends MongoRepository<LuminoNode, String> {

    public LuminoNode findByNodeAddress(String nodeAddress);
}
