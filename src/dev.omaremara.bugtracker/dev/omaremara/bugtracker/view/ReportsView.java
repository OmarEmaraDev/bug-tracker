package dev.omaremara.bugtracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ReportsView extends View {
  public ReportsView(Stage stage) { super(stage); }

  @Override
  public Scene getScene() {
    Label title = new Label("Reports List");
    return new Scene(new StackPane(title));
  }
}
