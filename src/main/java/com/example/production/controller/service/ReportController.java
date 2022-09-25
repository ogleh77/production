package com.example.production.controller.service;


import com.example.production.entities.services.DailyReport;
import com.example.production.models.service.DailyReportDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            try {
                dailyReportInit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


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
