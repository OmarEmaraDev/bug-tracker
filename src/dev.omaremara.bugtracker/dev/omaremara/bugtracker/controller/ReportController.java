package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.ReportListView;
import javafx.scene.Scene;
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

  public void close(Report report) {}

  public void reopen(Report report) {}
}
