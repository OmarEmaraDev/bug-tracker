package dev.omaremara.bugtracker.model;

public enum Assigne {
    assigne1("assigne1"),
    assigne2("assigne2"),
    assigne3("assigne3");
    private final String uiName;

    private Assigne(String uiName) {
        this.uiName = uiName;
    }

    public String toString() {
        return this.uiName;
    }
}