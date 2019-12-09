package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import dev.omaremara.bugtracker.model.exception.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

  public static User getUser(String email, String password)
      throws LoginException {
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query = "SELECT * FROM users WHERE email = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, email);
        try (ResultSet result = statement.executeQuery()) {
          if (!result.isBeforeFirst()) {
            throw new LoginException("No user exist with this email!");
          } else {
            result.next();
            String resultPassword = result.getString("password");
            if (password.equals(resultPassword)) {
              int id = result.getInt("id");
              String name = result.getString("name");
              UserRole role = UserRole.valueOf(result.getString("role"));
              return new User(id, name, email, password, role);
            } else {
              throw new LoginException("Invalid password!");
            }
          }
        } catch (SQLException exception) {
          throw new LoginException("Could not retrive user from database!");
        }
      } catch (SQLException exception) {
        throw new LoginException("Could not query user from database!");
      }
    } catch (SQLException exception) {
      throw new LoginException("Could not establish connection to database!");
    }
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
