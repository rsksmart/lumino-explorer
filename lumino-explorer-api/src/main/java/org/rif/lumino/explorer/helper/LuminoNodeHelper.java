package org.rif.lumino.explorer.helper;

import org.rif.lumino.explorer.models.documents.LuminoNode;
import org.rif.lumino.explorer.models.dto.LuminoNodeDTO;

public class LuminoNodeHelper {

    public static LuminoNodeDTO toDto(LuminoNode luminoNode) {
        LuminoNodeDTO luminoNodeDTO = new LuminoNodeDTO();
        luminoNodeDTO.setNodeAddress(luminoNode.getNodeAddress());
        luminoNodeDTO.setLastAliveSignal(luminoNode.getLastAliveSignal());
        luminoNodeDTO.setNodeChannelsIds(luminoNode.getNodeChannelsIds());
        luminoNodeDTO.setRnsAddress(luminoNode.getRnsAddress());
        return luminoNodeDTO;
    }

}
