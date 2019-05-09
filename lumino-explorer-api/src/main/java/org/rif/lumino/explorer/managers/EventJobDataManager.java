package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.constants.LuminoExplorerConstants;
import org.rif.lumino.explorer.models.documents.EventJobMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.rif.lumino.explorer.repositories.EventJobDataRepository;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class EventJobDataManager {

  @Autowired private EventJobDataRepository eventJobDataRepository;

  public EventJobMetadata updateLastSyncBlockChannels(BigInteger lastSyncBlock) {
    List<EventJobMetadata> syncDataList = eventJobDataRepository.findAll();
    Optional<EventJobMetadata> eventJobData =
        syncDataList.stream().min(Comparator.comparing(a -> a.getLastSyncBlockChannels()));
    if (eventJobData.isPresent()) {
      EventJobMetadata syncDataInstance = eventJobData.get();
      syncDataInstance.setLastSyncBlockChannels(lastSyncBlock);

      eventJobDataRepository.save(syncDataInstance);
      return syncDataInstance;
    } else {
      // doesnt exists, latest should be 0
      EventJobMetadata toInsert =
          new EventJobMetadata(
              "1",
              LuminoExplorerConstants.BIG_INTEGER_ZERO,
              LuminoExplorerConstants.BIG_INTEGER_ZERO,
              5000);
      EventJobMetadata result = eventJobDataRepository.insert(toInsert);
      return result;
    }
  }

  public EventJobMetadata updateLastSyncBlockTokens(BigInteger lastSyncBlock) {
    List<EventJobMetadata> syncDataList = eventJobDataRepository.findAll();
    Optional<EventJobMetadata> eventJobData =
        syncDataList.stream().min(Comparator.comparing(a -> a.getLastSyncBlockTokens()));
    if (eventJobData.isPresent()) {
      EventJobMetadata syncDataInstance = eventJobData.get();
      syncDataInstance.setLastSyncBlockTokens(lastSyncBlock);

      eventJobDataRepository.save(syncDataInstance);
      return syncDataInstance;
    } else {
      // doesnt exists, latest should be 0
      EventJobMetadata toInsert =
          new EventJobMetadata(
              "1",
              LuminoExplorerConstants.BIG_INTEGER_ZERO,
              LuminoExplorerConstants.BIG_INTEGER_ZERO,
              5000);
      EventJobMetadata result = eventJobDataRepository.insert(toInsert);
      return result;
    }
  }

  public EventJobMetadata getEventJobData() {
    Optional<EventJobMetadata> lastData = eventJobDataRepository.findById("1");
    return lastData.orElseThrow(() -> new RuntimeException("No EventJobMetadata .."));
  }
}
