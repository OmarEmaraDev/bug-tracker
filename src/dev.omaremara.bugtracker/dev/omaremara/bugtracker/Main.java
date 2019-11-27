package dev.omaremara.bugtracker;

import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.NewReportView;
import dev.omaremara.bugtracker.view.ReportsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static Stage primaryStage;
  public static Scene loginScene;
  public static Scene reportsScene;
  public static Scene newReportScene;

  @Override
  public void start(Stage stage) {
    Main.primaryStage = stage;
    Main.loginScene = new LoginView().getScene();
    Main.reportsScene = new ReportsView().getScene();
    Main.newReportScene = new NewReportView().getScene();
    stage.setScene(Main.loginScene);
    stage.show();
  }

  public static void main(String[] args) { launch(); }
}
