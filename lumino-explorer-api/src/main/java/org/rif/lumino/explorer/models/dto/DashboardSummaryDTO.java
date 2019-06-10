package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Summary information about tokens, nodes and channels")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardSummaryDTO {

    @ApiModelProperty(notes = "Quantity of registered tokens")
    @JsonProperty("token_nodes")
    private Integer tokenNodes;

    @ApiModelProperty(notes = "Quantity of registered nodes")
    @JsonProperty("lumino_nodes")
    private Long luminoNodes;

    @ApiModelProperty(notes = "Number of tokens with at least one open channel")
    @JsonProperty("token_channels")
    private Integer tokenChannels;

    @ApiModelProperty(notes = "List of all tokens")
    @JsonProperty("lumino_channels")
    private Long luminoChannels;

    public Integer getTokenNodes() {
        return tokenNodes;
    }

    public void setTokenNodes(Integer tokenNodes) {
        this.tokenNodes = tokenNodes;
    }

    public Long getLuminoNodes() {
        return luminoNodes;
    }

    public void setLuminoNodes(Long luminoNodes) {
        this.luminoNodes = luminoNodes;
    }

    public Integer getTokenChannels() {
        return tokenChannels;
    }

    public void setTokenChannels(Integer tokenChannels) {
        this.tokenChannels = tokenChannels;
    }

    public Long getLuminoChannels() {
        return luminoChannels;
    }

    public void setLuminoChannels(Long luminoChannels) {
        this.luminoChannels = luminoChannels;
    }
}
