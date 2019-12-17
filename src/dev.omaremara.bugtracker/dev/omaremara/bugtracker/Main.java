package dev.omaremara.bugtracker;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.util.ViewUtil;
import dev.omaremara.bugtracker.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {
  public static Stage primaryStage;
  public static User user;

  @Override
  public void start(Stage stage) {
    Main.primaryStage = stage;
    stage.setScene(new Scene(new Region()));
    ViewUtil.setSceneRoot(new LoginView());
    stage.setMaximized(true);
    stage.show();
  }

  public static void main(String[] args) { launch(); }
}
