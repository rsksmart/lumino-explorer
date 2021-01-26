package org.rif.lumino.explorer.managers;

import org.rif.lumino.explorer.exceptions.LuminoNodeNotFoundException;
import org.rif.lumino.explorer.helper.LuminoNodeHelper;
import org.rif.lumino.explorer.models.documents.Feed;
import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;
import org.rif.lumino.explorer.models.dto.response.GenericResponseDTO;
import org.rif.lumino.explorer.models.enums.FeedType;
import org.rif.lumino.explorer.repositories.FeedRepository;
import org.rif.lumino.explorer.repositories.LuminoNodeRepository;
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

    @Autowired
    private LuminoNodeRepository luminoNodeRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private FeedRepository feedRepository;

    public LuminoNodeDTO register(LuminoNodeDTO luminoNodeDTOParam) {

        LuminoNodeDTO luminoNodeDTOResult = luminoNodeDTOParam;

        LuminoNode luminoNode =
                luminoNodeRepository.findByNodeAddress(luminoNodeDTOParam.getNodeAddress());

        if (luminoNode == null) {
            luminoNode = new LuminoNode();
            luminoNode.setLastAliveSignal(luminoNodeDTOParam.getLastAliveSignal());
            luminoNode.setNodeAddress(luminoNodeDTOParam.getNodeAddress());
            luminoNode.setRnsAddress(luminoNodeDTOParam.getRnsAddress());
            luminoNode.setNodeChannelsIds(luminoNodeDTOParam.getNodeChannelsIds());
            luminoNodeRepository.save(luminoNode);

        } else {
            luminoNode.setLastAliveSignal(luminoNodeDTOParam.getLastAliveSignal());
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

    public void deleteNodeById(String rskAddress) {
        luminoNodeRepository.deleteById(rskAddress);
    }

    public GenericResponseDTO getNodeById(String rskAddress) {
        LuminoNode luminoNode = luminoNodeRepository.findByNodeAddress(rskAddress);
        GenericResponseDTO<LuminoNodeDTO> responseDTO = new GenericResponseDTO<>();
        if (luminoNode != null) {
            responseDTO.setSuccess(true);
            responseDTO.setMessage("Node with ID: " + luminoNode.getNodeAddress() + " was successfully retrieved");
            responseDTO.setData(LuminoNodeHelper.toDto(luminoNode));
        } else {
            throw new LuminoNodeNotFoundException(rskAddress);
        }

        return responseDTO;
    }

    public GenericResponseDTO updateNode(LuminoNodeDTO luminoNodeDTO, String nodeAddress) {
        LuminoNode luminoNode = luminoNodeRepository.findByNodeAddress(nodeAddress);
        GenericResponseDTO<LuminoNodeDTO> responseDTO = new GenericResponseDTO<LuminoNodeDTO>();
        if (luminoNode != null) {
            luminoNode.setRnsAddress(luminoNodeDTO.getRnsAddress());
            luminoNode.setNodeAddress(luminoNodeDTO.getNodeAddress());
            luminoNode.setLastAliveSignal(luminoNodeDTO.getLastAliveSignal());

            luminoNodeRepository.save(luminoNode);
            responseDTO.setData(luminoNodeDTO);
            responseDTO.setMessage("Node with ID: " + luminoNode.getNodeAddress() + " was successfully updated");
            responseDTO.setSuccess(true);
        } else {
            throw new LuminoNodeNotFoundException(nodeAddress);
        }

        return responseDTO;
    }
}
