package dev.omaremara.bugtracker.model;
import dev.omaremara.bugtracker.model.UserRole

public class User {
  public String email;
  public String password;
  public UserRole userRole;
  public String name;

  class User
  (String email, String password, UserRole userRole, String name) {
    this.email = email;
    this.password = password;
    this.userRolec = userRole;
    this.name = name;
  }

  public String toString() { return this.name; }
}
