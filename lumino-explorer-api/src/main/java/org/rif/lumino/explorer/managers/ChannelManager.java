package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.EventData;
import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.rif.lumino.explorer.repositories.ChannelRepository;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.services.ChannelService;
import org.rif.lumino.explorer.services.CommonService;

import static java.util.Comparator.comparing;

import java.util.*;

@Component
@Service
public class ChannelManager {

  @Autowired private ChannelRepository channelRepository;
  @Autowired private CommonService commonService;
  @Autowired private FeedRepository feedRepository;
  @Autowired private ChannelService channelService;

  public List<Channel> getChannelsByState(ChannelState channelState) {

    List<Channel> channels = channelRepository.findByChannelState(channelState.name());

    return channels;
  }

  public void processOpenChannelEvents(List<EventData> events) {
    for (EventData eventData : events) {
      processOpenChannelEvent(eventData);
    }
  }

  public void processOpenChannelEvent(EventData event) {
    Channel openedChannel =
        new Channel(
            event.getValues().get(0).getValue().toString(),
            event.getContractAddress(),
            event.getValues().get(1).getValue().toString(),
            event.getValues().get(2).getValue().toString(),
            ChannelState.Opened);

    Feed feed =
        commonService.mapNewFeed(
            FeedType.CHANNEL, getDataForChannelFeed(openedChannel, ChannelState.Opened));

    feedRepository.save(feed);
    channelRepository.save(openedChannel);
  }

  private Map getDataForChannelFeed(Channel channel, ChannelState channelState) {
    Map<String, String> data = new HashMap<String, String>();

    data.put("channel_identifier", channel.getId());
    data.put("from_address", channel.getParticipantOneAddress());
    data.put("to_address", channel.getParticipantTwoAddress());
    data.put("token_network_address", channel.getTokenNetworkAddress());
    data.put("state", channelState.toString());

    return data;
  }

  public void processClosedChannelEvents(List<EventData> events) {
    for (EventData eventData : events) {
      processClosedChannelEvent(eventData);
    }
  }

  public void processClosedChannelEvent(EventData event) {
    // Get the previously open channel and update it. If there is no open channel throws an error.
    Optional<Channel> openedChannel =
        channelRepository.findByIdAndTokenNetworkAddress(
            event.getValues().get(0).getValue().toString(), event.getContractAddress());
    Channel channel =
        openedChannel.orElseThrow(
            () -> new RuntimeException("No opened channel found for:" + event));
    channel.setChannelState(ChannelState.Closed);

    Feed feed =
        commonService.mapNewFeed(
            FeedType.CHANNEL, getDataForChannelFeed(channel, ChannelState.Closed));

    feedRepository.save(feed);

    channelRepository.save(channel);
  }

  public void processSettledChannelEvents(List<EventData> events) {
    for (EventData eventData : events) {
      processSettledChannelEvent(eventData);
    }
  }

  public void processSettledChannelEvent(EventData event) {
    // Get the previously closed channel and update it. If there is no closed channel throws an
    // error.
    Optional<Channel> closedChannel =
        channelRepository.findByIdAndTokenNetworkAddress(
            event.getValues().get(0).getValue().toString(), event.getContractAddress());
    Channel channel =
        closedChannel.orElseThrow(
            () -> new RuntimeException("No closed channel found for:" + event));
    channel.setChannelState(ChannelState.Settled);

    Feed feed =
        commonService.mapNewFeed(
            FeedType.CHANNEL, getDataForChannelFeed(channel, ChannelState.Settled));

    feedRepository.save(feed);
    channelRepository.save(channel);
  }

  public Map<ChannelState, List<EventData>> mapChannelEventsToCrud(
      List<EventData> openChannelEvents,
      List<EventData> closeChannelEvents,
      List<EventData> settleChannelEvents) {

    Map<ChannelState, List<EventData>> eventsMap = new HashMap<>();
    List<EventData> opened = new ArrayList<>(openChannelEvents);
    List<EventData> closed = new ArrayList<>(closeChannelEvents);
    List<EventData> settled = new ArrayList<>(settleChannelEvents);

    opened.removeAll(closeChannelEvents);
    opened.removeAll(settleChannelEvents);

    closed.removeAll(openChannelEvents);
    closed.removeAll(settleChannelEvents);

    Collection<EventData> openedThatWereClosed =
        CollectionUtils.intersection(openChannelEvents, closeChannelEvents);
    opened.removeAll(openedThatWereClosed);

    Collection<EventData> closedThatWereSettled =
        CollectionUtils.intersection(closeChannelEvents, settleChannelEvents);
    closed.removeAll(closedThatWereSettled);

    eventsMap.put(ChannelState.Opened, opened);
    eventsMap.put(ChannelState.Closed, closed);
    eventsMap.put(ChannelState.Settled, settled);

    return eventsMap;
  }

  public List<Channel> getAll() {
    return channelRepository.findAll();
  }

  public Long count() {
    return channelRepository.count();
  }

  public List<Channel> getChannelsByTokenNetworkAddressAndChannelState(
      String tokenNetoworkAddress, String channelState) {
    return channelRepository.findByTokenNetworkAddressAndChannelState(
        tokenNetoworkAddress, channelState);
  }

  public List<ChannelDTO> getChannelsByNode(String nodeAddress,String rnsAddress, String state) {
    List<ChannelDTO> channelDTOS = channelService.getChannelsByLuminoNodeAndState(nodeAddress, state);
    for (ChannelDTO channelDTO : channelDTOS) {
        if (channelDTO.getFromAddress().equalsIgnoreCase(nodeAddress)){
          channelDTO.setFromRnsAddress(rnsAddress);
        }
        if (channelDTO.getToAddress().equalsIgnoreCase(nodeAddress)){
          channelDTO.setToRnsAddress(rnsAddress);
        }
    }

    return channelDTOS;
  }

  public List<ChannelDTO> getChannels(String tokenNetworkAddress, String state) {
    List<Channel> channels = new ArrayList<>();

    if (tokenNetworkAddress == null && state == null) {
      channels = channelRepository.findAll();
    } else {
      if (state != null) {
        channels = channelRepository.findByChannelState(state);
      } else if (tokenNetworkAddress != null) {
        channels = channelRepository.findByTokenNetworkAddress(tokenNetworkAddress);
      } else if (tokenNetworkAddress != null && state != null) {
        channels =
            channelRepository.findByTokenNetworkAddressAndChannelState(tokenNetworkAddress, state);
      }
    }

    List<ChannelDTO> channelDTOS = commonService.mapChannelsDTO(channels);
    return channelDTOS;
  }
}
