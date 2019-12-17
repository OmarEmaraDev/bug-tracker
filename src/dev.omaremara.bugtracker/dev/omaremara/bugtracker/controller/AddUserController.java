package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.UserRole;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.InvalidUserException;
import javafx.scene.control.Label;

public class AddUserController {
  public static void addUser(String name, String email, String password,
                             UserRole role, Label errorLabel) {
    try {
      User user = new User(name, email, password, role);
      user.submit();
    } catch (DataBaseException | InvalidUserException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }
}
