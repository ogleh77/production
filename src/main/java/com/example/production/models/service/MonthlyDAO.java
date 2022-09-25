package com.example.production.models.service;

import com.example.production.entities.services.MonthlyReport;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonthlyDAO extends Repo<MonthlyReport> {

    //private LocalDate month;
    Connection connection;
    private MonthlyReport monthlyReport;

    public MonthlyDAO() {
        this.connection = getConnection();
    }

    @Override
    public void insert(MonthlyReport monthlyReport) throws SQLException {

    }

    @Override
    public void update(MonthlyReport monthlyReport) throws SQLException {

    }

    @Override
    public ObservableList<MonthlyReport> getAll() throws SQLException {
        ObservableList<MonthlyReport> monthlyReports = FXCollections.observableArrayList();
        String sql = "SELECT * FROM daily_report";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            monthlyReport = new MonthlyReport(
                    rs.getString(1), rs.getInt(2), rs.getInt(3),
                    rs.getInt(4), rs.getInt(5), rs.getInt(6));
            monthlyReports.add(monthlyReport);
        }
        connection.close();
        statement.close();
        rs.close();
        return monthlyReports;

    }

    public void monthlyReportMaleWithBox(String month, Statement st) throws SQLException {
        if (st.executeUpdate("UPDATE monthly_report set total_registrations=(total_registrations+1),total_male=(total_male+1),total_vip_box=(total_vip_box+1) WHERE report_month='" + month + "'") > 0) {
            System.out.println("Monthly report updated male with box");
        } else {
            String monthlySql = "INSERT INTO monthly_report(total_registrations,total_male,total_vip_box)VALUES(1,1,1)";
            st.addBatch(monthlySql);
        }
    }


    public void monthlyReportFemaleWithBox(String month, Statement st) throws SQLException {
        if (st.executeUpdate("UPDATE monthly_report set total_registrations=(total_registrations+1),total_female=(total_female+1),total_vip_box=(total_vip_box+1) WHERE report_month='" + month + "'") > 0) {
            System.out.println("Monthly report updated female with box");
        } else {
            String monthlySql = "INSERT INTO monthly_report(total_registrations,total_female,total_vip_box)VALUES(1,1,1)";
            st.addBatch(monthlySql);
        }
    }


    public void monthlyReportMaleWithOutBox(String month, Statement st) throws SQLException {
        if (st.executeUpdate("UPDATE monthly_report set total_registrations=(total_registrations+1),total_male=(total_male+1) WHERE report_month='" + month + "'") > 0) {
            System.out.println("Monthly report updated male without box");
        } else {
            String monthlySql = "INSERT INTO monthly_report(total_registrations,total_male)VALUES(1,1)";
            st.addBatch(monthlySql);
        }
    }


    public void monthlyReportFemaleWithOutBox(String month, Statement st) throws SQLException {
        if (st.executeUpdate("UPDATE monthly_report set total_registrations=(total_registrations+1),total_female=(total_female+1) WHERE report_month='" + month + "'") > 0) {
            System.out.println("Monthly report updated male without box");
        } else {
            String monthlySql = "INSERT INTO monthly_report(total_registrations,total_female)VALUES(1,1)";
            st.addBatch(monthlySql);
        }
    }


}
