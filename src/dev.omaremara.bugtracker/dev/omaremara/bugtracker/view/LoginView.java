package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.LoginController;
import dev.omaremara.bugtracker.view.View;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class LoginView implements View {
  public Scene getScene() {
    LoginController controller = new LoginController();

    Label headerLabel = new Label("Login:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();

    Button loginButton = new Button("Login");
    GridPane.setHalignment(loginButton, HPos.RIGHT);
    loginButton.setDefaultButton(true);

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(headerLabel, 0, 0, 2, 1);
    grid.add(emailLabel, 0, 1);
    grid.add(emailField, 1, 1);
    grid.add(passwordLabel, 0, 2);
    grid.add(passwordField, 1, 2);
    grid.add(loginButton, 1, 3);
    grid.add(errorLabel, 0, 4, 2, 1);

    loginButton.setOnAction(e -> {
      controller.login(emailField.getText(), passwordField.getText(),
                       errorLabel);
    });

    return new Scene(grid);
  }
}
