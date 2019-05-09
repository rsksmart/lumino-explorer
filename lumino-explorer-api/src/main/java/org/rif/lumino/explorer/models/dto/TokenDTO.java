package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

  @JsonProperty("address")
  private String address;

  @JsonProperty("name")
  private String name;

  @JsonProperty("network_address")
  private String networkAddress;

  @JsonProperty("symbol")
  private String symbol;

  private List<ChannelDTO> channels;

  public List<ChannelDTO> getChannels() {
    return channels;
  }

  public void setChannels(List<ChannelDTO> channels) {
    this.channels = channels;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getNetworkAddress() {
    return networkAddress;
  }

  public void setNetworkAddress(String networkAddress) {
    this.networkAddress = networkAddress;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
