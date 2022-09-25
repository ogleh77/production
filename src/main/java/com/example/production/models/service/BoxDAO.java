package com.example.production.models.service;

import com.example.production.entities.services.Box;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoxDAO extends Repo<Box> {
    private ObservableList<Box> boxes;

    public BoxDAO() throws SQLException {
        this.boxes = FXCollections.observableArrayList(getAll());
    }

    @Override
    public void insert(Box box) throws SQLException {

    }


    @Override
    public void update(Box box) throws SQLException {
///Saar waxanoo connection a

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE box SET box_status=" + false + " WHERE box_id=" + box.getBoxId());
        connection.close();
        statement.close();
        System.out.println("Box " + box.getBoxTitle() + " updated and set " + box.isReady());

    }

    public void outDatedBoxUpdate(Box box) throws SQLException {
///Saar waxanoo connection a

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE box SET box_status=" + true + " WHERE box_id=" + box.getBoxId());
        connection.close();
        statement.close();
        System.out.println("Box " + box.getBoxTitle() + " updated and set " + box.isReady());

    }

    @Override
    public ObservableList<Box> getAll() throws SQLException {
        ObservableList<Box> boxes = FXCollections.observableArrayList();
        Box box;

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM box");

        while (rs.next()) {
            box = new Box(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4));
            boxes.add(box);
        }
        connection.close();
        rs.close();
        statement.close();
        return boxes;
    }

    public ObservableList<Box> getBoxes() {
        return boxes;
    }
}
