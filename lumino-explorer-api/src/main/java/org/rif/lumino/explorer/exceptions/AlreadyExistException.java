package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends ApiException {

  public AlreadyExistException(String message) {
    super(message);
  }

  public AlreadyExistException() {
    super();
  }

  public AlreadyExistException(Throwable throwable) {
    super(throwable);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.CONFLICT;
  }
}
