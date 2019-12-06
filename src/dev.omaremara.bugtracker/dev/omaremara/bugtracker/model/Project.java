package dev.omaremara.bugtracker.model;

public class Project {

    private  String Name;

    private Project(String Name) {
        this.uiName = Name;
    }

    public String toString() {
        return this.Name;
    }
}
