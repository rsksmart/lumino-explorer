package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.services.FeedService;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class FeedManager {

  @Autowired private FeedRepository feedRepository;
  @Autowired private FeedService feedService;

  public List<FeedDTO> getFeeds(Long feedId) {
    List<Feed> feeds = new ArrayList<Feed>();
    if (feedId == null) {
      feeds = feedService.getLastFeedsByIdWithLimit(10);
    } else {
      feeds = feedService.getGraterThanId(feedId);
    }

    List<FeedDTO> feedDTOS = mapFeedToDTO(feeds);

    return feedDTOS;
  }

  private List<FeedDTO> mapFeedToDTO(List<Feed> feeds) {
    List<FeedDTO> feedDTOS = new ArrayList<FeedDTO>();
    for (Feed feed : feeds) {
      FeedDTO feedDTO = new FeedDTO();
      feedDTO.setData(feed.getData());
      feedDTO.setId(feed.getId());
      feedDTO.setType(feed.getType());
      feedDTOS.add(feedDTO);
    }
    return feedDTOS;
  }
}
