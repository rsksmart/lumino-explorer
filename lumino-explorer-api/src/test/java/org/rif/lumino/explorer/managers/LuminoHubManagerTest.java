package org.rif.lumino.explorer.managers;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.rif.lumino.explorer.boot.configuration.properties.LuminoHubProperties;
import org.rif.lumino.explorer.controllers.LuminoHubConnectionController;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.rif.lumino.explorer.repositories.LuminoHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class LuminoHubManagerTest {

    @TestConfiguration
    public static class LuminoHubManagerTestConfiguration {
        @Bean
        public LuminoHubManager luminoHubManager(
                LuminoHubRepository luminoHubRepository,
                LuminoHubProperties luminoHubProperties) {
            return new LuminoHubManager(luminoHubRepository, luminoHubProperties);
        }
    }

    @Autowired
    private LuminoHubManager luminoHubManager;

    @MockBean
    private LuminoHubRepository luminoHubRepository;

    @MockBean
    private LuminoHubProperties luminoHubProperties;

    @Before
    public void init() {
        Mockito.when(luminoHubProperties.getMaxConnections()).thenReturn(50);
        Mockito.when(luminoHubProperties.getUrls())
                .thenReturn(Arrays.asList("http://localhost:1234","http://localhost:1235"));

    }

    @Test
    public void registersAndReturnsUrl() {
        final LuminoHub hub = new LuminoHub("http://localhost:1234");
        final int initialCount = hub.getConnectionCount();
        final LuminoHubDTO expected =  new LuminoHubDTO(hub.getUrl());

        Mockito.when(luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.of(hub));

        LuminoHubDTO actual = luminoHubManager.addConnectionAndGetUrl();

        Assertions.assertThat(actual).isNotNull().as("Result should not be null");
        Assertions.assertThat(actual.getUrl()).isEqualTo(expected.getUrl()).as("Url should be %s", hub.getUrl());
        Assertions.assertThat(hub.getConnectionCount()).isEqualTo(initialCount + 1).as("Connection count should have increased");

        Mockito.verify(luminoHubRepository).findFirstByOrderByConnectionCount();
        Mockito.verify(luminoHubProperties).getMaxConnections();
        Mockito.verify(luminoHubRepository).save(hub);
    }

    @Test
    public void notFoundWhenEmptyRepository() {
        final LuminoHub hub = new LuminoHub("http://localhost:1234");

        Mockito.when(luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> luminoHubManager.addConnectionAndGetUrl())
                .isInstanceOf(NotFoundException.class);
        Mockito.verify(luminoHubRepository).findFirstByOrderByConnectionCount();
    }

    @Test
    public void maxConnectionWhenNoConnectionSlotsLeft() {
        final LuminoHub hub = new LuminoHub("http://localhost:1234");
        hub.setConnectionCount(50);

        Mockito.when(luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.of(hub));

        Assertions.assertThatThrownBy(() -> luminoHubManager.addConnectionAndGetUrl())
                .isInstanceOf(MaxConnectionException.class);

        Mockito.verify(luminoHubRepository).findFirstByOrderByConnectionCount();
        Mockito.verify(luminoHubProperties).getMaxConnections();
    }
}
