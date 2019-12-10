package dev.omaremara.bugtracker.model;

public enum ReportStatus {
  OPEN("Open"),
  CLOSED("Closed"),
  INVALID("Invalid");

  private String uiName;

  private ReportStatus(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
