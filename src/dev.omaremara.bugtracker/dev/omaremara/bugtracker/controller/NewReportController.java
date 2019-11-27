package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewReportController {
  private TextField titleField;
  private TextArea descriptionField;
  private ChoiceBox<ReportType> typeChoiceBox;
  private ChoiceBox<ReportPriority> priorityChoiceBox;
  private ChoiceBox<ReportLevel> levelChoiceBox;
  private ChoiceBox<String> projectChoiceBox;
  private ChoiceBox<String> assigneeChoiceBox;
  private Label errorLabel;

  public NewReportController(TextField titleField, TextArea descriptionField,
                             ChoiceBox<ReportType> typeChoiceBox,
                             ChoiceBox<ReportPriority> priorityChoiceBox,
                             ChoiceBox<ReportLevel> levelChoiceBox,
                             ChoiceBox<String> projectChoiceBox,
                             ChoiceBox<String> assigneeChoiceBox,
                             Label errorLabel) {
    this.titleField = titleField;
    this.descriptionField = descriptionField;
    this.typeChoiceBox = typeChoiceBox;
    this.priorityChoiceBox = priorityChoiceBox;
    this.levelChoiceBox = levelChoiceBox;
    this.projectChoiceBox = projectChoiceBox;
    this.assigneeChoiceBox = assigneeChoiceBox;
    this.errorLabel = errorLabel;
  }

  public void submit(ActionEvent e) {
    Stage stage = Main.primaryStage;
    Scene scene = Main.reportsScene;
    // if (isValidReport) {
    // stage.setScene(scene);
    // }
    // else {
    this.errorLabel.setText("Invalid report!");
    // }
  }
}
