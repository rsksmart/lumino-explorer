package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ApiModel(description = "Information about Lumino node")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LuminoNodeDTO {

  @ApiModelProperty(notes = "The rsk address of node")
  @NotEmpty(message = "nodeAddress can not be empty")
  @JsonProperty("node_address")
  private String nodeAddress;

  @ApiModelProperty(notes = "The endpoint to know if the node is alive")
  @JsonProperty("node_endpoint")
  private String nodeEndpoint;

  @ApiModelProperty(notes = "The rns address of node")
  @JsonProperty("rns_address")
  private String rnsAddress;

  @ApiModelProperty(notes = "The channel id list, to identifie a list of channels associated with node")
  @JsonProperty("node_channels_ids")
  private List<String> nodeChannelsIds;

  @ApiModelProperty(notes = "Complete list of channels informations associated with node")
  @JsonProperty("channels")
  private List<ChannelDTO> channels;

  @ApiModelProperty(notes = "Last alive signal what node send to the exlporer api")
  @JsonProperty("last_alive_signal")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date lastAliveSignal;

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

  public Date getLastAliveSignal() {
    return lastAliveSignal;
  }

  public void setLastAliveSignal(Date lastAliveSignal) {
    this.lastAliveSignal = lastAliveSignal;
  }
}
