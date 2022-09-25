package com.example.production.models;

import com.example.production.entities.Users;
import com.example.production.repositories.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDAO extends Repo<Users> {

    private ObservableList<Users> users;

    public UserDAO() throws SQLException {
        this.users = FXCollections.observableArrayList(getAll());
    }

    @Override
    public void insert(Users user) throws SQLException {
        String insertQuery = "INSERT INTO users(user_id, first_name, last_name, phone, gender, shift, username, password, image, role)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?);";

        Connection connection = getConnection();

        setPreparedStatement(user, insertQuery, connection);
        users.add(user);
        System.out.println("Added....");
    }


    @Override
    public void update(Users users) throws SQLException {

    }

    @Override
    public ObservableList<Users> getAll() throws SQLException {
        ObservableList<Users> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM users";
        Users user = null;
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            users.add(user);
        }
        connection.close();
        statement.close();
        rs.close();
        return users;
    }

    private void setPreparedStatement(Users user, String insertQuery, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(insertQuery);

        //     ps.setInt(1, user.getUserId());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getGender());
        ps.setString(6, user.getShift());
        ps.setString(7, user.getUsername());
        ps.setString(8, user.getPassword());
        ps.setString(9, user.getImage());
        ps.setString(10, user.getRole());

        ps.executeUpdate();
        System.out.println("User saved...");
    }

}
