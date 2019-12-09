package dev.omaremara.bugtracker.model;

public enum UserRole{
    TESTER("Tester"),
    DEVELOPER("Developer"),
    PROJECT_MANGER("Project Manger"),
    ADMINISTRATOR("Administrator");

    private String uiName;

    private UserRole(String uiName){
        this.uiName = uiName;
    }

    @Override
    public String toString() {
        return this.uiName;
    }
}
