package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.Main;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ReportView {
  private Report report;

  public ReportView(Report report) { this.report = report; }

  public Scene getScene() {
    ReportController controller = new ReportController();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    Button backButton = new Button("Back");
    ButtonBar.setButtonData(backButton, ButtonData.RIGHT);
    backButton.setOnAction(e -> controller.back());

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.setBackground(new Background(new BackgroundFill(
        Color.web("#24292e"), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonBar.setPadding(new Insets(10, 20, 10, 20));
    buttonBar.getButtons().addAll(backButton);

    Label titleLabel = new Label(this.report.title);
    titleLabel.setFont(Font.font("Regular", FontWeight.BOLD, 24));

    Label descriptionLabel = new Label(this.report.description);
    descriptionLabel.setWrapText(true);
    descriptionLabel.setTextAlignment(TextAlignment.JUSTIFY);

    VBox reportContent = new VBox(10, titleLabel, descriptionLabel);
    reportContent.setPadding(new Insets(20));

    File imageFile = new File(this.report.screenshotPath);
    if (imageFile.exists()) {
      ImageView image = new ImageView(imageFile.toURI().toString());
      reportContent.getChildren().add(image);
    }

    Label typeLabel = new Label("Type:");
    typeLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label typeText = new Label(report.type.toString());

    Label priorityLabel = new Label("Priority:");
    priorityLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label priorityText = new Label(report.priority.toString());

    Label levelLabel = new Label("Level:");
    levelLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label levelText = new Label(report.level.toString());

    Label projectLabel = new Label("Project:");
    projectLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label projectText = new Label(report.project.name);

    Label assigneeLabel = new Label("Assignee:");
    assigneeLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label assigneeText = new Label(report.assignee.name);

    Label authorLabel = new Label("Author:");
    authorLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label authorText = new Label(report.author.name);

    Label dateLabel = new Label("Date:");
    dateLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label dateText =
        new Label(report.date.format(DateTimeFormatter.ISO_LOCAL_DATE));

    Label statusLabel = new Label("Status:");
    statusLabel.setFont(Font.font("Regular", FontWeight.BOLD, 14));
    Label statusText = new Label(report.status.toString());

    GridPane sidePanel = new GridPane();
    sidePanel.add(typeLabel, 0, 0);
    sidePanel.add(typeText, 1, 0);
    sidePanel.add(priorityLabel, 0, 1);
    sidePanel.add(priorityText, 1, 1);
    sidePanel.add(levelLabel, 0, 2);
    sidePanel.add(levelText, 1, 2);
    sidePanel.add(projectLabel, 0, 3);
    sidePanel.add(projectText, 1, 3);
    sidePanel.add(assigneeLabel, 0, 4);
    sidePanel.add(assigneeText, 1, 4);
    sidePanel.add(authorLabel, 0, 5);
    sidePanel.add(authorText, 1, 5);
    sidePanel.add(dateLabel, 0, 6);
    sidePanel.add(dateText, 1, 6);
    sidePanel.add(statusLabel, 0, 7);
    sidePanel.add(statusText, 1, 7);
    sidePanel.setPadding(new Insets(20));
    sidePanel.setVgap(5);
    sidePanel.setHgap(5);

    BorderPane bottomBar = new BorderPane();
    bottomBar.setLeft(errorLabel);
    bottomBar.setPadding(new Insets(20));

    if (Main.user.email.equals(report.assignee.email)) {
      Button toggleStatusButton = new Button(
          this.report.status.equals(ReportStatus.OPENED) ? "Close" : "Reopen");
      toggleStatusButton.setAlignment(Pos.BOTTOM_RIGHT);
      toggleStatusButton.setDefaultButton(true);
      toggleStatusButton.setOnAction(e -> {
        controller.toggleStatus(this.report, toggleStatusButton, errorLabel);
      });
      bottomBar.setRight(toggleStatusButton);
    }

    BorderPane borderPane = new BorderPane();
    borderPane.setBottom(bottomBar);
    borderPane.setCenter(reportContent);
    borderPane.setRight(sidePanel);
    borderPane.setTop(buttonBar);
    return new Scene(borderPane);
  }
}
