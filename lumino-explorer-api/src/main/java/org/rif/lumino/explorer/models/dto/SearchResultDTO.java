package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Information about search results")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResultDTO {

    @ApiModelProperty(notes = "Tokens that match the search performed")
    @JsonProperty("tokens")
    List<TokenDTO> tokens;

    @ApiModelProperty(notes = "Channels that match the search performed")
    @JsonProperty("channels")
    List<ChannelDTO> channels;

    @ApiModelProperty(notes = "Nodes that match the search performed")
    @JsonProperty("nodes")
    List<LuminoNodeDTO> nodes;

    public List<TokenDTO> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenDTO> tokens) {
        this.tokens = tokens;
    }

    public List<ChannelDTO> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelDTO> channels) {
        this.channels = channels;
    }

    public List<LuminoNodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<LuminoNodeDTO> nodes) {
        this.nodes = nodes;
    }
}
