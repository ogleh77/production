package com.example.production.models.service;


import com.example.production.entities.services.Gym;
import com.example.production.repositories.IConnection;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GymDAO extends IConnection {
    //  private ObservableList<Gym> gyms;

    private Gym currentGym;
    private final Connection connection;

    public GymDAO() throws SQLException {
        this.connection = getConnection();
        // this.gyms = FXCollections.observableArrayList(getAll());
    }

    //    @Override
//    public void insert(Gym gym) {
//
//    }
//
//    @Override
    public void update(Gym gym) throws SQLException {
        String updateGym = "UPDATE gym SET gym_name='" + gym.getGymName() + "',fitness_cost=" + gym.getFitnessCost() +
                ",poxing_cost=" + gym.getPoxingCost() + "," +
                "max_discount=" + gym.getMaxDiscount() + " WHERE gym_id=1";

        Statement statement = connection.createStatement();

        statement.executeUpdate(updateGym);
        currentGym = gym;


    }
//
//    @Override
//    public ObservableList<Gym> getAll() throws SQLException {
//        Gym gym = null;
//
//        ObservableList<Gym> gyms = FXCollections.observableArrayList();
//
//        Connection connection = getConnection();
//        Statement statement = connectqion.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT * FROM gym");
//
//        while (rs.next()) {
//            gym = new Gym(rs.getInt(1), rs.getString(2), rs.getDouble(3),
//                    rs.getDouble(4), rs.getDouble(5));
//
//            gyms.add(gym);
//        }
//        statement.close();
//        connection.close();
//        rs.close();
//        return gyms;
//    }


    public Gym getCurrentGym() throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM gym");


        currentGym = new Gym(rs.getInt(1), rs.getString(2), rs.getDouble(3),
                rs.getDouble(4), rs.getDouble(5));

        rs.close();
        return currentGym;


    }


}
