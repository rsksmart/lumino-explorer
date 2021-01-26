package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.rif.lumino.explorer.models.documents.LuminoHub;

@ApiModel(description = "All details about the Lumino Hub. ")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LuminoHubDTO {

    public LuminoHubDTO() {}

    public LuminoHubDTO(String url) {
        this.url = url;
    }

    @ApiModelProperty(notes = "The Lumino Hub url")
    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static LuminoHubDTO fromLuminoHub(LuminoHub luminoHub) {
        return new LuminoHubDTO(luminoHub.getUrl());
    }
}
