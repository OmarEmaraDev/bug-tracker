package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.Assigne;
import dev.omaremara.bugtracker.model.Project;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;

public class Report {
  public String uiName;
  public String title;
  public String description;
  public ReportLevel level;
  public ReportPriority priority;
  public ReportType type;
  public Project project;
  public User assigne;
  // function return all dispcription
  class Report
  (String uiName, String title, String description, ReportLevel level,
   ReportPriority priority, ReportType type, Project project, User assigne) {
    this.title = title;
    this.assigne = assigne;
    this.description = description;
    this.level = level;
    this.priority = priority;
    this.project = project;
    this.uiName = uiName;
    this.type = type;
  }

  @Override
  public String toString() {
    return this.uiName;
  }
}
