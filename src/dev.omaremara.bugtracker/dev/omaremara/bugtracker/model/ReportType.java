package dev.omaremara.bugtracker.model;

public enum ReportType {
  BUG("Bug"),
  DOCUMENTATION("Documentation"),
  ENHANCEMENT("Enhancement");

  private String uiName;

  private ReportType(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
