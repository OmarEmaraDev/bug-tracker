package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.Project;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.InvalidReportException;
import dev.omaremara.bugtracker.util.ViewUtil;
import dev.omaremara.bugtracker.view.ReportListView;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class NewReportController {
  private String screenshotPath = "";

  public void submit(String title, String description, ReportType type,
                     ReportPriority priority, ReportLevel level,
                     Project project, User assignee, Label errorLabel) {
    try {
      Report report = new Report(Report.getReportsCount(), title, description,
                                 this.screenshotPath, type, priority, level,
                                 project, assignee, Main.user,
                                 LocalDateTime.now(), ReportStatus.OPENED);
      report.submit();
      ViewUtil.setSceneRoot(new ReportListView());
    } catch (DataBaseException | InvalidReportException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }

  public void attach(Label attachedLabel) {
    Stage stage = Main.primaryStage;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Attach Screenshot");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home") + "/Pictures"));
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
      this.screenshotPath = selectedFile.getPath();
      attachedLabel.setText(selectedFile.getName());
    }
  }

  public void cancel() { ViewUtil.setSceneRoot(new ReportListView()); }

  public List<User> getAllDevelopers(Label errorLabel) {
    try {
      return User.getAllUsers(UserRole.DEVELOPER);
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new ArrayList<User>();
  }

  public List<Project> getAllProjects(Label errorLabel) {
    try {
      return Project.getAllProjects();
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new ArrayList<Project>();
  }
}
