package org.rif.lumino.explorer.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.rif.lumino.explorer.constants.ControllerConstants;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.managers.LuminoHubManager;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LuminoHubConnectionController.class)
public class LuminoHubConnectionControllerTest {

    private static final String HUB_CONN_ENDPOINT = ControllerConstants.API_V1_VERSION_PATH + "/" + LuminoHubConnectionController.BASE_CONTROLLER_PATH;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LuminoHubManager luminoHubManager;

    @Test
    public void returnsUrl() throws Exception {
        final String expectedUrl = "http://localhost:1234";
        Mockito.when(luminoHubManager.addConnectionAndGetUrl()).thenReturn(new LuminoHubDTO("http://localhost:1234"));
        mockMvc.perform(MockMvcRequestBuilders.post(HUB_CONN_ENDPOINT))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.url")
                        .value(Matchers.equalTo(expectedUrl)));
        Mockito.verify(luminoHubManager).addConnectionAndGetUrl();
        Mockito.verifyNoMoreInteractions(luminoHubManager);
    }

    @Test
    public void NotFoundWhenNotFoundException() throws Exception {
        Mockito.when(luminoHubManager.addConnectionAndGetUrl()).thenThrow(new NotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.post(HUB_CONN_ENDPOINT))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        Mockito.verify(luminoHubManager).addConnectionAndGetUrl();
        Mockito.verifyNoMoreInteractions(luminoHubManager);

    }

    @Test
    public void ConflictWhenMaxConnectionException() throws Exception {
        Mockito.when(luminoHubManager.addConnectionAndGetUrl()).thenThrow(new MaxConnectionException());
        mockMvc.perform(MockMvcRequestBuilders.post(HUB_CONN_ENDPOINT))
                .andExpect(MockMvcResultMatchers.status().isConflict());
        Mockito.verify(luminoHubManager).addConnectionAndGetUrl();
        Mockito.verifyNoMoreInteractions(luminoHubManager);

    }
}
