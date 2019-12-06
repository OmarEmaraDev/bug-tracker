package dev.omaremara.bugtracker.model;

public enum UserRole{
    TESTER,
    DEVELOPER,
    PROJECTMANGER,
    ADMINISTRATOR;

    private String uiName;

    private UserRole(String uiName){
        this.uiName = uiName;
    }

    @Override
    public String toString() {
        return this.uiName;
    }
}
