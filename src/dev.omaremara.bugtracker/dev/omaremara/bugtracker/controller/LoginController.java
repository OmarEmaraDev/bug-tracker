package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
  public void login(ActionEvent e) {
    Stage stage = Main.primaryStage;
    stage.setScene(Main.reportsScene);
  }
}
