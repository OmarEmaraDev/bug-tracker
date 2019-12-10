package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.ReportListController;
import dev.omaremara.bugtracker.model.Report;
import java.time.format.DateTimeFormatter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReportListView implements View {
  public Scene getScene() {
    Button logOutButton = new Button("Log Out");
    ButtonBar.setButtonData(logOutButton, ButtonData.RIGHT);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setPadding(new Insets(20));
    buttonBar.getButtons().addAll(logOutButton);

    GridPane reportsGrid = new GridPane();
    reportsGrid.setPadding(new Insets(20));
    reportsGrid.setAlignment(Pos.CENTER);
    reportsGrid.setVgap(20);
    reportsGrid.setHgap(40);

    int i = 0;
    for (Report report : ReportListController.getAllReports()) {
      Label titleLabel = new Label(report.title);
      titleLabel.setStyle("-fx-font-size : 24px;");

      String infoString = "#" + report.id + " opened on " +
                          report.date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                          " By " + report.assignee.name;
      Label infoLabel = new Label(infoString);
      infoLabel.setTextFill(Color.GREY);

      VBox infoVBox = new VBox(titleLabel, infoLabel);
      GridPane.setHalignment(infoVBox, HPos.LEFT);

      Button viewReportButton = new Button("View Report");
      GridPane.setHalignment(viewReportButton, HPos.RIGHT);

      reportsGrid.add(infoVBox, 0, i);
      reportsGrid.add(viewReportButton, 1, i);
      i++;
    }

    Button newReportButton = new Button("New Report");
    BorderPane.setAlignment(newReportButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(newReportButton, new Insets(20));
    newReportButton.setDefaultButton(true);

    ReportListController controller = new ReportListController();
    newReportButton.setOnAction(e -> controller.newReport(e));
    logOutButton.setOnAction(e -> controller.logOut(e));

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(newReportButton);
    borderPane.setCenter(reportsGrid);
    borderPane.setTop(buttonBar);
    return new Scene(borderPane);
  }
}
