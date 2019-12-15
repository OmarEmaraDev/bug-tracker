package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.controller.AddUserController;
import dev.omaremara.bugtracker.model.UserRole;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddUserView implements View {
  public Scene getScene() {
    BorderPane borderPane = new BorderPane();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(10, 20, 10, 20));
    borderPane.setTop(buttonBar);

    Button backButton = new Button("Back");
    ButtonBar.setButtonData(backButton, ButtonData.RIGHT);
    buttonBar.getButtons().add(backButton);
    backButton.setOnAction(e -> AddUserController.back());

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    borderPane.setCenter(grid);

    Label headerLabel = new Label("New User:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label nameLabel = new Label("Name:");
    TextField nameField = new TextField();

    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();

    Label roleLabel = new Label("Role:");
    ChoiceBox<UserRole> roleChoiceBox = new ChoiceBox<UserRole>();
    roleChoiceBox.getItems().addAll(UserRole.values());
    roleChoiceBox.setValue(UserRole.TESTER);

    Button addUserButton = new Button("Add User");
    GridPane.setHalignment(addUserButton, HPos.RIGHT);
    addUserButton.setDefaultButton(true);
    addUserButton.setOnAction(
        e
        -> AddUserController.addUser(nameField.getText(), emailField.getText(),
                                     passwordField.getText(),
                                     roleChoiceBox.getValue(), errorLabel));

    grid.add(headerLabel, 0, 0, 2, 1);
    grid.add(nameLabel, 0, 1);
    grid.add(nameField, 1, 1);
    grid.add(emailLabel, 0, 2);
    grid.add(emailField, 1, 2);
    grid.add(passwordLabel, 0, 3);
    grid.add(passwordField, 1, 3);
    grid.add(roleLabel, 0, 4);
    grid.add(roleChoiceBox, 1, 4);
    grid.add(addUserButton, 1, 5);
    grid.add(errorLabel, 0, 6, 2, 1);

    return new Scene(borderPane);
  }
}
