package org.rif.lumino.explorer.services.blockchain;

import org.rif.lumino.explorer.generated.contracts.TokenNetworkRegistry;
import org.rif.lumino.explorer.events.LuminoEventRetriever;
import org.rif.lumino.explorer.models.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.rif.lumino.explorer.services.blockchain.rskaccount.RskAccountService;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@Component
public class TokenNetworkRegistryService {

  @Value("${lumino.contract.tokenNetworkRegistry}")
  private String tokenNetworkRegistryAddress;

  @Value("${rsk.blockchain.endpoint}")
  private String rskBlockchainEndpoint;

  @Value("${lumino.contracts.tokenNetworkCreatedEvent}")
  private String tokenNetworkCreatedEvent;

  @Autowired RskAccountService rskAccountService;
  private LuminoEventRetriever tokenNetworkCreatedRetriever;

  private TokenNetworkRegistry tokenNetworkRegistry;

  @PostConstruct
  public void init() {

    Web3j web3j = Web3j.build(new HttpService(rskBlockchainEndpoint));

    tokenNetworkRegistry =
        TokenNetworkRegistry.load(
            tokenNetworkRegistryAddress,
            web3j,
            rskAccountService.getRskAccountCredentials(),
            new BigInteger("1"),
            new BigInteger("1"));

    tokenNetworkCreatedRetriever =
        new LuminoEventRetriever(
            tokenNetworkCreatedEvent,
            Arrays.asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}),
            rskBlockchainEndpoint);
  }

  public String getTokenNetworkAddress(String tokenAddress) throws Exception {
    return tokenNetworkRegistry.token_to_token_networks(tokenAddress).send();
  }

  public List<EventData> getTokenRegisteredEvents(
      BigInteger from, BigInteger to, String contractAddress) throws Exception {
    List<EventData> registeredTokens =
        tokenNetworkCreatedRetriever.getLogs(from, to, contractAddress);
    if (!registeredTokens.isEmpty()) {
      registeredTokens.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    }
    return registeredTokens;
  }
}
