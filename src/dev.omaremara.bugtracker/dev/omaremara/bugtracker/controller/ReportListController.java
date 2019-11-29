package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.NewReportView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReportListController {
  public void newReport(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene newReportScene = new NewReportView().getScene();
    stage.setScene(newReportScene);
  }
  public void logOut(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene loginScene = new LoginView().getScene();
    stage.setScene(loginScene);
  }
}
