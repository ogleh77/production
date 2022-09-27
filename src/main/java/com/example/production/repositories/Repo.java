package com.example.production.repositories;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Repo<T> extends IConnection{



    public abstract void insert(T t) throws SQLException;

    public abstract void update(T t) throws SQLException;

    public abstract ObservableList<T> getAll() throws SQLException;
}
