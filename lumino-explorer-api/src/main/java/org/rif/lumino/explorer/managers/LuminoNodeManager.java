package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.repositories.LuminoNodeRepository;
import org.rif.lumino.explorer.services.ChannelService;
import org.rif.lumino.explorer.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class LuminoNodeManager {

  @Autowired private LuminoNodeRepository luminoNodeRepository;
  @Autowired private ChannelService channelService;
  @Autowired private CommonService commonService;
  @Autowired private FeedRepository feedRepository;

  public LuminoNodeDTO register(LuminoNodeDTO luminoNodeDTOParam) {

    LuminoNodeDTO luminoNodeDTOResult = luminoNodeDTOParam;

    LuminoNode luminoNode =
        luminoNodeRepository.findByNodeAddress(luminoNodeDTOParam.getNodeAddress());

    if (luminoNode == null) {
      luminoNode = new LuminoNode();
      luminoNode.setNodeAddress(luminoNodeDTOParam.getNodeAddress());
      luminoNode.setNodeEndpoint(luminoNodeDTOParam.getNodeEndpoint());
      luminoNode.setRnsAddress(luminoNodeDTOParam.getRnsAddress());
      luminoNode.setNodeChannelsIds(luminoNodeDTOParam.getNodeChannelsIds());
      luminoNodeRepository.save(luminoNode);

    } else {
      // Update latest rns domain value
      luminoNode.setRnsAddress(luminoNodeDTOParam.getRnsAddress());
      luminoNodeRepository.save(luminoNode);
    }

    Map<String, String> dataNode = new HashMap<String, String>();
    dataNode.put("address", luminoNode.getNodeAddress());
    dataNode.put("rns_address", luminoNode.getRnsAddress());
    Feed feed = commonService.mapNewFeed(FeedType.NODE, dataNode);
    feedRepository.save(feed);

    return luminoNodeDTOResult;
  }

  public List<LuminoNode> getAll() {
    return luminoNodeRepository.findAll();
  }

  public Long count() {
    return luminoNodeRepository.count();
  }

  public List<LuminoNodeDTO> getNodes() {
    List<LuminoNodeDTO> luminoNodeDTOS = commonService.mapLuminoNodesDTO(getAll());

    return luminoNodeDTOS;
  }
}
