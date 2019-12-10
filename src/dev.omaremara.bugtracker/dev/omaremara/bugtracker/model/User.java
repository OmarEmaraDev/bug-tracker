package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
  public String name;
  public String email;
  public String password;
  public UserRole role;

  public User(String name, String email, String password, UserRole role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public static User getFromResultSet(ResultSet result)
      throws DataBaseException {
    try {
      String name = result.getString("name");
      String email = result.getString("email");
      String password = result.getString("password");
      UserRole role = UserRole.valueOf(result.getString("role"));
      return new User(name, email, password, role);
    } catch (SQLException exception) {
      throw new DataBaseException("Invalid database attributes!", exception);
    }
  }

  public static User getFromLogin(String email)
      throws DataBaseException, LoginException {
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
            return User.getFromResultSet(result);
          }
        } catch (SQLException exception) {
          throw new DataBaseException("Could not retrive user from database!",
                                      exception);
        }
      } catch (SQLException exception) {
        throw new DataBaseException("Could not query user from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
  }

  public static User getFromLogin(String email, String password)
      throws LoginException, DataBaseException {
    User user = User.getFromLogin(email);
    if (password.equals(user.password)) {
      return user;
    } else {
      throw new LoginException("Invalid password!");
    }
  }

  public static List<User> getAllUsersWithRole(UserRole role)
      throws DataBaseException {
    List<User> users = new ArrayList<User>();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query = "SELECT * FROM users WHERE role = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, role.name());
        try (ResultSet result = statement.executeQuery()) {
          while (result.next()) {
            users.add(User.getFromResultSet(result));
          }
        } catch (SQLException exception) {
          throw new DataBaseException("Could not retrive users from database!",
                                      exception);
        }
      } catch (SQLException exception) {
        throw new DataBaseException("Could not query users from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
    return users;
  }
}
