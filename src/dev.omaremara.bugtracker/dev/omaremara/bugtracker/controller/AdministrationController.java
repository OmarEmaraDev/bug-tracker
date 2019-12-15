package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.view.AddUserView;
import dev.omaremara.bugtracker.view.DeleteUserView;
import dev.omaremara.bugtracker.view.ReportListView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdministrationController {
  public static void back() {
    Stage stage = Main.primaryStage;
    Scene reportListScene = new ReportListView().getScene();
    stage.setScene(reportListScene);
  }

  public static void addUser() {
    Stage stage = Main.primaryStage;
    Scene addUserScene = new AddUserView().getScene();
    stage.setScene(addUserScene);
  }
  public static void deleteUser() {
    Stage stage = Main.primaryStage;
    Scene deleteUserScene = new DeleteUserView().getScene();
    stage.setScene(deleteUserScene);
  }
}
