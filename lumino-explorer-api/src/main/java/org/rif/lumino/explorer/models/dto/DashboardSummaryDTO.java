package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardSummaryDTO {

    @JsonProperty("token_nodes")
    private Integer tokenNodes;
    @JsonProperty("lumino_nodes")
    private Long luminoNodes;
    @JsonProperty("token_channels")
    private Integer tokenChannels;
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
