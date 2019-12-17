package dev.omaremara.bugtracker.util;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.view.View;
import javafx.stage.Stage;

public class ViewUtil {
  public static void setSceneRoot(View view) {
    Stage stage = Main.primaryStage;
    stage.getScene().setRoot(view.getRoot());
  }
}
