package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.Project;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.InvalidReportException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

  public void updateStatus(ReportStatus newStatus) throws DataBaseException {
    if (newStatus.equals(this.status)) {
      return;
    }
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query = "UPDATE reports SET status = ? WHERE id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, newStatus.name());
        statement.setInt(2, this.id);
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new DataBaseException("Could not query report from database!",
                                    exception);
      }
    } catch (SQLException exception) {
      throw new DataBaseException("Could not establish connection to database!",
                                  exception);
    }
    this.status = newStatus;
  }

  public static Report getFromResultSet(ResultSet result)
      throws DataBaseException, LoginException {
    try {
      int id = result.getInt("id");
      String title = result.getString("title");
      String description = result.getString("description");
      String screenshotPath = result.getString("screenshotPath");
      ReportType type = ReportType.valueOf(result.getString("type"));
      ReportPriority priority =
          ReportPriority.valueOf(result.getString("priority"));
      ReportLevel level = ReportLevel.valueOf(result.getString("level"));
      Project project = new Project(result.getString("project"));
      User user = User.getFromLogin(result.getString("assignee"));
      LocalDateTime date = result.getObject("date", LocalDateTime.class);
      ReportStatus status = ReportStatus.valueOf(result.getString("status"));
      return new Report(id, title, description, screenshotPath, type, priority,
                        level, project, user, date, status);
    } catch (SQLException exception) {
      throw new DataBaseException("Invalid database attributes!", exception);
    }
  }

  public static List<Report> getAllReports()
      throws DataBaseException, LoginException {
    List<Report> reports = new ArrayList<Report>();
    String connectionURL = System.getProperty("JDBC.connection.url");
    try (Connection connection = DriverManager.getConnection(connectionURL)) {
      String query = "SELECT * FROM reports ORDER BY id";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        try (ResultSet result = statement.executeQuery()) {
          while (result.next()) {
            reports.add(Report.getFromResultSet(result));
          }
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
    return reports;
  }
}
