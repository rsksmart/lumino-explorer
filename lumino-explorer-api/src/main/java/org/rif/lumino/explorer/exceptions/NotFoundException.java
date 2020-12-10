package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException() {
    super();
  }

  public NotFoundException(Throwable throwable) {
    super(throwable);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
