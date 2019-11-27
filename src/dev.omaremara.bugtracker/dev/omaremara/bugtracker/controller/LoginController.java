package dev.omaremara.bugtracker.controller;

import dev.omaremara.bugtracker.Main;
import dev.omaremara.bugtracker.controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController extends Controller {
  public LoginController(Stage stage) { super(stage); }

  public void login(ActionEvent e) { this.stage.setScene(Main.reportsScene); }
}
