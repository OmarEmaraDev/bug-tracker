package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.controller.ReportListController;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.UserRole;
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
import javafx.scene.control.CheckBox;
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
    BorderPane borderPane = new BorderPane();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(10, 20, 10, 20));
    borderPane.setTop(buttonBar);

    Button logOutButton = new Button("Log Out");
    ButtonBar.setButtonData(logOutButton, ButtonData.RIGHT);
    buttonBar.getButtons().add(logOutButton);
    logOutButton.setOnAction(e -> ReportListController.logOut());

    if (Main.user.role.equals(UserRole.ADMINISTRATOR)) {
      Button administerButton = new Button("Administer");
      ButtonBar.setButtonData(administerButton, ButtonData.LEFT);
      administerButton.setOnAction(e -> ReportListController.administer());
      buttonBar.getButtons().add(administerButton);
    }

    CheckBox viewOpenedCheckBox = new CheckBox("View Opened");
    viewOpenedCheckBox.setSelected(true);
    viewOpenedCheckBox.setTextFill(Color.WHITE);

    CheckBox viewClosedCheckBox = new CheckBox("View Closed");
    viewClosedCheckBox.setTextFill(Color.WHITE);

    VBox viewStatusBox = new VBox(5, viewOpenedCheckBox, viewClosedCheckBox);
    ButtonBar.setButtonData(viewStatusBox, ButtonData.LEFT);
    buttonBar.getButtons().add(viewStatusBox);

    CheckBox onlyViewMineCheckBox = new CheckBox("Only View Mine");
    onlyViewMineCheckBox.setTextFill(Color.WHITE);
    ButtonBar.setButtonData(onlyViewMineCheckBox, ButtonData.LEFT);
    if (Main.user.role.equals(UserRole.DEVELOPER)) {
      buttonBar.getButtons().add(onlyViewMineCheckBox);
    }

    GridPane reportsGrid = new GridPane();
    reportsGrid.setPadding(new Insets(20));

    ReportListView.updateReportsList(
        reportsGrid, errorLabel, viewOpenedCheckBox.isSelected(),
        viewClosedCheckBox.isSelected(), onlyViewMineCheckBox.isSelected());

    viewOpenedCheckBox.setOnAction(
        e
        -> ReportListView.updateReportsList(reportsGrid, errorLabel,
                                            viewOpenedCheckBox.isSelected(),
                                            viewClosedCheckBox.isSelected(),
                                            onlyViewMineCheckBox.isSelected()));
    viewClosedCheckBox.setOnAction(
        e
        -> ReportListView.updateReportsList(reportsGrid, errorLabel,
                                            viewOpenedCheckBox.isSelected(),
                                            viewClosedCheckBox.isSelected(),
                                            onlyViewMineCheckBox.isSelected()));

    onlyViewMineCheckBox.setOnAction(
        e
        -> ReportListView.updateReportsList(reportsGrid, errorLabel,
                                            viewOpenedCheckBox.isSelected(),
                                            viewClosedCheckBox.isSelected(),
                                            onlyViewMineCheckBox.isSelected()));
    borderPane.setCenter(reportsGrid);

    BorderPane bottomBar = new BorderPane();
    bottomBar.setLeft(errorLabel);
    bottomBar.setPadding(new Insets(10));

    if (Main.user.role.equals(UserRole.TESTER)) {
      Button newReportButton = new Button("New Report");
      newReportButton.setDefaultButton(true);
      bottomBar.setRight(newReportButton);
      newReportButton.setOnAction(e -> ReportListController.newReport());
    }

    borderPane.setBottom(bottomBar);

    return new Scene(borderPane);
  }

  public static void updateReportsList(GridPane reportsGrid, Label errorLabel,
                                       boolean viewOpened, boolean viewClosed,
                                       boolean onlyViewMine) {
    reportsGrid.getChildren().clear();
    int i = 0;
    for (Report report : ReportListController.getAllReports(
             viewOpened, viewClosed, onlyViewMine, errorLabel)) {
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

      viewReportButton.setOnAction(
          e -> ReportListController.viewReport(report));

      Separator separator = new Separator(Orientation.HORIZONTAL);
      separator.setPadding(new Insets(10, 0, 10, 0));

      reportsGrid.add(infoVBox, 0, i);
      reportsGrid.add(viewReportButton, 1, i);
      reportsGrid.add(separator, 0, i + 1, 2, 1);
      i += 2;
    }
  }
}
