package com.example.production.models;

import com.example.production.entities.Customers;
import com.example.production.entities.services.Box;
import com.example.production.models.service.DailyReportDAO;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;


public class CustomerDAO extends Repo<Customers> {
    private Connection connection;
    private final DailyReportDAO dailyReportDAO;


    public CustomerDAO() {
        this.connection = getConnection();
        this.dailyReportDAO = new DailyReportDAO();
    }

    @Override
    public void insert(Customers customer) throws SQLException {
        String insertQuery = "INSERT INTO customer(first_name, last_name, phone, gender, paid_by, paid, discount, shift, exp_date, weight, box_fk, poxing, image, user_added,date_joined)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);

        PreparedStatement ps = connection.prepareStatement(insertQuery);
        //Check if it's male or female and setDate to update the dailyReports
        qualification(customer, LocalDate.now());
        //Insert not out dated customer
        saveOrUpdate(customer, ps, false);
        connection.commit();
        connection.close();

        ps.close();


    }

    @Override
    public void update(Customers customer) throws SQLException {

        connection = getConnection();
        PreparedStatement ps;

        String updateQuery = "UPDATE customer SET first_name=?,last_name=? ,phone=?,gender=?,paid_by=?,paid=?,discount=?,\n" +
                "shift=?,exp_date=?,weight=?,box_fk=?,poxing=?,image=?,user_added=?,date_joined=?; WHERE costumer_id=" + customer.getCostumerId();

        ps = connection.prepareStatement(updateQuery);

        saveOrUpdate(customer, ps, false);
        System.out.println("Updated..");


    }

    public void updateOutDated(Customers customer) {

    }

    @Override
    public ObservableList<Customers> getAll() throws SQLException {
        ObservableList<Customers> customers = FXCollections.observableArrayList();
        Customers customer = null;
        Box box = null;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("\n" +
                "SELECT *\n" +
                "FROM customer\n" +
                "         LEFT JOIN box ON customer.box_fk = box.box_id;");

        while (rs.next()) {

            box = new Box(rs.getInt("box_id"),
                    rs.getString("box_title"),
                    rs.getDouble("box_cost"), rs.getBoolean("box_status"));

            customer = getCustomers(box, rs);
            customers.add(customer);
        }
        rs.close();
        statement.close();
        return customers;
    }


    public void qualification(Customers customer, LocalDate today) throws SQLException {
        Statement st = connection.createStatement();

        if (customer.getGender().equals("Male") && customer.getBox().getBoxId() > 0) {
            dailyReportDAO.dailyReportMaleWithBox(today, st);

        } else if (customer.getGender().equals("Female") && customer.getBox().getBoxId() > 0) {
            dailyReportDAO.dailyReportFemaleWithBox(today, st);

        } else if (customer.getBox().getBoxId() == 0 && customer.getGender().equals("Male")) {
            dailyReportDAO.dailyReportMaleWithOutBox(today, st);
        } else if (customer.getBox().getBoxId() == 0 && customer.getGender().equals("Female")) {
            dailyReportDAO.dailyReportFemaleWithOutBox(today, st);
        }
        int arr[] = st.executeBatch();
        System.out.println(Arrays.toString(arr));
        st.close();
    }

    private void saveOrUpdate(Customers customer, PreparedStatement ps, boolean outdated) throws SQLException {
        System.out.println(customer);
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getPhone());
        System.out.println(customer.getPhone() + " fff");
        ps.setString(4, customer.getGender());
        ps.setString(5, customer.getPaidBy());
        ps.setDouble(6, customer.getPaid());
        ps.setDouble(7, customer.getDiscount());
        ps.setString(8, customer.getShift());
        ps.setString(9, customer.getExpDate().toString());
        ps.setDouble(10, customer.getWeight());
        ps.setInt(11, customer.getBox().getBoxId());
        ps.setBoolean(12, customer.isPoxing());
        ps.setString(13, customer.getImage());
        ps.setString(14, customer.getUserAdded());
        ps.setString(15, customer.getDateJoined().toString());
        if (outdated) {
            //update today if the user is outdated
            ps.setString(15, LocalDate.now().toString());
        }

        ps.executeUpdate();

        System.out.println("executed....");
    }

    private Customers getCustomers(Box box, ResultSet rs) throws SQLException {
        Customers customer;
        LocalDate expDate = LocalDate.parse(rs.getString(10));
        LocalDate dateJoined = LocalDate.parse(rs.getString(10));
        customer = new Customers(
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6), rs.getDouble(7),
                rs.getDouble(8), rs.getString(9), expDate, rs.getDouble(11),
                box, rs.getBoolean(13), rs.getString(14), rs.getString(15), dateJoined);
        return customer;
    }
}
