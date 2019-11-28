package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.ReportListController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReportListView implements View {
  public Scene getScene() {
    Button logOutButton = new Button("Log Out");
    ButtonBar.setButtonData(logOutButton, ButtonData.RIGHT);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setPadding(new Insets(20));
    buttonBar.getButtons().addAll(logOutButton);

    Button newReportButton = new Button("New Report");
    BorderPane.setAlignment(newReportButton, Pos.BOTTOM_RIGHT);
    BorderPane.setMargin(newReportButton, new Insets(20));
    newReportButton.setDefaultButton(true);

    ReportListController controller = new ReportListController();
    newReportButton.setOnAction(e -> controller.newReport(e));
    logOutButton.setOnAction(e -> controller.logOut(e));

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(newReportButton);
    borderPane.setTop(buttonBar);
    return new Scene(borderPane);
  }
}
