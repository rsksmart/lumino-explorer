package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Information about token. This token follows the ERC-20 standard")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

    @ApiModelProperty(notes = "Rsk address of token")
    @JsonProperty("address")
    private String address;

    @ApiModelProperty(notes = "Name of token")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(notes = "Rsk network address to which the token belongs")
    @JsonProperty("network_address")
    private String networkAddress;

    @ApiModelProperty(notes = "The symbol associated with token")
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
