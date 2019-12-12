package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.ReportListController;
import dev.omaremara.bugtracker.model.Report;
import java.time.format.DateTimeFormatter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReportListView implements View {
  public Scene getScene() {
    ReportListController controller = new ReportListController();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    Button logOutButton = new Button("Log Out");
    ButtonBar.setButtonData(logOutButton, ButtonData.RIGHT);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(20));
    buttonBar.getButtons().addAll(logOutButton);

    GridPane reportsGrid = new GridPane();
    reportsGrid.setPadding(new Insets(20));

    int i = 0;
    for (Report report : ReportListController.getAllReports(errorLabel)) {
      Label titleLabel = new Label(report.title);
      titleLabel.setStyle("-fx-font-size : 24px;");

      String infoString = "#" + report.id + " opened on " +
                          report.date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                          " By " + report.assignee.name;
      Label infoLabel = new Label(infoString);
      infoLabel.setTextFill(Color.GREY);

      VBox infoVBox = new VBox(titleLabel, infoLabel);
      GridPane.setHalignment(infoVBox, HPos.LEFT);
      GridPane.setHgrow(infoVBox, Priority.ALWAYS);

      Button viewReportButton = new Button("View Report");
      GridPane.setHalignment(viewReportButton, HPos.RIGHT);

      viewReportButton.setOnAction(e -> controller.viewReport(report));

      Separator separator = new Separator(Orientation.HORIZONTAL);
      separator.setPadding(new Insets(10, 0, 10, 0));

      reportsGrid.add(infoVBox, 0, i);
      reportsGrid.add(viewReportButton, 1, i);
      reportsGrid.add(separator, 0, i + 1, 2, 1);
      i += 2;
    }

    Button newReportButton = new Button("New Report");
    newReportButton.setAlignment(Pos.BOTTOM_RIGHT);
    newReportButton.setDefaultButton(true);

    BorderPane bottomBar = new BorderPane();
    bottomBar.setRight(newReportButton);
    bottomBar.setLeft(errorLabel);
    bottomBar.setPadding(new Insets(10));

    newReportButton.setOnAction(e -> controller.newReport());
    logOutButton.setOnAction(e -> controller.logOut());

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(bottomBar);
    borderPane.setCenter(reportsGrid);
    borderPane.setTop(buttonBar);
    return new Scene(borderPane);
  }
}
