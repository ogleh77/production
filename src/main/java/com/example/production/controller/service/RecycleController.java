package com.example.production.controller.service;

import com.example.production.entities.Customers;
import com.example.production.models.Model;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class RecycleController implements Initializable {
    @FXML
    private TableColumn<Customers, LocalDate> dateJoined;

    @FXML
    private TableColumn<Customers, JFXButton> delete;

    @FXML
    private TableColumn<Customers, JFXButton> edit;

    @FXML
    private TableColumn<Customers, LocalDate> expariedDate;

    @FXML
    private TableColumn<Customers, String> firstName;

    @FXML
    private TableColumn<Customers, Circle> images;

    @FXML
    private TableColumn<Customers, String> lastName;

    @FXML
    private TableColumn<Customers, String> phone;

    @FXML
    private TableColumn<Customers, JFXButton> preview;

    @FXML
    private TableColumn<Customers, String> shift;

    @FXML
    private TableView<Customers> tableView;

    @FXML
    private TableColumn<Customers, Customers> userAdded;

    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                initTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void initTable() throws SQLException {
        images.setCellValueFactory(new PropertyValueFactory<Customers, Circle>("image"));
        firstName.setCellValueFactory(new PropertyValueFactory<Customers, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastName"));
        phone.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
        shift.setCellValueFactory(new PropertyValueFactory<Customers, String>("shift"));
        dateJoined.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("dateJoined"));
        expariedDate.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("expDate"));
        preview.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("preview"));
        edit.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("update"));
        delete.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("delete"));
        tableView.setItems(model.getCustomerDAO().getOutdated());
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
