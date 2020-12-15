package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.boot.configuration.properties.LuminoHubProperties;
import org.rif.lumino.explorer.exceptions.AlreadyExistException;
import org.rif.lumino.explorer.exceptions.BadRequest;
import org.rif.lumino.explorer.exceptions.MaxConnectionException;
import org.rif.lumino.explorer.exceptions.NotFoundException;
import org.rif.lumino.explorer.models.documents.LuminoHub;
import org.rif.lumino.explorer.models.dto.LuminoHubDTO;
import org.rif.lumino.explorer.repositories.LuminoHubRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class LuminoHubBalancingManager {

  private final LuminoHubRepository luminoNodeRepository;

  private final LuminoHubProperties luminoHubProperties;

  public LuminoHubBalancingManager(LuminoHubRepository luminoNodeRepository,
                                   LuminoHubProperties luminoHubProperties) {
    this.luminoNodeRepository = luminoNodeRepository;
    this.luminoHubProperties = luminoHubProperties;
  }

  /**
   * Setup the default available hubs to use for the balancer
   */
  @PostConstruct
  private void setupDefaults() {
    // Populate only if empty
    if (this.luminoNodeRepository.findAll().isEmpty() && this.luminoHubProperties.isUseDefaults()) {
      List<LuminoHub> hubs = this.luminoHubProperties.getUrl().entrySet().stream()
              .map((entry) -> {
                boolean hasInfiniteCapacity = this.luminoHubProperties.getInfiniteCapacity().getOrDefault(entry.getKey(), false);
                Long capacity = this.luminoHubProperties.getMaxConnections().get(entry.getKey());
                if (hasInfiniteCapacity || capacity == null) {
                  return new LuminoHub(entry.getValue(), true);
                } else {
                  return new LuminoHub(entry.getValue(), capacity);
                }
              }).collect(Collectors.toList());
      this.luminoNodeRepository.saveAll(hubs);
    }
  }

  /**
   * Retrieves the url of the {@link org.rif.lumino.explorer.models.documents.LuminoHub} with the least connection count
   * and increases it by one.
   * @return url of the {@link org.rif.lumino.explorer.models.documents.LuminoHub}
   * @throws NotFoundException if no lumino hub is found on database
   * @throws MaxConnectionException if all hubs are out of capacity and we can't add the client to any hub
   */
  public LuminoHubDTO addConnectionAndGetUrl(String address) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findByConnectedClientsContains(address);
    return currentHub.map(LuminoHubDTO::fromLuminoHub).orElseGet(() -> {
      LuminoHub hub = this.luminoNodeRepository.findFirstByOrderByConnectionCount()
              .orElseThrow(() -> new NotFoundException("There aren't Lumino Hub nodes registered in the Explorer"));
      return LuminoHubDTO.fromLuminoHub(this.addConnection(hub, address));
    });
  }

  /**
   * Removes a connection from a hub
   * @throws NotFoundException if no lumino hub is found on database
   * @throws MaxConnectionException if all hubs are out of capacity and we can't add the client to any hub
   */
  public void removeConnection(String address) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findByConnectedClientsContains(address);
    if (currentHub.isPresent()) {
      LuminoHub hub = currentHub.get();
      hub.removeConnection(address);
      this.luminoNodeRepository.save(hub);
    } else {
      throw new NotFoundException(String.format("No Lumino Hub or Hubs Found containing client with address %s", address));
    }
  }

  /**
   * Add a client to the hub connection if the hub has enough capacity
   * @param hub the Hub to add the connection
   * @param address the address for the client to add
   */
  private LuminoHub  addConnection(LuminoHub hub, String address) {
    if (hub.getConnectionCount() >= hub.getMaxConnectionsAllowed() && !hub.isInfiniteCapacity()) {
      LuminoHub infiniteCapacityHub = this.luminoNodeRepository.findByInfiniteCapacityEqualsOrderByConnectionCount(true)
              .orElseThrow(() -> new MaxConnectionException("There isn't any Lumino Hub node with free connection slots"));
      infiniteCapacityHub.addConnection(address);
      this.luminoNodeRepository.save(infiniteCapacityHub);
      return infiniteCapacityHub;
    } else {
      hub.addConnection(address);
      this.luminoNodeRepository.save(hub);
      return hub;
    }
  }

  /**
   * Adds a new available hub to the list of hubs for the balancer.
   * If the url already exists then it throws an exception
   * @param url the hub url
   * @param maxConnectionsAllowed the max allowed connections for this hub
   * @param infiniteCapacity indicates if the hub should have infinite capacity
   * @throws AlreadyExistException when already exists some hub with that url
   */
  public LuminoHub saveHub(String url, long maxConnectionsAllowed, boolean infiniteCapacity) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findByUrl(url);
    if (currentHub.isPresent()) {
      throw new AlreadyExistException(String.format("Hub Already exists with this url %s", url));
    }
    if (!infiniteCapacity && maxConnectionsAllowed <= 0) {
      throw new BadRequest("A hub should have at least maxConnectionsAllowed set to 1 or otherwise -1 to put maxConnectionsAllowed as infinite");
    }

    LuminoHub hub = infiniteCapacity ? new LuminoHub(url, true) : new LuminoHub(url, maxConnectionsAllowed);
    return this.luminoNodeRepository.save(hub);
  }

  /**
   * Gets a hub by id
   * @param id the hub id
   * @return a hub with the specified id
   * @throws NotFoundException when the hub with that id doesn't exists
   */
  public LuminoHub getHub(String id) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findById(id);
    return currentHub.orElseThrow(() -> new NotFoundException(String.format("No Lumino Hub Found with id %s", id)));
  }

  /**
   * Gets all hubs
   * @return a list of all the available hubs
   */
  public List<LuminoHub> getAllHubs() {
    return this.luminoNodeRepository.findAll();
  }

  /**
   * Updates a hub with the new parameters, it throws an exception if the newMaxConnectionsAllowed is lower than
   * the current maxConnectionsAllowed for the hub since we can't pull down that value without killing some already
   * connected clients.
   * @param id the hub id to search for
   * @param newUrl the new url for this hub
   * @param newMaxConnectionsAllowed the new max allowed connections for this hub
   * @param newInfiniteCapacity the new value for infinite capacity
   * @throws NotFoundException when the hub with that id doesn't exists
   * @throws AlreadyExistException when already exists some hub with that url
   * @throws BadRequest when the newMaxConnectionsAllowed is lower than the current value
   */
  public LuminoHub updateHub(String id, Optional<String> newUrl,
                             Optional<Long> newMaxConnectionsAllowed,
                             Optional<Boolean> newInfiniteCapacity) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findById(id);
    return currentHub.map(hub -> {
      newUrl.ifPresent(url -> {
        Optional<LuminoHub> existentHub = this.luminoNodeRepository.findByUrl(url);
        if (existentHub.isPresent()) {
          throw new AlreadyExistException(String.format("Hub Already exists with this url %s", url));
        }
        hub.setUrl(url);
      });
      if (!newInfiniteCapacity.orElse(false)) {
        newMaxConnectionsAllowed.ifPresent(maxConnectionsAllowed -> {
          if (hub.getConnectedClients().size() > maxConnectionsAllowed) {
            throw new BadRequest(String.format(
                    "Can't change a hub with newMaxConnectionsAllowed %d because is lower than the current connected client list size %d",
                    maxConnectionsAllowed,
                    hub.getConnectedClients().size()));
          }
          hub.setMaxConnectionsAllowed(maxConnectionsAllowed);
        });
        if (!newMaxConnectionsAllowed.isPresent()) {
          if (hub.getConnectedClients().size() > hub.getMaxConnectionsAllowed()) {
            hub.setMaxConnectionsAllowed(hub.getConnectedClients().size());
          }
        }
        // infinite capacity change to true
        hub.setInfiniteCapacity(false);
      } else {
        // infinite capacity change to true
        hub.setInfiniteCapacity(true);
      }
      return this.luminoNodeRepository.save(hub);
    }).orElseThrow(() -> new NotFoundException(String.format("No Lumino Hub Found with id %s", id)));
  }

  /**
   * Deletes a hub with the specified id
   * @param id the id to search for the hub
   * @throws NotFoundException when the hub with that id doesn't exists
   */
  public void deleteHub(String id) {
    Optional<LuminoHub> currentHub = this.luminoNodeRepository.findById(id);
    if (!currentHub.isPresent()) {
      throw new NotFoundException(String.format("No Lumino Hub Found with id %s", id));
    }
    this.luminoNodeRepository.deleteById(id);
  }

}
