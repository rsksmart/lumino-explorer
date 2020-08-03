package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.boot.configuration.properties.LuminoHubProperties;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.rif.lumino.explorer.repositories.LuminoHubRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class LuminoHubManager {

  private final LuminoHubRepository luminoNodeRepository;

  private final LuminoHubProperties luminoHubProperties;

  public LuminoHubManager(LuminoHubRepository luminoNodeRepository, LuminoHubProperties luminoHubProperties) {
    this.luminoNodeRepository = luminoNodeRepository;
    this.luminoHubProperties = luminoHubProperties;
    populate();
  }


  /**
   *
   * Retrieves the url of the {@link org.rif.lumino.explorer.models.documents.LuminoHub} with the least connection count
   * and increases it by one.
   * @return url of the {@link org.rif.lumino.explorer.models.documents.LuminoHub}
   */
  public LuminoHubDTO addConnectionAndGetUrl() {
    LuminoHub hub = luminoNodeRepository.findFirstByOrderByConnectionCount()
            .orElseThrow(() -> new NotFoundException("There aren't  Lumino Hub nodes registered in the Explorer"));
    addConnection(hub);
    return new LuminoHubDTO(hub.getUrl());
  }

  private void addConnection(LuminoHub hub) {
    if (hub.getConnectionCount() >= luminoHubProperties.getMaxConnections()) {
      throw new MaxConnectionException("There isn't any Lumino Hub node with free connection slots");
    }
    hub.increaseConnectionCount();
    luminoNodeRepository.save(hub);
  }

  private void populate() {
    // Populate only if empty
    if (luminoNodeRepository.findAll().isEmpty()) {
      List<LuminoHub> hubs = luminoHubProperties.getUrls().values().stream()
              .map(url -> new LuminoHub(url))
              .collect(Collectors.toList());
      luminoNodeRepository.saveAll(hubs);
    }
  }
}
