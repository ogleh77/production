package com.example.production.controller.service;


import com.example.production.entities.Customers;
import com.example.production.entities.services.DailyReport;
import com.example.production.models.service.DailyReportDAO;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    private TableColumn<DailyReport, Integer> female;

    @FXML
    private TableColumn<DailyReport, Integer> male;

    @FXML
    private TableColumn<DailyReport, Integer> registration;

    @FXML
    private TableColumn<DailyReport, String> reportDay;

    @FXML
    private TableColumn<DailyReport, Integer> tempReg;

    @FXML
    private TableColumn<DailyReport, Integer> vipBox;

    @FXML
    private TableView<DailyReport> dailyTable;

    private DailyReportDAO dailyReportDAO = new DailyReportDAO();
    @FXML
    private TextField searchDay;

    private FilteredList<DailyReport> filteredList;
    private SortedList<DailyReport> sortedList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                dailyReportInit();
                filteredList = new FilteredList<>(dailyReportDAO.getAll(), b -> true);


                searchDay.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredList.setPredicate(dailyReport -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        if (dailyReport.getDay().contains(newValue)) {
                            return true;

                        } else {
                            return false;
                        }
                    });
                });

                sortedList = new SortedList<>(filteredList);

                sortedList.comparatorProperty().bind(dailyTable.comparatorProperty());

                dailyTable.setItems(sortedList);
            } catch (SQLException e) {

            }
        });

    }

    /*



                searchDay.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredList.setPredicate(customer -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        if (customer.getFirstName().contains(newValue.toLowerCase()) || customer.getFirstName().contains(newValue.toUpperCase())) {
                            return true;
                        } else if (customer.getPhone().contains(newValue)) {
                            return true;
                        } else if (customer.getLastName().contains(newValue.toLowerCase()) || customer.getLastName().contains(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });*/

    private void dailyReportInit() throws SQLException {
        reportDay.setCellValueFactory(new PropertyValueFactory<DailyReport, String>("day"));
        registration.setCellValueFactory(new PropertyValueFactory<DailyReport, Integer>("registrations"));
        tempReg.setCellValueFactory(new PropertyValueFactory<DailyReport, Integer>("shortRegistrations"));
        male.setCellValueFactory(new PropertyValueFactory<DailyReport, Integer>("male"));
        female.setCellValueFactory(new PropertyValueFactory<DailyReport, Integer>("female"));
        vipBox.setCellValueFactory(new PropertyValueFactory<DailyReport, Integer>("vipBox"));

        dailyTable.setItems(dailyReportDAO.getAll());
    }


//    public void setDailyReport(DailyReportDAO dailyReportDAO) {
//        this.dailyReportDAO = dailyReportDAO;
//    }
}
