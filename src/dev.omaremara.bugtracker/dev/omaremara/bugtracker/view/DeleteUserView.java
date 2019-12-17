package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.DeleteUserController;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.util.ViewUtil;
import dev.omaremara.bugtracker.view.AdministrationView;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeleteUserView implements View {
  public Parent getRoot() {
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
    backButton.setOnAction(
        e -> ViewUtil.setSceneRoot(new AdministrationView()));

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    borderPane.setCenter(grid);

    Label headerLabel = new Label("Delet User:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label userLabel = new Label("User:");
    ChoiceBox<User> userChoiceBox = new ChoiceBox<User>();
    List<User> users = DeleteUserController.getAllUsers(errorLabel);
    userChoiceBox.getItems().addAll(users);
    userChoiceBox.setValue(users.get(0));

    Button deleteUserButton = new Button("Delete User");
    GridPane.setHalignment(deleteUserButton, HPos.RIGHT);
    deleteUserButton.setDefaultButton(true);
    deleteUserButton.setOnAction(e -> {
      DeleteUserController.deleteUser(userChoiceBox.getValue(), errorLabel);
    });

    grid.add(headerLabel, 0, 0, 2, 1);
    grid.add(userLabel, 0, 1);
    grid.add(userChoiceBox, 1, 1);
    grid.add(deleteUserButton, 1, 2);
    grid.add(errorLabel, 0, 3, 2, 1);

    return borderPane;
  }
}
