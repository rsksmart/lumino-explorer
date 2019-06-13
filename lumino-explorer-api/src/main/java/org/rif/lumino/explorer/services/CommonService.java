package org.rif.lumino.explorer.services;

import org.rif.lumino.explorer.helper.LuminoNodeHelper;
import org.rif.lumino.explorer.managers.ChannelManager;
import org.rif.lumino.explorer.managers.TokenManager;
import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.dto.TokenDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Component
public class CommonService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private ChannelManager channelManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private LuminoNodeService luminoNodeService;

    public List<ChannelDTO> mapChannelsDTO(List<Channel> channels) {
        List<ChannelDTO> channelDTOS = new ArrayList<>();

        channels.forEach(channel->{
            ChannelDTO channelDTO = new ChannelDTO();
            channelDTO.setChannelIdentifier(channel.getId());
            channelDTO.setFromAddress(channel.getParticipantOneAddress());
            channelDTO.setToAddress(channel.getParticipantTwoAddress());

            List<LuminoNode> luminoNodesFromAddress = luminoNodeService.getNodeById(channel.getParticipantOneAddress());
            List<LuminoNode> luminoNodesToAddress = luminoNodeService.getNodeById(channel.getParticipantTwoAddress());
            if (!luminoNodesFromAddress.isEmpty()){
                channelDTO.setFromRnsAddress(luminoNodesFromAddress.get(0).getRnsAddress());
            }
            if (!luminoNodesToAddress.isEmpty()){
                channelDTO.setToRnsAddress(luminoNodesToAddress.get(0).getRnsAddress());
            }

            channelDTO.setTokenNetworkAddress(channel.getTokenNetworkAddress());
            channelDTO.setState(channel.getChannelState().name());
            Token token = tokenManager.getTokenByNetworkAddress(channel.getTokenNetworkAddress());
            channelDTO.setTokenAddress(token.getTokenAddress());
            channelDTO.setTokenName(token.getName());
            channelDTO.setTokenSymbol(token.getSymbol());
            channelDTOS.add(channelDTO);
        });

        return channelDTOS;
    }

    public List<TokenDTO> mapTokensDTO(List<Token> tokens) {

        List<TokenDTO> tokenDTOS = new ArrayList<>();
        tokens.forEach(token->{
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setAddress(token.getTokenAddress());
            tokenDTO.setName(token.getName());
            tokenDTO.setNetworkAddress(token.getTokenNetworkAddress());
            tokenDTO.setSymbol(token.getSymbol());
            tokenDTO.setChannels(mapChannelsDTO(channelManager.getChannelsByTokenNetworkAddressAndChannelState(token.getTokenNetworkAddress(), ChannelState.Opened.name())));

            tokenDTOS.add(tokenDTO);
        });

        return tokenDTOS;
    }

    public List<LuminoNodeDTO> mapLuminoNodesDTO(List<LuminoNode> luminoNodes){
        List<LuminoNodeDTO> luminoNodeDTOS = new ArrayList<>();
        luminoNodes.forEach( luminoNode-> {
            LuminoNodeDTO luminoNodeDTO = LuminoNodeHelper.toDto(luminoNode);
            luminoNodeDTO.setChannels(channelManager.getChannelsByNode(luminoNode.getNodeAddress(),luminoNode.getRnsAddress(), ChannelState.Opened.toString()));
            luminoNodeDTOS.add(LuminoNodeHelper.toDto(luminoNode));
        });

        return luminoNodeDTOS;
    }

    public Feed mapNewFeed(FeedType feedType, Map data) {
        Feed feed =  new Feed();
        feed.setId(sequenceGeneratorService.generateSequence(Feed.SEQUENCE_NAME));
        feed.setType(feedType.toString());
        feed.setData(data);
        return feed;
    }



}
