package org.rif.lumino.explorer.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequest extends ApiException {

  public BadRequest(String message) {
    super(message);
  }

  public BadRequest() {
    super();
  }

  public BadRequest(Throwable throwable) {
    super(throwable);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.BAD_REQUEST;
  }

}
