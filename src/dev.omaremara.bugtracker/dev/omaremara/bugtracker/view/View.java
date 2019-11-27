package dev.omaremara.bugtracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View {
  public Stage stage;
  public View(Stage stage) { this.stage = stage; }

  public Scene getScene() {
    Label title = new Label("View not implemented!");
    return new Scene(new StackPane(title));
  }
}
