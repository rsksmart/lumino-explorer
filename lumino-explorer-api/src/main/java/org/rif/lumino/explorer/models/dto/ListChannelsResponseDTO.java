package org.rif.lumino.explorer.models.dto;

import java.util.ArrayList;
import java.util.List;

public class ListChannelsResponseDTO {

    private List<ChannelDTO> channels = new ArrayList<>();

    public List<ChannelDTO> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelDTO> channels) {
        this.channels = channels;
    }
}
