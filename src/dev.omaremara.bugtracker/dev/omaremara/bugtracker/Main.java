package dev.omaremara.bugtracker;

import dev.omaremara.bugtracker.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static Stage primaryStage;

  @Override
  public void start(Stage stage) {
    Main.primaryStage = stage;
    Scene loginScene = new LoginView().getScene();
    stage.setScene(loginScene);
    stage.show();
  }

  public static void main(String[] args) { launch(); }
}
