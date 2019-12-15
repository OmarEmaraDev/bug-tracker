package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.view.AdministrationView;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteUserController {
  public static void back() {
    Stage stage = Main.primaryStage;
    Scene administrationScene = new AdministrationView().getScene();
    stage.setScene(administrationScene);
  }

  public static void deleteUser(User user, Label errorLabel) {
    try {
      user.delete();
      DeleteUserController.back();
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }

  public static List<User> getAllUsers(Label errorLabel) {
    try {
      return User.getAllUsers(null);
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new ArrayList<User>();
  }
}
