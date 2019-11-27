package dev.omaremara.bugtracker.model;

public enum ReportLevel {
  USER("User"),
  API("API"),
  ABI("ABI"),
  KERNEL("Kernel");

  private String uiName;

  private ReportLevel(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
