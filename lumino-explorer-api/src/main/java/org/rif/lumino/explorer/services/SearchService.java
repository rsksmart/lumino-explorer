package org.rif.lumino.explorer.services;

import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.dto.SearchResultDTO;
import org.rif.lumino.explorer.models.dto.TokenDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
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
public class SearchService {

  private static final String FIELD_ID = "_id";
  private static final String FIELD_TOKEN_NETWORK_ADDRESS = "tokenNetworkAddress";

  @Autowired private MongoTemplate mongoTemplate;
  @Autowired private CommonService commonService;

  public SearchResultDTO search(String queryParam) {

    Pattern pattern = Pattern.compile(queryParam, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    SearchResultDTO searchResultDTO = new SearchResultDTO();
    searchResultDTO.setChannels(searchInChannelDocument(pattern));
    searchResultDTO.setNodes(searchInLuminoNodeDocument(pattern));
    searchResultDTO.setTokens(searchInTokenDocument(pattern));

    return searchResultDTO;
  }

  private List<LuminoNodeDTO> searchInLuminoNodeDocument(Pattern pattern) {

    Query query = new Query();
    query.addCriteria(
        new Criteria()
            .orOperator(
                new Criteria().where(FIELD_ID).regex(pattern),
                new Criteria().where("nodeEndpoint").regex(pattern),
                new Criteria().where("rnsAddress").regex(pattern),
                new Criteria().where("nodeChannelsIds").regex(pattern)));

    List<LuminoNode> luminoNodes = mongoTemplate.find(query, LuminoNode.class, "lumino_node");
    List<LuminoNodeDTO> luminoNodeDTOS = commonService.mapLuminoNodesDTO(luminoNodes);

    return luminoNodeDTOS;
  }

  private List<ChannelDTO> searchInChannelDocument(Pattern pattern) {

    Query query = new Query();
    query.addCriteria(
        new Criteria()
            .orOperator(
                new Criteria().where(FIELD_ID).regex(pattern),
                new Criteria().where(FIELD_TOKEN_NETWORK_ADDRESS).regex(pattern),
                new Criteria().where("participantOneAddress").regex(pattern),
                new Criteria().where("participantTwoAddress").regex(pattern))
            .and("channelState").is(ChannelState.Opened));

    List<Channel> channels = mongoTemplate.find(query, Channel.class, "channel");

    List<ChannelDTO> channelDTOS = commonService.mapChannelsDTO(channels);

    return channelDTOS;
  }

  private List<TokenDTO> searchInTokenDocument(Pattern pattern) {

    Query query = new Query();
    query.addCriteria(
        new Criteria()
            .orOperator(
                new Criteria().where(FIELD_ID).regex(pattern),
                new Criteria().where(FIELD_TOKEN_NETWORK_ADDRESS).regex(pattern),
                new Criteria().where("name").regex(pattern),
                new Criteria().where("symbol").regex(pattern),
                new Criteria().where("decimals").regex(pattern)));

    List<Token> tokens = mongoTemplate.find(query, Token.class, "token");
    List<TokenDTO> tokenDTOS = commonService.mapTokensDTO(tokens);

    return tokenDTOS;
  }
}
