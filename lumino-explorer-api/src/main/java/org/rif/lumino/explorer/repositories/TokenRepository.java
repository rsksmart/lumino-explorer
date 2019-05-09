package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {

    public Token findByTokenNetworkAddress(String networkAddress);

}
