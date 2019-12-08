package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import java.util.ArrayList;
import java.util.List;

public class User {
  public int id;
  public String name;
  public String email;
  public String password;
  public UserRole role;

  public User(int id, String name, String email, String password,
              UserRole role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public static List<User> getAllDevelopers() {
    List<User> developers = new ArrayList<User>();
    developers.add(new User(0, "Omar", "mail@OmarEmara.dev", "password",
                            UserRole.DEVELOPER));
    developers.add(new User(1, "Karim", "karim@gmail.com", "password",
                            UserRole.DEVELOPER));
    developers.add(new User(2, "Mohamed", "mohamed@gmail.com", "password",
                            UserRole.DEVELOPER));
    return developers;
  }
}
