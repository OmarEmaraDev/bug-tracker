package dev.omaremara.bugtracker.model.exception;

import java.lang.Exception;

public class LoginException extends Exception {
  private static final long serialVersionUID = -1903243429543597697L;

  public LoginException(String message) { super(message); }
}
