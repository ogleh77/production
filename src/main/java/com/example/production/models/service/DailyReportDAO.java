package com.example.production.models.service;

import com.example.production.entities.services.DailyReport;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DailyReportDAO extends Repo<DailyReport> {
    private MonthlyDAO monthlyDAO;
    private Connection connection = getConnection();

    private String month;

    public DailyReportDAO() {
        monthlyDAO = new MonthlyDAO();
    }

    @Override
    public void insert(DailyReport dailyReport) throws SQLException {

    }

    @Override
    public void update(DailyReport dailyReport) throws SQLException {

    }

    @Override
    public ObservableList<DailyReport> getAll() throws SQLException {
        ObservableList<DailyReport> dailyReports = FXCollections.observableArrayList();
        DailyReport dailyReport = null;
        String sql = "SELECT * FROM daily_report";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            dailyReport = new DailyReport(
                    rs.getString(1), rs.getInt(2), rs.getInt(3),
                    rs.getInt(4), rs.getInt(5), rs.getInt(6));
            dailyReports.add(dailyReport);
        }
        // connection.close();
        statement.close();
        rs.close();
        return dailyReports;
    }


    public void dailyReportMaleWithBox(LocalDate date, Statement st) throws SQLException {
        month = String.valueOf(date).substring(0, 7);
        System.out.println("date is " + date);

        if (st.executeUpdate("UPDATE daily_report SET registration=(registration+1),male=(male+1),vip_box=(vip_box+1) " + "WHERE report_day ='" + date + "'") > 0) {
            //st.addBatch();
            System.out.println("Updated male with box....");
            monthlyDAO.monthlyReportMaleWithBox(month, st);
        } else {
            String query = "INSERT INTO daily_report(registration,male,vip_box)VALUES (1,1,1)";
            // st.executeUpdate("INSERT INTO daily_report(regstared,male,vip_box)VALUES (1,1,1)");
            st.addBatch(query);
            System.out.println("Saved male with box....");
            //    monthlyDao.monthlyReportMaleWithBox(month, st);
        }
    }


    public void dailyReportFemaleWithBox(LocalDate date, Statement st) throws SQLException {

        month = String.valueOf(date).substring(0, 7);
        if (st.executeUpdate("UPDATE daily_report SET registration=(registration+1),female=(female+1),vip_box=(vip_box+1) " + "WHERE report_day ='" + date + "'") > 0) {
            //st.addBatch();
            System.out.println("Updated Female with box....");
            monthlyDAO.monthlyReportFemaleWithBox(month, st);
        } else {
            String query = "INSERT INTO daily_report(registration,female,vip_box)VALUES (1,1,1)";
            // st.executeUpdate("INSERT INTO daily_report(regstared,male,vip_box)VALUES (1,1,1)");
            st.addBatch(query);
            System.out.println("Saved Female with box....");
            monthlyDAO.monthlyReportFemaleWithBox(month, st);
        }
    }


    public void dailyReportMaleWithOutBox(LocalDate date, Statement st) throws SQLException {

        month = String.valueOf(date).substring(0, 7);

        System.out.println("Month is " + month);
        if (st.executeUpdate("UPDATE daily_report SET registration=(registration+1),male=(male+1) " + "WHERE report_day ='" + date + "'") > 0) {
            //st.addBatch();
            System.out.println("Updated male with out box....");
            monthlyDAO.monthlyReportMaleWithOutBox(month, st);
        } else {
            String query = "INSERT INTO daily_report(registration,male)VALUES (1,1)";
            // st.executeUpdate("INSERT INTO daily_report(regstared,male,vip_box)VALUES (1,1,1)");
            st.addBatch(query);
            System.out.println("Saved male with out box....");
            monthlyDAO.monthlyReportMaleWithOutBox(month, st);
        }
    }


    public void dailyReportFemaleWithOutBox(LocalDate date, Statement st) throws SQLException {
        month = String.valueOf(date).substring(0, 7);

        if (st.executeUpdate("UPDATE daily_report SET registration=(registration+1),female=(female+1)" + "WHERE report_day ='" + date + "'") > 0) {
            //st.addBatch();
            System.out.println("Updated female with out box....");
            monthlyDAO.monthlyReportFemaleWithOutBox(month, st);
        } else {
            String query = "INSERT INTO daily_report(registration,female)VALUES (1,1)";
            // st.executeUpdate("INSERT INTO daily_report(regstared,male,vip_box)VALUES (1,1,1)");
            st.addBatch(query);
            System.out.println("Saved female with out box....");
            monthlyDAO.monthlyReportFemaleWithOutBox(month, st);
        }
    }

}
