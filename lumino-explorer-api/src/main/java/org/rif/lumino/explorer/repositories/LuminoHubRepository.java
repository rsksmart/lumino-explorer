package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface LuminoHubRepository extends MongoRepository<LuminoHub, String> {

    Optional<LuminoHub> findFirstByOrderByConnectionCount();

    Optional<LuminoHub> findFirstByMaxConnectionsAllowedEqualsOrderByConnectionCount(long maxConnectionsAllowed);

    Optional<LuminoHub> findByInfiniteCapacityEqualsOrderByConnectionCount(boolean infiniteCapacity);

    Optional<LuminoHub> findByConnectedClientsContains(String address);

    Optional<LuminoHub> findByUrl(String url);
}
