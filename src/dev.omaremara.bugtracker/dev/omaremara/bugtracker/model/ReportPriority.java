package dev.omaremara.bugtracker.model;

public enum ReportPriority {
  BLOCKER("Blocker"),
  CRITICAL("Critical"),
  MAJOR("Major"),
  MINOR("Minor"),
  TRIVIAL("Trivial");

  private String uiName;

  private ReportPriority(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
