package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.view.ReportListView;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class NewReportController {
  private TextField titleField;
  private TextArea descriptionField;
  private Label attachedLabel;
  private ChoiceBox<ReportType> typeChoiceBox;
  private ChoiceBox<ReportPriority> priorityChoiceBox;
  private ChoiceBox<ReportLevel> levelChoiceBox;
  private ChoiceBox<String> projectChoiceBox;
  private ChoiceBox<String> assigneeChoiceBox;
  private Label errorLabel;

  public NewReportController(TextField titleField, TextArea descriptionField,
                             Label attachedLabel,
                             ChoiceBox<ReportType> typeChoiceBox,
                             ChoiceBox<ReportPriority> priorityChoiceBox,
                             ChoiceBox<ReportLevel> levelChoiceBox,
                             ChoiceBox<String> projectChoiceBox,
                             ChoiceBox<String> assigneeChoiceBox,
                             Label errorLabel) {
    this.titleField = titleField;
    this.descriptionField = descriptionField;
    this.attachedLabel = attachedLabel;
    this.typeChoiceBox = typeChoiceBox;
    this.priorityChoiceBox = priorityChoiceBox;
    this.levelChoiceBox = levelChoiceBox;
    this.projectChoiceBox = projectChoiceBox;
    this.assigneeChoiceBox = assigneeChoiceBox;
    this.errorLabel = errorLabel;
  }

  public void submit(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene reportListScene = new ReportListView().getScene();
    stage.setScene(reportListScene);

    // if (isValidReport) {
    // stage.setScene(scene);
    // }
    // else {
    // this.errorLabel.setText("Invalid report!");
    // }
  }

  public void attach(ActionEvent e) {
    Stage stage = Main.primaryStage;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Attach Screenshot");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home") + "/Pictures"));
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
      this.attachedLabel.setText(selectedFile.getName());
    }
  }

  public void cancel(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene reportListScene = new ReportListView().getScene();
    stage.setScene(reportListScene);
  }
}
