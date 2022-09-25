package com.example.production.repositories;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Repo<T> {

    public Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/database/database.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return con;
    }

    public abstract void insert(T t) throws SQLException;

    public abstract void update(T t) throws SQLException;

    public abstract ObservableList<T> getAll() throws SQLException;
}
