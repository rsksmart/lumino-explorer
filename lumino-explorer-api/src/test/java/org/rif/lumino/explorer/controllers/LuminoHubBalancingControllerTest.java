package org.rif.lumino.explorer.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.managers.LuminoHubBalancingManager;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.rif.lumino.explorer.repositories.LuminoHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LuminoHubBalancingController.class)
public class LuminoHubBalancingControllerTest {

    private final String EXPECTED_URL = "http://localhost:1234";
    private static final String ADDRESS = "0xee67692FD1965CF6Ee8321aCAF82860daf840040";
    private static final String BALANCING_CONTROLLER_PATH = ControllerConstants.API_V1_VERSION_PATH + "/" + LuminoHubBalancingController.BASE_CONTROLLER_PATH;
    private static final String REGISTER_CLIENT_PATH = BALANCING_CONTROLLER_PATH + "/register_client/" + ADDRESS;
    private static final String UNREGISTER_CLIENT_PATH = BALANCING_CONTROLLER_PATH + "/unregister_client/" + ADDRESS;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LuminoHubBalancingManager luminoHubBalancingManager;

    @MockBean
    private LuminoHubRepository luminoHubRepository;

    @Test
    public void unregister() throws Exception {
        final String hubUrl = "http://localhost:1111";
        final String address = "someaddress";
        final String hubId = "someid";
        final LuminoHub hubInstance = new LuminoHub(hubUrl, 10);
        hubInstance.setId(hubId);
        hubInstance.addConnection(address);
        Mockito.when(this.luminoHubRepository.findByUrl(hubUrl)).thenReturn(Optional.of(hubInstance));
        Mockito.when(this.luminoHubRepository.findByConnectedClientsContains(address)).thenReturn(Optional.of(hubInstance));
        Mockito.when(this.luminoHubRepository.findFirstByOrderByConnectionCount()).thenReturn(Optional.of(hubInstance));
        Mockito.when(this.luminoHubRepository.findById(hubId)).thenReturn(Optional.of(hubInstance));

        this.mockMvc.perform(MockMvcRequestBuilders.post(UNREGISTER_CLIENT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verifyNoMoreInteractions(this.luminoHubRepository);
    }

    @Test
    public void register() throws Exception {
        Mockito.when(this.luminoHubBalancingManager.addConnectionAndGetUrl(ADDRESS)).thenReturn(new LuminoHubDTO(EXPECTED_URL));
        this.mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_CLIENT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.url")
                        .value(Matchers.equalTo(EXPECTED_URL)));
        Mockito.verify(this.luminoHubBalancingManager).addConnectionAndGetUrl(ADDRESS);
        Mockito.verifyNoMoreInteractions(this.luminoHubBalancingManager);
    }

    @Test
    public void NotFoundWhenNotFoundException() throws Exception {
        Mockito.when(this.luminoHubBalancingManager.addConnectionAndGetUrl(ADDRESS)).thenThrow(new NotFoundException());
        this.mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_CLIENT_PATH))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        Mockito.verify(this.luminoHubBalancingManager).addConnectionAndGetUrl(ADDRESS);
        Mockito.verifyNoMoreInteractions(this.luminoHubBalancingManager);
    }

    @Test
    public void ConflictWhenMaxConnectionException() throws Exception {
        Mockito.when(this.luminoHubBalancingManager.addConnectionAndGetUrl(ADDRESS)).thenThrow(new MaxConnectionException());
        this.mockMvc.perform(MockMvcRequestBuilders.post(REGISTER_CLIENT_PATH))
                .andExpect(MockMvcResultMatchers.status().isConflict());
        Mockito.verify(this.luminoHubBalancingManager).addConnectionAndGetUrl(ADDRESS);
        Mockito.verifyNoMoreInteractions(this.luminoHubBalancingManager);
    }
}
