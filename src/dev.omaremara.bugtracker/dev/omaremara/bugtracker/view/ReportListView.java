package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.ReportListController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReportListView implements View {
  public Scene getScene() {
    Button newReportButton = new Button("New Report");
    BorderPane.setAlignment(newReportButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(newReportButton, new Insets(20));
    newReportButton.setDefaultButton(true);

    ReportListController controller = new ReportListController();
    newReportButton.setOnAction(e -> controller.newReport(e));

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(newReportButton);
    return new Scene(borderPane);
  }
}
