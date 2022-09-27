package com.example.production.models.service;

import com.example.production.entities.services.Salary;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.SimpleFormatter;

public class SalaryDAO extends Repo<Salary> {
    private final Connection connection;
    private final SimpleDateFormat df;

    public SalaryDAO() {
        this.connection = getConnection();
        this.df = new SimpleDateFormat("MM-yyyy");
    }

    @Override
    public void insert(Salary salary) throws SQLException {

        String salaryQuery = "INSERT INTO salary(amount_paid, discount, poxing, vip_box) " +
                "VALUES (" + salary.getAmount() + "," + salary.getDiscount() +
                "," + salary.getPoxing() + "," + salary.getVipBox() + ");";
        Statement statement = connection.createStatement();

        statement.executeUpdate(salaryQuery);

        System.out.println("Salary inserted.");
        statement.close();

    }

    @Override
    public void update(Salary salary) throws SQLException {
        System.out.println("Date is " + LocalDate.now());
        String month = String.valueOf(LocalDate.now()).substring(0, 7);

        //salary ga month only ka dhig si monthly ugu update garoobo insha Allah
        String sqlUpdate = "UPDATE salary SET amount_paid=(amount_paid + " + salary.getAmount() + "),discount=(discount+" + salary.getDiscount() + ")" +
                ",poxing=(poxing+" + salary.getPoxing() + "),vip_box=(vip_box+" + salary.getVipBox() + ") WHERE month_salary='" + month + "'";


        Statement statement = connection.createStatement();


        if (statement.executeUpdate(sqlUpdate) > 0) {
            System.out.println(" Salary Updated....");
        } else {
            insert(salary);
        }
        statement.close();
    }

    @Override
    public ObservableList<Salary> getAll() throws SQLException {

        ObservableList<Salary> salaries = FXCollections.observableArrayList();
        Salary salary = null;
        String sqlQuery = "SELECT * FROM salary;";

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            salary = new Salary(rs.getString(1).substring(0, 2), rs.getDouble(2), rs.getDouble(3),
                    rs.getDouble(4), rs.getDouble(5));

            salaries.add(salary);
        }


        return salaries;
    }
}
