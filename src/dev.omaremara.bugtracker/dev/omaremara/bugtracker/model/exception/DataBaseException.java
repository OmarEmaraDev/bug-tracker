package dev.omaremara.bugtracker.model.exception;

import java.lang.Exception;
import java.lang.Throwable;

public class DataBaseException extends Exception {
  private static final long serialVersionUID = -3772896768185064821L;

  public DataBaseException(String message) { super(message); }
  public DataBaseException(String message, Throwable cause) {
    super(message, cause);
  }
}
