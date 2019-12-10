package dev.omaremara.bugtracker.model.exception;

import java.lang.Exception;

public class InvalidReportException extends Exception {
  private static final long serialVersionUID = -9217070336470109960L;

  public InvalidReportException(String message) { super(message); }
}
