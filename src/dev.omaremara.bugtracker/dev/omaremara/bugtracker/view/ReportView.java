package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.ReportController;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.ReportStatus;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReportView {
  private Report report;

  public ReportView(Report report) { this.report = report; }

  public Scene getScene() {
    ReportController controller = new ReportController();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    Button logOutButton = new Button("Log Out");
    ButtonBar.setButtonData(logOutButton, ButtonData.RIGHT);

    Button backButton = new Button("Back");
    ButtonBar.setButtonData(backButton, ButtonData.LEFT);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(20));
    buttonBar.getButtons().addAll(backButton, logOutButton);

    Label titleLabel = new Label(this.report.title);
    titleLabel.setStyle("-fx-font-size : 24px;");

    Label descriptionLabel = new Label(this.report.description);
    descriptionLabel.setWrapText(true);

    VBox reportContent = new VBox(10, titleLabel, descriptionLabel);
    reportContent.setPadding(new Insets(20));

    File imageFile = new File(this.report.screenshotPath);
    if (imageFile.exists()) {
      ImageView image = new ImageView(imageFile.toURI().toString());
      reportContent.getChildren().add(image);
    }

    Button toggleStatusButton = new Button(
        this.report.status.equals(ReportStatus.OPEN) ? "Close" : "Reopen");
    toggleStatusButton.setAlignment(Pos.BOTTOM_RIGHT);
    toggleStatusButton.setDefaultButton(true);

    BorderPane bottomBar = new BorderPane();
    bottomBar.setRight(toggleStatusButton);
    bottomBar.setLeft(errorLabel);
    bottomBar.setPadding(new Insets(20));

    toggleStatusButton.setOnAction(
        e
        -> controller.toggleStatus(this.report, toggleStatusButton,
                                   errorLabel));

    backButton.setOnAction(e -> controller.back());
    logOutButton.setOnAction(e -> controller.logOut());

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(bottomBar);
    borderPane.setCenter(reportContent);
    borderPane.setTop(buttonBar);
    return new Scene(borderPane);
  }
}
