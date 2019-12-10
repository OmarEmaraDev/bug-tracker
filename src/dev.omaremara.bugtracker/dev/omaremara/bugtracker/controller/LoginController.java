package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import dev.omaremara.bugtracker.view.ReportListView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
  private TextField emailField;
  private PasswordField passwordField;
  private Label errorLabel;

  public LoginController(TextField emailField, PasswordField passwordField,
                         Label errorLabel) {
    this.emailField = emailField;
    this.passwordField = passwordField;
    this.errorLabel = errorLabel;
  }

  public void login(ActionEvent event) {
    try {
      String email = this.emailField.getText();
      String password = this.passwordField.getText();
      Main.user = User.getUserFromLogin(email, password);
      Stage stage = Main.primaryStage;
      Scene reportListScene = new ReportListView().getScene();
      stage.setScene(reportListScene);
    } catch (LoginException | DataBaseException exception) {
      this.errorLabel.setText(exception.getMessage());
    }
  }
}
