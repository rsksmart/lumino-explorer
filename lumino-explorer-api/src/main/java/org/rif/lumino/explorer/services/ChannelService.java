package org.rif.lumino.explorer.services;

import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Component
public class ChannelService {

  private static final String CHANNEL_STATE_FIELD = "channelState";

  @Autowired private MongoTemplate mongoTemplate;
  @Autowired private CommonService commonService;

  public List<ChannelDTO> getChannelsByLuminoNodeAndState(String nodeAddress, String channelState) {

    Pattern pattern = Pattern.compile(nodeAddress, Pattern.CASE_INSENSITIVE);

    Query query = new Query();
    query.addCriteria(
        new Criteria()
            .orOperator(
                new Criteria().where("participantOneAddress").regex(pattern).and(CHANNEL_STATE_FIELD).is(channelState),
                new Criteria().where("participantTwoAddress").regex(pattern).and(CHANNEL_STATE_FIELD).is(channelState)));

    List<Channel> channels = mongoTemplate.find(query, Channel.class, "channel");
    List<ChannelDTO> channelDTOS = commonService.mapChannelsDTO(channels);

    return channelDTOS;
  }
}
