package dev.omaremara.bugtracker.model;

import dev.omaremara.bugtracker.model.UserRole;
import java.sql.*;

public class User {
  public String email;
  public String password;
  public UserRole userRole;
  public String name;

  User(String email, String password, UserRole userRole, String name) {
    this.email = email;
    this.password = password;
    this.userRole = userRole;
    this.name = name;
  }

  public void test() {
    System.out.println(
        "Connect to SQL Server and demo Create, Read, Update and Delete operations.");

    String connectionUrl =
        "jdbc:sqlserver://localhost:1433;databaseName=master;integratedSecurity=true";
  }
}