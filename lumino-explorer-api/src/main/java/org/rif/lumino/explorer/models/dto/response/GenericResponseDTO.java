package org.rif.lumino.explorer.models.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Generic api endpoint response. ")
public class GenericResponseDTO<T> {

    @ApiModelProperty(notes = "The result of the operation")
    private boolean success;

    @ApiModelProperty(notes = "Message detail of response operation")
    private String message;

    @ApiModelProperty(notes = "Meta data, this can be any data")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
