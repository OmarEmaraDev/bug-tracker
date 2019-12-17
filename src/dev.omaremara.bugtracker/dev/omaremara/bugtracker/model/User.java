package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.InvalidUserException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public void validateUser() throws InvalidUserException {
    if (this.name.isBlank()) {
      throw new InvalidUserException("Empty name!");
    }
    if (this.email.isBlank()) {
      throw new InvalidUserException("Empty email!");
    }
    if (this.password.isBlank()) {
      throw new InvalidUserException("Empty password!");
    }
    if (this.role == null) {
      throw new InvalidUserException("Invalid role!");
    }
  }

  public void submit() throws DataBaseException, InvalidUserException {
    validateUser();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query =
          "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, this.name);
        statement.setString(2, this.email);
        statement.setString(3, this.password);
        statement.setString(4, this.role.name());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new DataBaseException("Could not add user to database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
  }

  public void delete() throws DataBaseException {
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query = "DELETE FROM users WHERE email = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, this.email);
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new DataBaseException("Could not delete user from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
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

  public static List<User> getAllUsers(UserRole role) throws DataBaseException {
    List<User> users = new ArrayList<User>();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      StringBuilder queryBuilder = new StringBuilder("SELECT * FROM users ");
      if (role != null) {
        queryBuilder.append("WHERE role = ?");
      }
      String query = queryBuilder.toString();
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        if (role != null) {
          statement.setString(1, role.name());
        }
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

  public static Map<User, Integer> getUsersStats() throws DataBaseException {
    Map<User, Integer> stats = new HashMap<User, Integer>();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      try (Statement statement = connection.createStatement()) {
        String query =
            "SELECT users.*, COUNT(*) AS count FROM users INNER JOIN reports "
            + "ON ((email = assignee AND status = 'CLOSED') OR email = author) "
            + "GROUP BY name, email, password, role";
        try (ResultSet result = statement.executeQuery(query)) {
          while (result.next()) {
            stats.put(User.getFromResultSet(result), result.getInt("count"));
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
    return stats;
  }
}
