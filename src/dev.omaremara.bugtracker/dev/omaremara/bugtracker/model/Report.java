package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.Project;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.InvalidReportException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Report {
  public int id;
  public String title;
  public String description;
  public String screenshotPath;
  public ReportType type;
  public ReportPriority priority;
  public ReportLevel level;
  public Project project;
  public User assignee;
  public LocalDateTime date;
  public ReportStatus status;

  public Report(int id, String title, String description, String screenshotPath,
                ReportType type, ReportPriority priority, ReportLevel level,
                Project project, User assignee, LocalDateTime date,
                ReportStatus status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.screenshotPath = screenshotPath;
    this.type = type;
    this.priority = priority;
    this.level = level;
    this.project = project;
    this.assignee = assignee;
    this.date = date;
    this.status = status;
  }

  public void validateReport() throws InvalidReportException {
    if (this.title.isBlank()) {
      throw new InvalidReportException("Title is empty!");
    }
    if (this.description.isBlank()) {
      throw new InvalidReportException("Description is empty!");
    }
  }

  public static int getReportsCount() throws DataBaseException {
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      try (Statement statement = connection.createStatement()) {
        String query = "SELECT COUNT (*) FROM reports";
        try (ResultSet result = statement.executeQuery(query)) {
          result.next();
          return result.getInt("count");
        } catch (SQLException exception) {
          throw new DataBaseException(
              "Could not retrive reports from database!", exception);
        }
      } catch (SQLException exception) {
        throw new DataBaseException("Could not query reports from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
  }

  public void submit() throws DataBaseException, InvalidReportException {
    validateReport();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query =
          "INSERT INTO reports VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, this.id);
        statement.setString(2, this.title);
        statement.setString(3, this.description);
        statement.setString(4, this.screenshotPath);
        statement.setString(5, this.type.name());
        statement.setString(6, this.priority.name());
        statement.setString(7, this.level.name());
        statement.setString(8, this.project.name);
        statement.setString(9, this.assignee.email);
        statement.setObject(10, this.date);
        statement.setString(11, this.status.name());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new DataBaseException("Could not add report to database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
  }
}
