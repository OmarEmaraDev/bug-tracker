package dev.omaremara.bugtracker.model.exception;

import java.lang.Exception;

public class InvalidUserException extends Exception {
  private static final long serialVersionUID = -6706275615836095273L;

  public InvalidUserException(String message) { super(message); }
}
