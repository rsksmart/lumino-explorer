package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Channel. ")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {

    @ApiModelProperty(notes = "The channel identifier")
    @JsonProperty("channel_identifier")
    private String channelIdentifier;

    @ApiModelProperty(notes = "The from rsk address node")
    @JsonProperty("from_address")
    private String fromAddress;

    @ApiModelProperty(notes = "The from rns address node")
    @JsonProperty("from_rns_address")
    private String fromRnsAddress;

    @ApiModelProperty(notes = "The to rns address node")
    @JsonProperty("to_rns_address")
    private String toRnsAddress;

    @ApiModelProperty(notes = "The to rsk address node")
    @JsonProperty("to_address")
    private String toAddress;

    @ApiModelProperty(notes = "The state of the channel")
    @JsonProperty("state")
    private String state;

    @ApiModelProperty(notes = "The network identifier that identifies a set of tokens")
    @JsonProperty("token_network_address")
    private String tokenNetworkAddress;

    @ApiModelProperty(notes = "The token name with the channel is associated")
    @JsonProperty("token_name")
    private String tokenName;

    @ApiModelProperty(notes = "The rsk token address")
    @JsonProperty("token_address")
    private String tokenAddress;

    @ApiModelProperty(notes = "The symbol that is associated with the channel token")
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
