package dev.omaremara.bugtracker.view;

import dev.omaremara.bugtracker.controller.NewReportController;
import dev.omaremara.bugtracker.model.Project;
import dev.omaremara.bugtracker.model.ReportLevel;
import dev.omaremara.bugtracker.model.ReportPriority;
import dev.omaremara.bugtracker.model.ReportType;
import dev.omaremara.bugtracker.model.User;
import dev.omaremara.bugtracker.view.View;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class NewReportView implements View {
  public Parent getRoot() {
    NewReportController controller = new NewReportController();

    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED);

    Label headerLabel = new Label("New Report:");
    headerLabel.setStyle("-fx-font-size : 24px;");
    GridPane.setHalignment(headerLabel, HPos.LEFT);

    Label titleLabel = new Label("Title:");
    TextField titleField = new TextField();

    Label descriptionLabel = new Label("Description:");
    TextArea descriptionField = new TextArea();

    Label attachedLabel = new Label("No attached screenshot.");
    GridPane.setHalignment(attachedLabel, HPos.LEFT);
    Button attachButton = new Button("Attach Screenshot");
    GridPane.setHalignment(attachButton, HPos.RIGHT);

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

    Label projectLabel = new Label("Project:");
    ChoiceBox<Project> projectChoiceBox = new ChoiceBox<Project>();
    List<Project> projects = controller.getAllProjects(errorLabel);
    projectChoiceBox.getItems().addAll(projects);
    projectChoiceBox.setValue(projects.get(0));

    Label assigneeLabel = new Label("Assignee:");
    ChoiceBox<User> assigneeChoiceBox = new ChoiceBox<User>();
    List<User> developers = controller.getAllDevelopers(errorLabel);
    assigneeChoiceBox.getItems().addAll(developers);
    assigneeChoiceBox.setValue(developers.get(0));

    Button submitButton = new Button("Submit");
    submitButton.setDefaultButton(true);

    Button cancelButton = new Button("Cancel");

    HBox actionRow = new HBox(10, cancelButton, submitButton);
    actionRow.setAlignment(Pos.BOTTOM_RIGHT);

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(headerLabel, 0, 0, 2, 1);

    grid.add(titleLabel, 0, 1);
    grid.add(titleField, 1, 1);

    grid.add(descriptionLabel, 0, 2);
    grid.add(descriptionField, 1, 2);

    grid.add(attachedLabel, 1, 3);
    grid.add(attachButton, 1, 3);

    grid.add(typeLabel, 0, 4);
    grid.add(typeChoiceBox, 1, 4);

    grid.add(priorityLabel, 0, 5);
    grid.add(priorityChoiceBox, 1, 5);

    grid.add(levelLabel, 0, 6);
    grid.add(levelChoiceBox, 1, 6);

    grid.add(projectLabel, 0, 7);
    grid.add(projectChoiceBox, 1, 7);

    grid.add(assigneeLabel, 0, 8);
    grid.add(assigneeChoiceBox, 1, 8);

    grid.add(actionRow, 1, 9);
    grid.add(errorLabel, 0, 10, 2, 1);

    attachButton.setOnAction(e -> controller.attach(attachedLabel));
    cancelButton.setOnAction(e -> controller.cancel());
    submitButton.setOnAction(e -> {
      controller.submit(titleField.getText(), descriptionField.getText(),
                        typeChoiceBox.getValue(), priorityChoiceBox.getValue(),
                        levelChoiceBox.getValue(), projectChoiceBox.getValue(),
                        assigneeChoiceBox.getValue(), errorLabel);
    });

    return grid;
  }
}
