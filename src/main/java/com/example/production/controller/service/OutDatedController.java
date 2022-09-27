package com.example.production.controller.service;

import com.example.production.entities.Customers;
import com.example.production.general.GeneralClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OutDatedController extends GeneralClass implements Initializable {
    @FXML
    private Label customerName;
    @FXML
    private BorderPane borderPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Label date;
    private Customers customer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void outdatedHandler(MouseEvent event) throws IOException {

        //  FXMLLoader loader = openShortWindow("/com/example/project03/views/preview.fxml", stackPane);
        //PreViewController preViewController = loader.getController();
        //  preViewController.setCustomer(customer);
    }


    public void setCustomer(Customers customer) {
        this.customer = customer;
        customerName.setText(customer.getFirstName() + " " + customer.getLastName());
        date.setText(customer.getExpDate().toString());

    }


    public void setNodes(BorderPane borderPane, StackPane stackPane) {
        this.borderPane = borderPane;
        this.stackPane = stackPane;
    }
}
