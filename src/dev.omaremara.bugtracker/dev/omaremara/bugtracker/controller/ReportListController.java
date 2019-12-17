package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.model.Report;
import dev.omaremara.bugtracker.model.ReportStatus;
import dev.omaremara.bugtracker.model.exception.DataBaseException;
import dev.omaremara.bugtracker.model.exception.LoginException;
import dev.omaremara.bugtracker.view.AdministrationView;
import dev.omaremara.bugtracker.view.InsightsView;
import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.NewReportView;
import dev.omaremara.bugtracker.view.ReportView;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReportListController {
  public static List<Report> getAllReports(boolean getOpenedReports,
                                           boolean getClosedReports,
                                           boolean getMyReportsOnly,
                                           Label errorLabel) {
    try {
      if (getClosedReports ^ getOpenedReports) {
        if (getMyReportsOnly) {
          if (getClosedReports) {
            return Report.getAllReports(ReportStatus.CLOSED, Main.user);
          } else {
            return Report.getAllReports(ReportStatus.OPENED, Main.user);
          }
        } else {
          if (getClosedReports) {
            return Report.getAllReports(ReportStatus.CLOSED, null);
          } else {
            return Report.getAllReports(ReportStatus.OPENED, null);
          }
        }
      } else {
        if (getMyReportsOnly) {
          return Report.getAllReports(null, Main.user);
        } else {
          return Report.getAllReports(null, null);
        }
      }
    } catch (DataBaseException | LoginException exception) {
      errorLabel.setText(exception.getMessage());
    }
    return new ArrayList<Report>();
  }
}
