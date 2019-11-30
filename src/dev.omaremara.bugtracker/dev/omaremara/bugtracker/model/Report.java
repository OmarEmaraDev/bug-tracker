package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.model.Assigne;
import dev.omaremara.bugtracker.model.Project;

public class Report {
    String title;
    String description;
    ReportLevel level;
    ReportPriority priority;
    ReportType type;
    Project project;
    Assigne assigne;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(ReportLevel level) {
        this.level = level;
    }

    public void setAssigne(Assigne assigne) {
        this.assigne = assigne;
    }

    public void setPriority(ReportPriority priority) {
        this.priority = priority;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public ReportLevel getLevel() {
        return level;
    }

    public Assigne getAssigne() {
        return assigne;
    }

    public Project getProject() {
        return project;
    }

    public ReportPriority getPriority() {
        return priority;
    }

    public ReportType getType() {
        return type;
    }
}