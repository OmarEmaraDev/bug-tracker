package dev.omaremara.bugtracker;

import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.ReportsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static Stage primaryStage;
  public static Scene loginScene;
  public static Scene reportsScene;

  @Override
  public void start(Stage stage) {
    this.primaryStage = stage;
    this.loginScene = new LoginView(stage).getScene();
    this.reportsScene = new ReportsView(stage).getScene();
    stage.setScene(this.loginScene);
    stage.show();
  }

  public static void main(String[] args) { launch(); }
}
