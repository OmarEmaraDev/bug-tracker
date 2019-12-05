package dev.omaremara.bugtracker.model;

public enum Project {
    project1("project1"),
    project2("project2"),
    project3("project3");

    private final String uiName;

    private Project(String uiName) {
        this.uiName = uiName;
    }

    public String toString() {
        return this.uiName;
    }
}