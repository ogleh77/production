package com.example.production.controller.service;

import com.example.production.entities.Users;
import com.example.production.general.GeneralClass;
import com.example.production.models.UserDAO;

import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserCreationController extends GeneralClass implements Initializable {
    @FXML
    private RadioButton admin;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TextField firstname;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField lastname;

    @FXML
    private JFXRadioButton male;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox<String> shift;

    @FXML
    private RadioButton superadmin;

    @FXML
    private RadioButton user;

    @FXML
    private TextField username;

    private UserDAO userDAO;

    private ObservableList<Control> mandotaryFields;

    private ToggleGroup ganderToggle;

    public UserCreationController() throws SQLException {
        ganderToggle = new ToggleGroup();
        mandotaryFields = FXCollections.observableArrayList();
        this.userDAO = new UserDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            male.setToggleGroup(ganderToggle);
            female.setToggleGroup(ganderToggle);
            mandotaryFields.addAll(firstname, lastname, phone, shift, username, password, shift);
            shift.setItems(getShift());
            ToggleGroup roleToggle = new ToggleGroup();
            superadmin.setToggleGroup(roleToggle);
            admin.setToggleGroup(roleToggle);
            user.setToggleGroup(roleToggle);
            user.setSelected(true);

        });
    }


    @FXML
    void createUser(ActionEvent event) throws SQLException {
        String gender = chooseGender(male, female);
        String role = role(superadmin, admin, user);
        if (isValid(mandotaryFields, ganderToggle)) {
            Users user = new Users(0, firstname.getText().trim(), lastname.getText().trim(), phone.getText().trim(),
                    gender, shift.getValue(), username.getText().trim(), password.getText().trim(), "Empty", role);
            userDAO.insert(user);
            System.out.println("New user added.....");
        } else {
            System.out.println("Ivalid...");
        }


    }


}
