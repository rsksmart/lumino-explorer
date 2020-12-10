package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super();
    }
    public ApiException(Throwable throwable) {
        super(throwable);
    }
    public abstract HttpStatus getStatus();
}
