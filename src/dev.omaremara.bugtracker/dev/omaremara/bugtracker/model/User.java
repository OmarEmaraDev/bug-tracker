package dev.omaremara.bugtracker.model;

public class User {
    private String email;
    private String password;
    private UserRole userRole;
    private String name;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserRole() {
        return userRole;
    }

    //check email return 1 or 0
    public int checkUser(String email){

    }

    public String toString(){
        return this.name;
    }
}
