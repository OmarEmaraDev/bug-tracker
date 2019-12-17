package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.InsightsController;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.util.ViewUtil;
import dev.omaremara.bugtracker.view.ReportListView;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InsightsView implements View {
  public Parent getRoot() {
    BorderPane borderPane = new BorderPane();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);
    errorLabel.setPadding(new Insets(10, 20, 10, 20));

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(10, 20, 10, 20));
    borderPane.setTop(buttonBar);

    Button backButton = new Button("Back");
    ButtonBar.setButtonData(backButton, ButtonData.RIGHT);
    buttonBar.getButtons().add(backButton);
    backButton.setOnAction(e -> ViewUtil.setSceneRoot(new ReportListView()));

    CategoryAxis usersAxis = new CategoryAxis();
    usersAxis.setLabel("Users");

    NumberAxis reportsAxis = new NumberAxis();
    reportsAxis.setLabel("Reports");

    BarChart<String, Number> barChart =
        new BarChart<String, Number>(usersAxis, reportsAxis);
    XYChart.Series<String, Number> dataSeries =
        new XYChart.Series<String, Number>();
    dataSeries.setName("Number of reports per user.");

    Map<User, Integer> usersStats =
        InsightsController.getUsersStats(errorLabel);
    for (Map.Entry<User, Integer> entry : usersStats.entrySet()) {
      dataSeries.getData().add(new XYChart.Data<String, Number>(
          entry.getKey().name, entry.getValue()));
    }

    barChart.getData().add(dataSeries);

    borderPane.setCenter(barChart);
    borderPane.setBottom(errorLabel);

    return borderPane;
  }
}
