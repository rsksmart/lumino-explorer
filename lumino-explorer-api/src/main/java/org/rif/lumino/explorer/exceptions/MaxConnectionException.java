package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public class MaxConnectionException extends ApiException {
  public MaxConnectionException(String message) {
    super(message);
  }

  public MaxConnectionException() {
    super();
  }

  public MaxConnectionException(Throwable throwable) {
    super(throwable);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.CONFLICT;
  }
}
