package dev.omaremara.bugtracker.model;

public class Project {

    private  String name;

    public Project(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
