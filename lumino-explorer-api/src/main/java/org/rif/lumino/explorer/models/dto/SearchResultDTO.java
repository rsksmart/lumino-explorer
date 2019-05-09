package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResultDTO {

  @JsonProperty("tokens")
  List<TokenDTO> tokens;

  @JsonProperty("channels")
  List<ChannelDTO> channels;

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
