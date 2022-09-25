package com.example.production.controller.service;

import com.example.production.controller.DashboardController;
import com.example.production.entities.Users;
import com.example.production.models.Model;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {
    @FXML
    private ProgressBar progress;
    @FXML
    private Label welcomeUserName;

    private Model model;
    private Users activeUser;
    private Stage splashScreenStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {

            progress.progressProperty().bind(model.progressProperty());
            model.setOnSucceeded(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/production/views/dashboard.fxml"));
                    splashScreenStage = (Stage) progress.getScene().getWindow();
                    splashScreenStage.close();
                    Scene scene = new Scene(loader.load());
                    DashboardController dashboardController = loader.getController();
                    dashboardController.setModel(activeUser, model);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //Make animation few
                    stage.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            });
        });
    }

    public void setModel(Users activeUser) throws SQLException {
        this.model = new Model();

        Thread thread = new Thread(model);
        this.activeUser = activeUser;
        this.welcomeUserName.setText("Welcome " + activeUser.getUsername());
        thread.start();


    }
}
