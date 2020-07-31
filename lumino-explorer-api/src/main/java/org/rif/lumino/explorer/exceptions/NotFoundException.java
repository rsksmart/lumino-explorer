package org.rif.lumino.explorer.exceptions;

public class NotFoundException extends RuntimeException
{

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException() {
  }

  public NotFoundException(Throwable throwable) {
    super(throwable);
  }
}
