package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.view.LoginView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReportController {
  public void toggleStatus(Report report, Button toggleButton,
                           Label errorLabel) {
    try {
      if (report.status.equals(ReportStatus.OPENED)) {
        report.updateStatus(ReportStatus.CLOSED);
        toggleButton.setText("Reopen");
      } else {
        report.updateStatus(ReportStatus.OPENED);
        toggleButton.setText("Close");
      }
    } catch (DataBaseException exception) {
      errorLabel.setText(exception.getMessage());
    }
  }
}
