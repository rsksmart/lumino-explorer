package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.documents.Channel;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.documents.Token;
import org.rif.lumino.explorer.models.dto.DashboardDTO;
import org.rif.lumino.explorer.models.dto.DashboardSummaryDTO;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.dto.TokenDTO;
import org.rif.lumino.explorer.models.enums.ChannelState;
import org.rif.lumino.explorer.services.CommonService;
import org.rif.lumino.explorer.services.blockchain.HumanStandardTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Service
public class DashboardManager {

    private static final Logger logger = LoggerFactory.getLogger(DashboardManager.class);

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private ChannelManager channelManager;
    @Autowired
    private LuminoNodeManager luminoNodeManager;
    @Autowired
    HumanStandardTokenService humanStandardTokenService;
    @Autowired
    CommonService commonService;

    public DashboardDTO getDashboardData(String tokenNetworkAddress) {

        List<Token> tokens = null;
        List<LuminoNode> nodes = null;
        DashboardDTO dashboardDTO = new DashboardDTO();

        try {
            tokens = tokenManager.getAll();
            nodes = luminoNodeManager.getAll();

            List<TokenDTO> tokenDTOS = commonService.mapTokensDTO(tokens);

            String selectedToken = tokenNetworkAddress;
            if ((null == selectedToken) && (null != tokenDTOS) && (tokenDTOS.size() > 0)) {
                selectedToken = tokens.get(0).getTokenNetworkAddress();
            }

            dashboardDTO.setDashboardSummaryDTO(getDashboardSummary(selectedToken, nodes));

            // we want to have the channels of the selected token
            List<Channel> openedChannels = this.getOpenChannelsForToken(selectedToken);

            dashboardDTO.setChannelsDTO(commonService.mapChannelsDTO(openedChannels));
            dashboardDTO.setTokensDTO(tokenDTOS);
            dashboardDTO.setLuminoNodeDTO(getLuminoNodesDTO(nodes));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return dashboardDTO;
    }

    private List<Channel> getOpenChannelsForToken(final String selectedToken) {
        List<Channel> openedChannels = channelManager.getChannelsByState(ChannelState.Opened);
        return openedChannels.stream().filter(channel -> channel.getTokenNetworkAddress().equals(selectedToken)).collect(Collectors.toList());
    }

    private List<LuminoNodeDTO> getLuminoNodesDTO(List<LuminoNode> nodes) {
        List<LuminoNodeDTO> luminoNodeDTOS = new ArrayList<>();
        for (LuminoNode luminoNode : nodes) {
            LuminoNodeDTO luminoNodeDTO = new LuminoNodeDTO();
            luminoNodeDTO.setNodeAddress(luminoNode.getNodeAddress());
            luminoNodeDTO.setRnsAddress(luminoNode.getRnsAddress());
            luminoNodeDTOS.add(luminoNodeDTO);
        }

        return luminoNodeDTOS;
    }

    /**
     * Returns the dashboard summary with the following data:
     * <p>
     * Total amount of Lumino Nodes
     * Total amount of nodes for the selected token
     * Total amount of Lumino channels
     * Total amount of channels for the selected token
     *
     * @param selectedToken
     * @param nodes
     * @return
     */
    private DashboardSummaryDTO getDashboardSummary(String selectedToken, List<LuminoNode> nodes) {

        DashboardSummaryDTO dashboardSummaryDTO = new DashboardSummaryDTO();

        dashboardSummaryDTO.setLuminoNodes(luminoNodeManager.count());

        List<Channel> openedChannels = channelManager.getChannelsByState(ChannelState.Opened);
        dashboardSummaryDTO.setLuminoChannels(Long.valueOf(openedChannels.size()));

        dashboardSummaryDTO.setTokenChannels(getChannelsCountByTokenNetworkAddress(selectedToken));
        List<Channel> channelsByTokenNetworkAddress = channelManager.getChannelsByTokenNetworkAddressAndChannelState(selectedToken, ChannelState.Opened.toString());

        dashboardSummaryDTO.setTokenNodes(getNodeCountWithOpenChannels(nodes, channelsByTokenNetworkAddress));

        return dashboardSummaryDTO;
    }

    private Integer getNodeCountWithOpenChannels(List<LuminoNode> nodes, List<Channel> openedChannels) {
        Set<String> nodeAddreses = new HashSet<>();
        for (LuminoNode node : nodes) {
            for (Channel channel : openedChannels) {
                try {
                    if (node.getNodeAddress().equalsIgnoreCase(channel.getParticipantOneAddress()) ||
                            node.getNodeAddress().equalsIgnoreCase(channel.getParticipantTwoAddress())) {
                        nodeAddreses.add(node.getNodeAddress());
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
            }
        }
        return nodeAddreses.size();
    }

    private Integer getChannelsCountByTokenNetworkAddress(String tokenNetworkAddress) {
        Integer count = 0;
        if (tokenNetworkAddress != null) {
            count = channelManager.getChannelsByTokenNetworkAddressAndChannelState(tokenNetworkAddress, ChannelState.Opened.toString()).size();
        }
        return count;
    }

}
