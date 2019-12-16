package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.view.ReportListView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InsightsController {
  public static void back() {
    Stage stage = Main.primaryStage;
    Scene reportListScene = new ReportListView().getScene();
    stage.setScene(reportListScene);
  }

  public static Map<User, Integer> getUsersStats(Label errorLabel) {
    try {
      return User.getUsersStats();
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new HashMap<User, Integer>();
  }
}
