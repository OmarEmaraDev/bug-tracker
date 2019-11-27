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
import javafx.stage.Stage;

public class LoginView extends View {
  public LoginView(Stage stage) { super(stage); }

  @Override
  public Scene getScene() {
    Label headerLabel = new Label("Login:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();

    Button loginButton = new Button("Login");
    GridPane.setHalignment(loginButton, HPos.RIGHT);
    loginButton.setDefaultButton(true);

    LoginController controller = new LoginController(this.stage);
    loginButton.setOnAction(e -> controller.login(e));

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(headerLabel, 0, 0, 2, 1);
    grid.add(usernameLabel, 0, 1);
    grid.add(usernameField, 1, 1);
    grid.add(passwordLabel, 0, 2);
    grid.add(passwordField, 1, 2);
    grid.add(loginButton, 1, 3);
    return new Scene(grid);
  }
}
