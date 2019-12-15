package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.controller.AdministrationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdministrationView implements View {
  public Scene getScene() {
    BorderPane borderPane = new BorderPane();

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(10, 20, 10, 20));
    borderPane.setTop(buttonBar);

    Button backButton = new Button("Back");
    ButtonBar.setButtonData(backButton, ButtonData.RIGHT);
    buttonBar.getButtons().add(backButton);
    backButton.setOnAction(e -> AdministrationController.back());

    Button addUserButton = new Button("Add User");
    addUserButton.setOnAction(e -> AdministrationController.addUser());

    Button deleteUserButton = new Button("Delete User");
    deleteUserButton.setOnAction(e -> AdministrationController.deleteUser());

    VBox buttonsBox = new VBox(5, addUserButton, deleteUserButton);
    buttonsBox.setAlignment(Pos.CENTER);

    borderPane.setCenter(buttonsBox);

    return new Scene(borderPane);
  }
}
