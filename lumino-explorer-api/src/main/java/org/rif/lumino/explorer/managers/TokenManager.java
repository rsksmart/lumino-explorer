package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.EventData;
import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.repositories.TokenRepository;
import org.rif.lumino.explorer.services.CommonService;
import org.rif.lumino.explorer.services.blockchain.HumanStandardTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class TokenManager {

  @Autowired private TokenRepository tokenRepository;
  @Autowired private HumanStandardTokenService humanStandardTokenService;
  @Autowired private FeedRepository feedRepository;
  @Autowired private CommonService commonService;

  public void processRegisterTokenEvent(EventData event) {
    Token token =
        new Token(
            event.getValues().get(0).toString(),
            event.getValues().get(1).toString(),
            null,
            null,
            null);

    Map<String, String> data = new HashMap<String, String>();
    humanStandardTokenService.setTokenInfo(token);

    data.put("token_name", token.getName());
    data.put("token_symbol", token.getSymbol());
    data.put("token_decimals", token.getDecimals().toString());

    Feed feed = commonService.mapNewFeed(FeedType.TOKEN, data);

    feedRepository.save(feed);
    tokenRepository.save(token);
  }

  public void processRegisterTokenEvents(List<EventData> events) {
    for (EventData eventData : events) {
      processRegisterTokenEvent(eventData);
    }
  }

  public List<Token> getAll() {
    return tokenRepository.findAll();
  }


  public Token getTokenByNetworkAddress(String networkAddress) {

    return tokenRepository.findByTokenNetworkAddress(networkAddress);
  }

}
