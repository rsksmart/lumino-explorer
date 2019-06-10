package org.rif.lumino.explorer.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel(description = "Information about feeds, these represent a new event into explorer. " +
        "Which can be of the type, token, node or channel, each with its own data")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedDTO {

    @ApiModelProperty(notes = "The unique feed identifier")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(notes = "The type of feed, wich can be of type: token, node or channel")
    @JsonProperty("type")
    private String type;

    @ApiModelProperty(notes = "The data is a map, wich contain a custom data per type")
    @JsonProperty("data")
    private Map data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
