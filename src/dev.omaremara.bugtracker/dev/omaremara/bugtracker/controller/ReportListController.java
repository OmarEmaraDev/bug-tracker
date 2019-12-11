package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.NewReportView;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReportListController {
  public void newReport() {
    Stage stage = Main.primaryStage;
    Scene newReportScene = new NewReportView().getScene();
    stage.setScene(newReportScene);
  }
  public void logOut() {
    Stage stage = Main.primaryStage;
    Scene loginScene = new LoginView().getScene();
    stage.setScene(loginScene);
  }
  public static List<Report> getAllReports(Label errorLabel) {
    try {
      return Report.getAllReports();
    } catch (DataBaseException | LoginException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new ArrayList<Report>();
  }
}
