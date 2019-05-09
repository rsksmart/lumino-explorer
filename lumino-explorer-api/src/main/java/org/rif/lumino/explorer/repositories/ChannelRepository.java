package org.rif.lumino.explorer.repositories;

import org.rif.lumino.explorer.models.documents.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends MongoRepository<Channel, String> {

  Optional<Channel> findByIdAndTokenNetworkAddress(String id, String tokenAddress);
  List<Channel> findByChannelState(String channelState);
  List<Channel> findByTokenNetworkAddressAndChannelState(String tokenNetworkAddress, String channelState);
  List<Channel> findByTokenNetworkAddress(String tokenNetworkAddress);
}
