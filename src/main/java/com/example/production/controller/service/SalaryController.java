package com.example.production.controller.service;

import com.example.production.entities.Customers;
import com.example.production.entities.services.Salary;
import com.example.production.models.service.SalaryDAO;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    private TableColumn<Salary, Double> amount;

    @FXML
    private TableColumn<Salary, Double> discount;

    @FXML
    private TableColumn<Salary, Double> loan;

    @FXML
    private TableColumn<Salary, String> month;
    @FXML
    private TableColumn<Salary, Double> poxing;
    @FXML
    private TableColumn<Salary, Double> vipBox;
    @FXML
    private TableView<Salary> tableView;
    @FXML
    private Label mounthAmount;
    @FXML
    private Label sumtion;
    private SalaryDAO salaryDAO;
    private double totalEarn;
    private double eranByMonth;

    public SalaryController() throws SQLException {
        salaryDAO = new SalaryDAO();
        eranByMonth = ((salaryDAO.getAll().get(0).getAmount()
                + salaryDAO.getAll().get(0).getPoxing()
                + salaryDAO.getAll().get(0).getVipBox()) - salaryDAO.getAll().get(0).getDiscount());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            try {
                initTable();

                mounthAmount.setText("$" + eranByMonth);
                for (Salary salary : salaryDAO.getAll()) {
                    totalEarn += ((salary.getAmount()
                            + salary.getPoxing()
                            + salary.getVipBox()) - salary.getDiscount());
                    sumtion.setText("$" + totalEarn);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void initTable() throws SQLException {
        month.setCellValueFactory(new PropertyValueFactory<Salary, String>("salaryMonth"));
        amount.setCellValueFactory(new PropertyValueFactory<Salary, Double>("amount"));
        discount.setCellValueFactory(new PropertyValueFactory<Salary, Double>("discount"));
        poxing.setCellValueFactory(new PropertyValueFactory<Salary, Double>("poxing"));
//             loan.setCellValueFactory(new PropertyValueFactory<Customers, Double>("userAdded"));
        vipBox.setCellValueFactory(new PropertyValueFactory<Salary, Double>("vipBox"));

        tableView.setItems(salaryDAO.getAll());


    }

}
