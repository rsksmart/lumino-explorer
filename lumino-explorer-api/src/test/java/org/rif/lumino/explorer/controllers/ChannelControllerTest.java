package org.rif.lumino.explorer.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.managers.ChannelManager;
import org.rif.lumino.explorer.models.dto.ChannelDTO;
import org.rif.lumino.explorer.models.dto.ListChannelsResponseDTO;
import org.rif.lumino.explorer.utils.UtilChannelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
    @WebMvcTest(controllers = ChannelController.class)
public class ChannelControllerTest {

    private static final String CHANNEL_ENDPOINT = ControllerConstants.API_V1_VERSION_PATH + "/" + ChannelController.BASE_CONTROLLER_PATH;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelManager channelManager;

    @Test
    public void canRetrieveAllChannels() throws Exception {

        ListChannelsResponseDTO listChannelsResponseDTO = new ListChannelsResponseDTO();
        listChannelsResponseDTO.setChannels(UtilChannelTest.getChannels(null, 10, true));

        when(channelManager.getChannels(null, null)).thenReturn(listChannelsResponseDTO);

        mockMvc.perform(
                get(CHANNEL_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.channels.*", hasSize(10)));

        verify(channelManager, times(1)).getChannels(null, null);
        verifyNoMoreInteractions(channelManager);

    }

    @Test
    public void notExistsChannels() throws Exception {
        ListChannelsResponseDTO listChannelsResponseDTO = new ListChannelsResponseDTO();
        listChannelsResponseDTO.setChannels(new ArrayList<>());

        when(channelManager.getChannels(null, null)).thenReturn(listChannelsResponseDTO);

        mockMvc.perform(get(CHANNEL_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.channels.*", hasSize(0)));

        verify(channelManager, times(1)).getChannels(null, null);
        verifyNoMoreInteractions(channelManager);

    }

    @Test
    public void getChannelsByTokenNetoworkAddress() throws Exception {

        ListChannelsResponseDTO listChannelsResponseDTO = new ListChannelsResponseDTO();
        listChannelsResponseDTO.setChannels(UtilChannelTest.getChannels(null, 1, true));

        ChannelDTO first = listChannelsResponseDTO.getChannels().get(0);

        when(channelManager.getChannels(first.getTokenNetworkAddress(), null)).thenReturn(listChannelsResponseDTO);

        mockMvc.perform(get(CHANNEL_ENDPOINT + "/" + "?token_network_address=" + first.getTokenNetworkAddress()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.channels.*", hasSize(1)))
                .andExpect(jsonPath("$.channels[0].channel_identifier", equalTo(first.getChannelIdentifier())))
                .andExpect(jsonPath("$.channels[0].from_address", equalTo(first.getFromAddress())))
                .andExpect(jsonPath("$.channels[0].from_rns_address", equalTo(first.getFromRnsAddress())))
                .andExpect(jsonPath("$.channels[0].to_rns_address", equalTo(first.getToRnsAddress())))
                .andExpect(jsonPath("$.channels[0].to_address", equalTo(first.getToAddress())))
                .andExpect(jsonPath("$.channels[0].state", equalTo(first.getState())))
                .andExpect(jsonPath("$.channels[0].token_network_address", equalTo(first.getTokenNetworkAddress())))
                .andExpect(jsonPath("$.channels[0].token_name", equalTo(first.getTokenName())))
                .andExpect(jsonPath("$.channels[0].token_address", equalTo(first.getTokenAddress())))
                .andExpect(jsonPath("$.channels[0].token_symbol", equalTo(first.getTokenSymbol())));

        verify(channelManager, times(1)).getChannels(first.getTokenNetworkAddress(), null);
        verifyNoMoreInteractions(channelManager);

    }

}
