package org.rif.lumino.explorer.managers;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.rif.lumino.explorer.boot.configuration.properties.LuminoHubProperties;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.rif.lumino.explorer.repositories.LuminoHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class LuminoHubBalancingManagerTest {

    @TestConfiguration
    public static class LuminoHubManagerTestConfiguration {
        @Bean
        public LuminoHubBalancingManager luminoHubManager(
                LuminoHubRepository luminoHubRepository,
                LuminoHubProperties luminoHubProperties) {
            return new LuminoHubBalancingManager(luminoHubRepository, luminoHubProperties);
        }
    }

    @Autowired
    private LuminoHubBalancingManager luminoHubBalancingManager;

    @MockBean
    private LuminoHubRepository luminoHubRepository;

    @MockBean
    private LuminoHubProperties luminoHubProperties;

    private final String address = "0x26b157A476755fD499a6C9Fee200C4ce4438FB5c";

    @Before
    public void init() {
        Mockito.when(this.luminoHubProperties.getMaxConnections()).thenReturn(50L);
        Mockito.when(this.luminoHubProperties.isUseDefaults()).thenReturn(true);
        Map<String, String> config = new HashMap<>();
        config.put("test1", "http://localhost:1234");
        config.put("test2", "http://localhost:1235");
        Mockito.when(this.luminoHubProperties.getUrls())
                .thenReturn(config);
    }

    @Test
    public void registersAndReturnsUrl() {
        final LuminoHub hub = new LuminoHub("http://localhost:1234", 10);
        final long initialCount = hub.getConnectionCount();
        final LuminoHubDTO expected =  new LuminoHubDTO(hub.getUrl());

        Mockito.when(this.luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.of(hub));

        LuminoHubDTO actual = this.luminoHubBalancingManager.addConnectionAndGetUrl(this.address);

        Assertions.assertThat(actual).isNotNull().as("Result should not be null");
        Assertions.assertThat(actual.getUrl()).isEqualTo(expected.getUrl()).as("Url should be %s", hub.getUrl());
        Assertions.assertThat(hub.getConnectionCount()).isEqualTo(initialCount + 1).as("Connection count should have increased");

        Mockito.verify(this.luminoHubRepository).findFirstByOrderByConnectionCount();
        Mockito.verify(this.luminoHubRepository).save(hub);
    }

    @Test
    public void notFoundWhenEmptyRepository() {
        Mockito.when(luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> luminoHubBalancingManager.addConnectionAndGetUrl(this.address))
                .isInstanceOf(NotFoundException.class);
        Mockito.verify(luminoHubRepository).findFirstByOrderByConnectionCount();
    }

    @Test
    public void maxConnectionWhenNoConnectionSlotsLeft() {
        final LuminoHub hub = new LuminoHub("http://localhost:1234", 50);
        hub.setConnectionCount(50);

        Mockito.when(luminoHubRepository.findFirstByOrderByConnectionCount())
                .thenReturn(Optional.of(hub));

        Assertions.assertThatThrownBy(() -> luminoHubBalancingManager.addConnectionAndGetUrl(this.address))
                .isInstanceOf(MaxConnectionException.class);

        Mockito.verify(luminoHubRepository).findFirstByOrderByConnectionCount();
    }
}
