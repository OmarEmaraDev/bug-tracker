package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Label;

public class InsightsController {
  public static Map<User, Integer> getUsersStats(Label errorLabel) {
    try {
      return User.getUsersStats();
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new HashMap<User, Integer>();
  }
}
