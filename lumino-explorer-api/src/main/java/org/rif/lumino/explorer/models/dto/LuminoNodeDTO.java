package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LuminoNodeDTO {

  @NotEmpty(message = "nodeAddress can not be empty")
  @JsonProperty("node_address")
  private String nodeAddress;

  @JsonProperty("node_endpoint")
  private String nodeEndpoint;

  @JsonProperty("rns_address")
  private String rnsAddress;

  @JsonProperty("node_channels_ids")
  private List<String> nodeChannelsIds;

  @JsonProperty("channels")
  private List<ChannelDTO> channels;

  public List<ChannelDTO> getChannels() {
    return channels;
  }

  public void setChannels(List<ChannelDTO> channels) {
    this.channels = channels;
  }

  public String getNodeAddress() {
    return nodeAddress;
  }

  public void setNodeAddress(String nodeAddress) {
    this.nodeAddress = nodeAddress;
  }

  public String getNodeEndpoint() {
    return nodeEndpoint;
  }

  public void setNodeEndpoint(String nodeEndpoint) {
    this.nodeEndpoint = nodeEndpoint;
  }

  public String getRnsAddress() {
    return rnsAddress;
  }

  public void setRnsAddress(String rnsAddress) {
    this.rnsAddress = rnsAddress;
  }

  public List<String> getNodeChannelsIds() {
    return nodeChannelsIds;
  }

  public void setNodeChannelsIds(List<String> nodeChannelsIds) {
    this.nodeChannelsIds = nodeChannelsIds;
  }
}
