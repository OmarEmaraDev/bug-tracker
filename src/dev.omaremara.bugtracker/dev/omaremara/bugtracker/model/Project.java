package dev.omaremara.bugtracker.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
  public int id;
  public String name;

  public Project(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String toString() { return this.name; }

  public static List<Project> getAllProjects() {
    List<Project> projects = new ArrayList<Project>();
    projects.add(new Project(0, "Bug Tracker"));
    projects.add(new Project(1, "JDBC Driver"));
    projects.add(new Project(2, "OpenJFX"));
    return projects;
  }
}
