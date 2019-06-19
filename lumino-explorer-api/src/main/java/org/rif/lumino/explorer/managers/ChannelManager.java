package org.rif.lumino.explorer.managers;

import org.apache.commons.collections4.CollectionUtils;
import org.rif.lumino.explorer.models.EventData;
import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.dto.ListChannelsResponseDTO;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.rif.lumino.explorer.repositories.ChannelRepository;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.services.ChannelService;
import org.rif.lumino.explorer.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
@Service
public class ChannelManager {

    private static final Integer EVENT_VALUE_KEY_CHANNEL_IDENTIFIER = 0;
    private static final Integer EVENT_VALUE_KEY_TOTAL_DEPOSIT= 2;

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private LuminoNodeManager luminoNodeManager;
    @Autowired
    private TokenManager tokenManager;

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
                        ChannelState.Opened, new BigInteger("0"));

        Feed feed =
                commonService.mapNewFeed(
                        FeedType.CHANNEL, getDataForChannelFeed(openedChannel, ChannelState.Opened));

        feedRepository.save(feed);
        channelRepository.save(openedChannel);

        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        Date lastAliveSignal = Date.from(utc.toInstant());

        LuminoNodeDTO participant1 = new LuminoNodeDTO();

        participant1.setLastAliveSignal(lastAliveSignal);

        participant1.setNodeAddress(event.getValues().get(1).getValue().toString());
        luminoNodeManager.register(participant1);

        LuminoNodeDTO participant2 = new LuminoNodeDTO();
        participant2.setNodeAddress(event.getValues().get(2).getValue().toString());
        participant2.setLastAliveSignal(lastAliveSignal);

        luminoNodeManager.register(participant2);
    }

    private Map getDataForChannelFeed(Channel channel, ChannelState channelState) {
        Map<String, String> data = new HashMap<>();

        Token token = tokenManager.getTokenByNetworkAddress(channel.getTokenNetworkAddress());

        data.put("channel_identifier", channel.getId());
        data.put("from_address", channel.getParticipantOneAddress());
        data.put("to_address", channel.getParticipantTwoAddress());
        data.put("token_network_address", channel.getTokenNetworkAddress());
        data.put("token_address", token.getTokenAddress());
        data.put("token_decimals", token.getDecimals().toString());
        data.put("state", channelState.toString());

        if (channel.getTotalDeposit() != null) {
            data.put("deposit", channel.getTotalDeposit().toString());
        }

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

    public void processNewDepositChannelEvents(List<EventData> events) {
        for (EventData eventData : events) {
            processNewDepositChannelEvent(eventData);
        }
    }

    public void processNewDepositChannelEvent(EventData event) {
        BigInteger channelIdentifier = (BigInteger) event.getValues().get(EVENT_VALUE_KEY_CHANNEL_IDENTIFIER).getValue();
        Optional<Channel> channel = channelRepository.findById(channelIdentifier.toString());
        Channel channelRetrieved = channel.get();
        BigInteger totalDeposit = (BigInteger) event.getValues().get(EVENT_VALUE_KEY_TOTAL_DEPOSIT).getValue();
        channelRetrieved.setTotalDeposit(totalDeposit);

        Feed feed =
                commonService.mapNewFeed(
                        FeedType.CHANNEL, getDataForChannelFeed(channelRetrieved, ChannelState.NewDeposit));

        feedRepository.save(feed);

        channelRepository.save(channelRetrieved);
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
            List<EventData> settleChannelEvents,
            List<EventData> newDepositChannelEvents) {

        Map<ChannelState, List<EventData>> eventsMap = new HashMap<>();
        List<EventData> opened = new ArrayList<>(openChannelEvents);
        List<EventData> closed = new ArrayList<>(closeChannelEvents);
        List<EventData> settled = new ArrayList<>(settleChannelEvents);
        List<EventData> newDeposit = new ArrayList<>(newDepositChannelEvents);

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
        eventsMap.put(ChannelState.NewDeposit, newDeposit);

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

    public List<ChannelDTO> getChannelsByNode(String nodeAddress, String rnsAddress, String state) {
        List<ChannelDTO> channelDTOS = channelService.getChannelsByLuminoNodeAndState(nodeAddress, state);
        for (ChannelDTO channelDTO : channelDTOS) {
            if (channelDTO.getFromAddress().equalsIgnoreCase(nodeAddress)) {
                channelDTO.setFromRnsAddress(rnsAddress);
            }
            if (channelDTO.getToAddress().equalsIgnoreCase(nodeAddress)) {
                channelDTO.setToRnsAddress(rnsAddress);
            }
        }

        return channelDTOS;
    }

    public ListChannelsResponseDTO getChannels(String tokenNetworkAddress, String state) {

        ListChannelsResponseDTO listChannelsResponseDTO = new ListChannelsResponseDTO();

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

        listChannelsResponseDTO.setChannels(channelDTOS);

        return listChannelsResponseDTO;
    }
}
