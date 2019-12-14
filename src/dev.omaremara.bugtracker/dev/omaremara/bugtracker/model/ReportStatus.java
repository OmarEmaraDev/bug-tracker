package dev.omaremara.bugtracker.model;

public enum ReportStatus {
  OPENED("Opened"),
  CLOSED("Closed");

  private String uiName;

  private ReportStatus(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
