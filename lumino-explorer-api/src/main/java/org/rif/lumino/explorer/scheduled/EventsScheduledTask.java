package org.rif.lumino.explorer.scheduled;

import org.rif.lumino.explorer.managers.ChannelManager;
import org.rif.lumino.explorer.managers.EventJobDataManager;
import org.rif.lumino.explorer.managers.TokenManager;
import org.rif.lumino.explorer.models.EventData;
import org.rif.lumino.explorer.models.documents.EventJobMetadata;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.rif.lumino.explorer.services.blockchain.ChannelEventsService;
import org.rif.lumino.explorer.services.blockchain.TokenNetworkRegistryService;
import org.rif.lumino.explorer.services.blockchain.Web3Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Component
public class EventsScheduledTask {


  @Value("${lumino.contract.tokenNetworkRegistry}")
  private String tokenNetworkRegistryAddress;

  @Autowired ChannelEventsService channelEventsService;

  @Autowired TokenNetworkRegistryService tokenNetworkRegistryService;

  @Autowired EventJobDataManager eventJobDataManager;

  @Autowired ChannelManager channelManager;

  @Autowired TokenManager tokenManager;

  @Autowired Web3Service web3Service;

  private EventJobMetadata jobMetadata;

  @Scheduled(fixedRate = 30000)
  public void run() throws Exception {

    // Get schedule metadata
    jobMetadata = eventJobDataManager.getEventJobData();
    // Get last blockchain block
    BigInteger lastChainBlock = web3Service.getBlockNumber();

    // Execute process

    // Get token networks
    List<EventData> registeredTokens = readTokenCreationEvents();
    // Update token networks data
    processTokensPersistence(registeredTokens);

    List<Token> tokenNetworks = tokenManager.getAll();

    tokenNetworks
        .parallelStream()
        .forEach(
            token -> {
              try {
                Map<ChannelState, List<EventData>> channelEvents =
                    readChannelEvents(token.getTokenNetworkAddress());
                System.out.println("Token network " + token.getTokenNetworkAddress());

                processChannelPersistence(channelEvents);

              } catch (Exception e) {
                e.printStackTrace();
              }
            });

    updateLastSyncBlockChannels(lastChainBlock.add(new BigInteger("1")));
    updateLastSyncBlockTokens(lastChainBlock.add(new BigInteger("1")));
  }

  private Map<ChannelState, List<EventData>> readChannelEvents(String tokenNetworkAddress)
      throws Exception {
    Map<ChannelState, List<EventData>> processEvents = processEvents(tokenNetworkAddress);
    System.out.println(processEvents);
    return processEvents;
  }

  private List<EventData> readTokenCreationEvents() throws Exception {
    return tokenNetworkRegistryService.getTokenRegisteredEvents(
        jobMetadata.getLastSyncBlockTokens(), null, tokenNetworkRegistryAddress);
  }

  private void processChannelPersistence(Map<ChannelState, List<EventData>> processEvents) {
    channelManager.processOpenChannelEvents(processEvents.get(ChannelState.Opened));
    channelManager.processClosedChannelEvents(processEvents.get(ChannelState.Closed));
    channelManager.processSettledChannelEvents(processEvents.get(ChannelState.Settled));
  }

  private Map<ChannelState, List<EventData>> processEvents(String tokenNetworkAddress)
      throws Exception {

    BigInteger from = jobMetadata.getLastSyncBlockChannels();

    System.out.println("Getting channel org.rif.lumino.explorer.events from block " + from);

    List<EventData> openChannelEvents =
        channelEventsService.getOpenChannelEvents(from, null, tokenNetworkAddress);
    List<EventData> closeChannelEvents =
        channelEventsService.getClosedChannelEvents(from, null, tokenNetworkAddress);
    List<EventData> settledChannelEvents =
        channelEventsService.getSettledChannelEvents(from, null, tokenNetworkAddress);

    return channelManager.mapChannelEventsToCrud(
        openChannelEvents, closeChannelEvents, settledChannelEvents);
  }

  private void processTokensPersistence(List<EventData> registeredTokens) {
    tokenManager.processRegisterTokenEvents(registeredTokens);
  }

  private void updateLastSyncBlockChannels(BigInteger lastChainBlock) throws Exception {
    System.out.println(
        "Last chain block "
            + lastChainBlock
            + ", updating blocknumber for channel sync to "
            + lastChainBlock);

    eventJobDataManager.updateLastSyncBlockChannels(lastChainBlock);
  }

  private void updateLastSyncBlockTokens(BigInteger lastChainBlock) throws Exception {
    System.out.println(
        "Last chain block "
            + lastChainBlock
            + ", updating blocknumber for tokens sync to "
            + lastChainBlock);

    eventJobDataManager.updateLastSyncBlockTokens(lastChainBlock);
  }
}
