package com.example.production.controller;


import com.example.production.entities.Customers;
import com.example.production.general.GeneralClass;
import com.example.production.models.Model;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController extends GeneralClass implements Initializable {
    @FXML
    private TableColumn<Customers, Integer> customerId;

    @FXML
    private TableColumn<Customers, String> dateJoined;

    @FXML
    private TableColumn<Customers, JFXButton> delete;

    @FXML
    private TableColumn<Customers, String> firstName;

    @FXML
    private TableColumn<Customers, String> lastName;

    @FXML
    private TableColumn<Customers, String> paidby;

    @FXML
    private TableColumn<Customers, JFXButton> preview;

    @FXML
    private TableColumn<Customers, String> shift;

    @FXML
    private TableColumn<Customers, JFXButton> update;

    @FXML
    private TableColumn<Customers, String> userAdded;
    @FXML
    private TableView<Customers> tableView;

    @FXML
    private Label usersCount;
    @FXML
    private Label activeCount;

    @FXML
    private Label outDatedCount;
    private BorderPane borderPane;
    private StackPane stackPane;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            try {

                activeCount.setText(String.valueOf(model.getActiveCustomers().size()));
                outDatedCount.setText(String.valueOf(model.getOutDatedCustomers().size()));

                initTable();

//                for (Customers customer : model.getActiveCustomers()) {
//                    customer.getUpdate().setOnAction(e -> {
//                        try {
//                            FXMLLoader loader = openWideWindow("/com/example/lastproject/views/services/update.fxml", borderPane, stackPane);
//                            UpdateController controller = loader.getController();
//                            controller.setModel(model, customer);
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    });
//                }ex


                for (Customers customer : model.getActiveCustomers()) {
                    customer.getDelete().setOnAction(e -> {
                        customer.remove(model.getActiveCustomers());
                    });
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void initTable() throws SQLException {
        customerId.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("costumerId"));
        firstName.setCellValueFactory(new PropertyValueFactory<Customers, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastName"));
        shift.setCellValueFactory(new PropertyValueFactory<Customers, String>("shift"));
        //  paidby.setCellValueFactory(new PropertyValueFactory<Customers, String>("paidBy"));
        userAdded.setCellValueFactory(new PropertyValueFactory<Customers, String>("userAdded"));
        dateJoined.setCellValueFactory(new PropertyValueFactory<Customers, String>("dateJoined"));
        preview.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("preview"));
        update.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("update"));
        delete.setCellValueFactory(new PropertyValueFactory<Customers, JFXButton>("delete"));
        tableView.setItems(model.getActiveCustomers());


    }

    public void setModel(Model model) throws SQLException {
        System.out.println("Called");
        this.model = model;
        System.out.println(model.getActiveCustomers().size() + " Active");
    }

    public void setNodes(BorderPane borderPane, StackPane stackPane) {
        this.borderPane = borderPane;
        this.stackPane = stackPane;
    }


}