package com.example.production.controller;

import com.example.production.controller.service.ReportController;
import com.example.production.entities.Customers;
import com.example.production.entities.Users;
import com.example.production.general.GeneralClass;
import com.example.production.models.CustomerDAO;
import com.example.production.models.Model;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController extends GeneralClass implements Initializable {
    @FXML
    private MenuItem userCreation;


    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton salleryBtn;
    private Users activeUser;

    private Model model;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if (activeUser.getRole().equals("user")) {
                salleryBtn.setDisable(true);
            }

        });


    }

    @FXML
    void registrationsHandler(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = openWideWindow("/com/example/production/views/registrations.fxml", borderPane, stackPane);
        RegistrationController controller = fxmlLoader.getController();
        controller.setActiveUser(activeUser, model);
    }

    public void setModel(Users activeUser, Model model) {
        this.activeUser = activeUser;
        this.model = model;
    }


    @FXML
    void homeHandler(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = openWideWindow("/com/example/production/views/home.fxml", borderPane, stackPane);
        HomeController controller = loader.getController();
        controller.setModel(model);
        controller.setNodes(borderPane, stackPane);
    }

    @FXML
    void reportHandler(ActionEvent event) throws IOException {
        openWideWindow("/com/example/production/views/services/reports.fxml", borderPane, stackPane);

    }

    @FXML
    void sallerHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = openWideWindow("/com/example/production/views/services/reports.fxml", borderPane, stackPane);

    }

    @FXML
    void userCreationHandler(ActionEvent event) throws IOException {

        openShortWindow("/com/example/production/views/services/user-creation.fxml", stackPane);
    }
}
