package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {

  @JsonProperty("channel_identifier")
  private String channelIdentifier;

  @JsonProperty("from_address")
  private String fromAddress;

  @JsonProperty("from_rns_address")
  private String fromRnsAddress;

  @JsonProperty("to_rns_address")
  private String toRnsAddress;

  @JsonProperty("to_address")
  private String toAddress;

  @JsonProperty("state")
  private String state;

  @JsonProperty("token_network_address")
  private String tokenNetworkAddress;

  @JsonProperty("token_name")
  private String tokenName;

  @JsonProperty("token_address")
  private String tokenAddress;

  @JsonProperty("token_symbol")
  private String tokenSymbol;

  public String getFromRnsAddress() {
    return fromRnsAddress;
  }

  public void setFromRnsAddress(String fromRnsAddress) {
    this.fromRnsAddress = fromRnsAddress;
  }

  public String getToRnsAddress() {
    return toRnsAddress;
  }

  public void setToRnsAddress(String toRnsAddress) {
    this.toRnsAddress = toRnsAddress;
  }

  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }

  public String getTokenAddress() {
    return tokenAddress;
  }

  public void setTokenAddress(String tokenAddress) {
    this.tokenAddress = tokenAddress;
  }

  public String getTokenSymbol() {
    return tokenSymbol;
  }

  public void setTokenSymbol(String tokenSymbol) {
    this.tokenSymbol = tokenSymbol;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getTokenNetworkAddress() {
    return tokenNetworkAddress;
  }

  public void setTokenNetworkAddress(String tokenNetworkAddress) {
    this.tokenNetworkAddress = tokenNetworkAddress;
  }

  public String getChannelIdentifier() {
    return channelIdentifier;
  }

  public void setChannelIdentifier(String channelIdentifier) {
    this.channelIdentifier = channelIdentifier;
  }

  public String getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public String getToAddress() {
    return toAddress;
  }

  public void setToAddress(String toAddress) {
    this.toAddress = toAddress;
  }
}
