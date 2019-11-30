package dev.omaremara.bugtracker;

import dev.omaremara.bugtracker.view.LoginView;
import dev.omaremara.bugtracker.view.NewReportView;
import dev.omaremara.bugtracker.view.ReportsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        Main.primaryStage = stage;
        Scene loginScene = new LoginView().getScene();
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
        System.out.println("Connect to SQL Server and demo Create, Read, Update and Delete operations.");

        //Update the username and password below
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;integratedSecurity=true";

        // Load SQL Server JDBC driver and establish connection.
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(connectionUrl);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id FROM t";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                //Display values
                System.out.print("ID: " + id);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample

