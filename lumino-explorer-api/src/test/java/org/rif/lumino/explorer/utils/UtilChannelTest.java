package org.rif.lumino.explorer.utils;

import org.rif.lumino.explorer.models.dto.ChannelDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilChannelTest {

    private static final String FROM_RNS_ADDRESS = "from_rns_address";
    private static final String TO_RNS_ADDRESS = "to_rns_address";
    private static final String CHANNEL_IDENTIFIER = "channel_identifier";
    private static final String FROM_ADDRESS = "from_address";
    private static final String TO_ADDRESS = "to_address";
    private static final String STATE = "state";
    private static final String TOKEN_ADDRESS = "token_address";
    private static final String TOKEN_NAME = "token_name";
    private static final String TOKEN_NETWORK_ADDRESS = "token_network_address";
    private static final String TOKEN_SYMBOL = "token_symbol";

    private static final String FROM_RNS_ADDRESS_MOCK = "fromRnsAddressMock.rsk.co";
    private static final String TO_RNS_ADDRESS_MOCK = "toRnsAddressMock.rsk.co";
    private static final String TOKEN_NAME_MOCK = "RIF";
    private static final String TOKEN_SYMBOL_SYMBOL_MOCK = "RIF_SYMBOL";

    public static List<ChannelDTO> getChannels(Map dataParam, Integer quantity, boolean defaultMockData) {
        List<ChannelDTO> channelDTOS = new ArrayList<ChannelDTO>();
        for (int i = 0; i < quantity; i++) {
            Map data = dataParam;
            if (defaultMockData) {
                data = createDefaultMockData();
            }
            ChannelDTO channelDTO = createChannel(data);
            channelDTOS.add(channelDTO);
        }
        return channelDTOS;
    }

    private static ChannelDTO createChannel(Map data) {
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setFromRnsAddress((String) data.get(FROM_RNS_ADDRESS));
        channelDTO.setToRnsAddress((String) data.get(TO_RNS_ADDRESS));
        channelDTO.setChannelIdentifier(data.get(CHANNEL_IDENTIFIER).toString());
        channelDTO.setFromAddress((String) data.get(FROM_ADDRESS));
        channelDTO.setToAddress((String)data.get(TO_ADDRESS));
        channelDTO.setState((String) data.get(STATE));
        channelDTO.setTokenAddress((String) data.get(TOKEN_ADDRESS));
        channelDTO.setTokenName((String) data.get(TOKEN_NAME));
        channelDTO.setTokenNetworkAddress((String) data.get(TOKEN_NETWORK_ADDRESS));
        channelDTO.setTokenSymbol((String) data.get(TOKEN_SYMBOL));
        return channelDTO;
    }

    private static Map createDefaultMockData() {
        Map data = new HashMap();
        data.put(FROM_RNS_ADDRESS, FROM_RNS_ADDRESS_MOCK);
        data.put(TO_RNS_ADDRESS, TO_RNS_ADDRESS_MOCK);
        data.put(CHANNEL_IDENTIFIER, RandomUtil.getRandomNumberId());
        data.put(FROM_ADDRESS, RandomUtil.getRandomRSKAddress());
        data.put(TO_ADDRESS, RandomUtil.getRandomRSKAddress());
        data.put(STATE, RandomUtil.getRandomChannelState());
        data.put(TOKEN_ADDRESS, RandomUtil.getRandomRSKAddress());
        data.put(TOKEN_NAME, TOKEN_NAME_MOCK);
        data.put(TOKEN_NETWORK_ADDRESS, RandomUtil.getRandomRSKAddress());
        data.put(TOKEN_SYMBOL,TOKEN_SYMBOL_SYMBOL_MOCK);
        return  data;
    }

}
