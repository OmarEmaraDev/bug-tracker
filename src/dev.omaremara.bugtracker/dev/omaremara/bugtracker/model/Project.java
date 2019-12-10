package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.exception.DataBaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Project {
  public String name;

  public Project(String name) { this.name = name; }

  public String toString() { return this.name; }

  public static List<Project> getAllProjects() throws DataBaseException {
    List<Project> projects = new ArrayList<Project>();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      try (Statement statement = connection.createStatement()) {
        String query = "SELECT * FROM projects";
        try (ResultSet result = statement.executeQuery(query)) {
          while (result.next()) {
            String name = result.getString("name");
            projects.add(new Project(name));
          }
        } catch (SQLException exception) {
          throw new DataBaseException(
              "Could not retrive projects from database!", exception);
        }
      } catch (SQLException exception) {
        throw new DataBaseException("Could not query projects from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
    return projects;
  }
}
