package com.example.production.models;

import com.example.production.entities.Customers;
import com.example.production.models.service.BoxDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.SQLException;
import java.time.LocalDate;

public class Model extends Task<Void> {

    private final CustomerDAO customerDAO;
    private final BoxDAO boxDAO;

    private ObservableList<Customers> customers;
    private ObservableList<Customers> activeCustomers;
    private ObservableList<Customers> outDatedCustomers;
    int limit = 0;

    public Model() throws SQLException {
        this.customerDAO = new CustomerDAO();
        this.boxDAO = new BoxDAO();
        this.customers = FXCollections.observableArrayList(customerDAO.getAll());
        this.activeCustomers = FXCollections.observableArrayList();
        this.outDatedCustomers = FXCollections.observableArrayList();
        limit = customers.size();
    }

    @Override
    protected Void call() throws Exception {

        for (int i = 0; i < customers.size(); i++) {
            if (LocalDate.now().isAfter(customers.get(i).getExpDate())) {
                outDatedCustomers.add(customers.get(i));
                if (customers.get(i).getBox() != null) {
                    boxDAO.outDatedBoxUpdate(customers.get(i).getBox());
                }
                System.out.println(customers.get(i).getFirstName() + " is out " + customers.get(i).getExpDate());
            } else {
                System.out.println(customers.get(i).getFirstName() + " is active " + customers.get(i).getExpDate());
                activeCustomers.add(customers.get(i));
            }
            updateProgress(i, limit);
            updateMessage("Loading ");
            Thread.sleep(1000);
        }


        return null;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public BoxDAO getBoxDAO() {
        return boxDAO;
    }

    public ObservableList<Customers> getActiveCustomers() {
        return activeCustomers;
    }

    public ObservableList<Customers> getOutDatedCustomers() {
        return outDatedCustomers;
    }

    public ObservableList<Customers> getCustomers() {
        return customers;
    }
}
