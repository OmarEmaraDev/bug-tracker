package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.NewReportController;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.view.View;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class NewReportView implements View {
  public Scene getScene() {
    Label headerLabel = new Label("New Report:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label titleLabel = new Label("Title:");
    TextField titleField = new TextField();

    Label descriptionLabel = new Label("Description:");
    TextArea descriptionField = new TextArea();

    Label typeLabel = new Label("Type:");
    ChoiceBox<ReportType> typeChoiceBox = new ChoiceBox<ReportType>();
    typeChoiceBox.getItems().addAll(ReportType.values());
    typeChoiceBox.setValue(ReportType.BUG);

    Label priorityLabel = new Label("Priority:");
    ChoiceBox<ReportPriority> priorityChoiceBox =
        new ChoiceBox<ReportPriority>();
    priorityChoiceBox.getItems().addAll(ReportPriority.values());
    priorityChoiceBox.setValue(ReportPriority.TRIVIAL);

    Label levelLabel = new Label("Level:");
    ChoiceBox<ReportLevel> levelChoiceBox = new ChoiceBox<ReportLevel>();
    levelChoiceBox.getItems().addAll(ReportLevel.values());
    levelChoiceBox.setValue(ReportLevel.USER);

    Label projectLabel = new Label("Level:");
    ChoiceBox<String> projectChoiceBox = new ChoiceBox<String>();
    projectChoiceBox.getItems().addAll("Project 1", "Project 2", "Project 3");
    projectChoiceBox.setValue("Project 1");

    Label assigneeLabel = new Label("Assignee:");
    ChoiceBox<String> assigneeChoiceBox = new ChoiceBox<String>();
    assigneeChoiceBox.getItems().addAll("Assignee 1", "Assignee 2",
                                        "Assignee 3");
    assigneeChoiceBox.setValue("Assignee 1");

    Button submitButton = new Button("Submit");
    GridPane.setHalignment(submitButton, HPos.RIGHT);
    submitButton.setDefaultButton(true);

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    NewReportController controller = new NewReportController(
        titleField, descriptionField, typeChoiceBox, priorityChoiceBox,
        levelChoiceBox, projectChoiceBox, assigneeChoiceBox, errorLabel);
    submitButton.setOnAction(e -> controller.submit(e));

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(headerLabel, 0, 0, 2, 1);

    grid.add(titleLabel, 0, 1);
    grid.add(titleField, 1, 1);

    grid.add(descriptionLabel, 0, 2);
    grid.add(descriptionField, 1, 2);

    grid.add(typeLabel, 0, 3);
    grid.add(typeChoiceBox, 1, 3);

    grid.add(priorityLabel, 0, 4);
    grid.add(priorityChoiceBox, 1, 4);

    grid.add(levelLabel, 0, 5);
    grid.add(levelChoiceBox, 1, 5);

    grid.add(projectLabel, 0, 6);
    grid.add(projectChoiceBox, 1, 6);

    grid.add(assigneeLabel, 0, 7);
    grid.add(assigneeChoiceBox, 1, 7);

    grid.add(submitButton, 1, 8);
    grid.add(errorLabel, 0, 9, 2, 1);
    return new Scene(grid);
  }
}
