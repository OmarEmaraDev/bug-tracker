package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import dev.omaremara.bugtracker.view.ReportListView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {
  public void login(String email, String password, Label errorLabel) {
    try {
      Main.user = User.getFromLogin(email, password);
      Stage stage = Main.primaryStage;
      Scene reportListScene = new ReportListView().getScene();
      stage.setScene(reportListScene);
    } catch (LoginException | DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }
}
