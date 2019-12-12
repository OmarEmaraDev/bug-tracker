package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.ReportListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReportController {
  public void back() {
    Stage stage = Main.primaryStage;
    Scene reportListScene = new ReportListView().getScene();
    stage.setScene(reportListScene);
  }

  public void logOut() {
    Stage stage = Main.primaryStage;
    Scene loginScene = new LoginView().getScene();
    stage.setScene(loginScene);
  }

  public void toggleStatus(Report report, Button toggleButton,
                           Label errorLabel) {
    try {
      if (report.status.equals(ReportStatus.OPEN)) {
        report.updateStatus(ReportStatus.CLOSED);
        toggleButton.setText("Reopen");
      } else {
        report.updateStatus(ReportStatus.OPEN);
        toggleButton.setText("Close");
      }
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }
}
