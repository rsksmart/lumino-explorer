package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public class LuminoNodeNotFoundException extends ApiException {

    public LuminoNodeNotFoundException(String nodeId) {
        super(String.format("Could not find node: %s",nodeId));
    }

    public LuminoNodeNotFoundException() {
        super();
    }

    public LuminoNodeNotFoundException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
