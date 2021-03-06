package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "All Information to build a graph of nodes with channels, left sidebar summary, and list all tokens")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDTO {

    @ApiModelProperty(notes = "List of all tokens")
    @JsonProperty("tokens")
    private List<TokenDTO> tokensDTO;

    @ApiModelProperty(notes = "List of only open channels")
    @JsonProperty("channels")
    private List<ChannelDTO> channelsDTO;

    @ApiModelProperty(notes = "Information to show a left sidebar summary")
    @JsonProperty("summary")
    private DashboardSummaryDTO dashboardSummaryDTO;

    @ApiModelProperty(notes = "All registered nodes in the topology")
    @JsonProperty("nodes")
    private List<LuminoNodeDTO> luminoNodeDTO;

    public DashboardDTO() {
        this.tokensDTO = new ArrayList<>();
        this.channelsDTO = new ArrayList<>();
        this.dashboardSummaryDTO = new DashboardSummaryDTO();
        this.luminoNodeDTO = new ArrayList<>();
    }

    public List<LuminoNodeDTO> getLuminoNodeDTO() {
        return luminoNodeDTO;
    }

    public void setLuminoNodeDTO(List<LuminoNodeDTO> luminoNodeDTO) {
        this.luminoNodeDTO = luminoNodeDTO;
    }

    public List<TokenDTO> getTokensDTO() {
        return tokensDTO;
    }

    public void setTokensDTO(List<TokenDTO> tokensDTO) {
        this.tokensDTO = tokensDTO;
    }

    public List<ChannelDTO> getChannelsDTO() {
        return channelsDTO;
    }

    public void setChannelsDTO(List<ChannelDTO> channelsDTO) {
        this.channelsDTO = channelsDTO;
    }

    public DashboardSummaryDTO getDashboardSummaryDTO() {
        return dashboardSummaryDTO;
    }

    public void setDashboardSummaryDTO(DashboardSummaryDTO dashboardSummaryDTO) {
        this.dashboardSummaryDTO = dashboardSummaryDTO;
    }
}
