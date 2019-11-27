package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
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

  public void login(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene scene = Main.reportsScene;
    // if (isValidLogin) {
    // Main.user = create user;
    // stage.setScene(scene);
    // }
    // else {
    errorLabel.setText("Invalid email or password!");
    // }
  }
}
