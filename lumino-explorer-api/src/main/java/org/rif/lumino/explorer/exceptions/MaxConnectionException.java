package org.rif.lumino.explorer.exceptions;

public class MaxConnectionException extends RuntimeException
{

  public MaxConnectionException(String message) {
    super(message);
  }

  public MaxConnectionException() {
  }

  public MaxConnectionException(Throwable throwable) {
    super(throwable);
  }
}
