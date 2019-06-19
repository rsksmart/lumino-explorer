package org.rif.lumino.explorer.services.blockchain;

import org.rif.lumino.explorer.events.LuminoEventRetriever;
import org.rif.lumino.explorer.models.EventData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@Component
public class ChannelEventsService {

  @Value("${rsk.blockchain.endpoint}")
  private String rskBlockchainEndpoint;

  @Value("${lumino.contracts.openChannelEvent}")
  private String openChannelEventName;

  @Value("${lumino.contracts.closeChannelEvent}")
  private String closeChannelEventName;

  @Value("${lumino.contracts.settleChannelEvent}")
  private String settleChannelEventName;

  @Value("${lumino.contracts.newDepositChannelEvent}")
  private String newDespositChannelEventName;

  LuminoEventRetriever openChannelService;
  LuminoEventRetriever closeChannelService;
  LuminoEventRetriever settleChannelService;
  LuminoEventRetriever newDepositChannelService;

  @PostConstruct
  public void init() {

    openChannelService =
        new LuminoEventRetriever(
            openChannelEventName,
            Arrays.asList(
                new TypeReference<Uint256>(true) {},
                new TypeReference<Address>(true) {},
                new TypeReference<Address>(true) {},
                new TypeReference<Uint256>() {}),
            rskBlockchainEndpoint);

    closeChannelService =
        new LuminoEventRetriever(
            closeChannelEventName,
            Arrays.asList(
                new TypeReference<Uint256>(true) {},
                new TypeReference<Address>(true) {},
                new TypeReference<Uint256>(true) {}),
            rskBlockchainEndpoint);

    settleChannelService =
        new LuminoEventRetriever(
            settleChannelEventName,
            Arrays.asList(
                new TypeReference<Uint256>(true) {},
                new TypeReference<Uint256>(false) {},
                new TypeReference<Uint256>(false) {}),
            rskBlockchainEndpoint);

    newDepositChannelService =  new LuminoEventRetriever(
            newDespositChannelEventName,
            Arrays.asList(
                    new TypeReference<Uint256>(true) {},
                    new TypeReference<Address>(true) {},
                    new TypeReference<Uint256>(false) {}),
            rskBlockchainEndpoint);
  }

  public List<EventData> getNewDepositChannelEvents(
          BigInteger from, BigInteger to, String contractAddress) throws Exception {
    List<EventData> newDepositChannelEvents = newDepositChannelService.getLogs(from, to, contractAddress);
    if (!newDepositChannelEvents.isEmpty()) {
      newDepositChannelEvents.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    }
    return newDepositChannelEvents;
  }

  public List<EventData> getOpenChannelEvents(
      BigInteger from, BigInteger to, String contractAddress) throws Exception {
    List<EventData> openEvents = openChannelService.getLogs(from, to, contractAddress);
    if (!openEvents.isEmpty()) {
      openEvents.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    }
    return openEvents;
  }

  public List<EventData> getClosedChannelEvents(
      BigInteger from, BigInteger to, String contractAddress) throws Exception {
    List<EventData> closeEvents = closeChannelService.getLogs(from, to, contractAddress);
    if (!closeEvents.isEmpty()) {
      closeEvents.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    }
    return closeEvents;
  }

  public List<EventData> getSettledChannelEvents(
      BigInteger from, BigInteger to, String contractAddress) throws Exception {
    List<EventData> settleEvents = settleChannelService.getLogs(from, to, contractAddress);
    if (!settleEvents.isEmpty()) {
      settleEvents.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    }
    return settleEvents;
  }

  public List<EventData> getChanneslEvents(BigInteger from, BigInteger to, String contractAddress)
      throws Exception {
    List<EventData> openEvents = getOpenChannelEvents(from, to, contractAddress);
    List<EventData> closeEvents = getClosedChannelEvents(from, to, contractAddress);
    List<EventData> settleEvents = getSettledChannelEvents(from, to, contractAddress);

    List<EventData> allEvents = new ArrayList<>();
    allEvents.addAll(openEvents);
    allEvents.addAll(closeEvents);
    allEvents.addAll(settleEvents);
    // Sort by blockNumber
    allEvents.sort(Comparator.comparing(eventData -> eventData.getBlockNumber()));
    return allEvents;
  }
}
