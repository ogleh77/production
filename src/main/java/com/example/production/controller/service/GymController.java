package com.example.production.controller.service;


import com.example.production.entities.services.Box;
import com.example.production.entities.services.Gym;
import com.example.production.models.service.BoxDAO;
import com.example.production.models.service.GymDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class GymController implements Initializable {
    @FXML
    private TextField boxName;

    @FXML
    private TextField boxCost;

    @FXML
    private ComboBox<Box> boxView;

    @FXML
    private TextField fitnessCost;

    @FXML
    private TextField gymName;

    @FXML
    private TextField maxDiscount;

    @FXML
    private TextField poxingCost;


    private GymDAO gymDAO;
    private final Gym gym;
    private BoxDAO boxDAO;

    public GymController() throws SQLException {
        gymDAO = new GymDAO();
        gym = gymDAO.getGym();
        this.boxDAO = new BoxDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            gymName.setText(gym.getGymName());
            poxingCost.setText(String.valueOf(gym.getPoxingCost()));
            fitnessCost.setText(String.valueOf(gym.getFitnessCost()));
            maxDiscount.setText(String.valueOf(gym.getMaxDiscount()));

            try {
                boxView.setItems(boxDAO.getAll());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void cancelHandler(MouseEvent event) {

    }

    @FXML
    void createBoxHandler(ActionEvent event) throws SQLException {
        //Make auto increament btn
        Box box = new Box(0, boxName.getText().trim(), Double.parseDouble(boxCost.getText()), true);

        boxDAO.insert(box);

        System.out.println("Box saved.....");
    }

    @FXML
    void updateHandler(ActionEvent event) throws SQLException {
        Gym updatedGym = new Gym(gymName.getText(), Double.parseDouble(fitnessCost.getText()), Double.parseDouble(poxingCost.getText()), Double.parseDouble(maxDiscount.getText()));
        gymDAO.update(updatedGym);

        System.out.println("updated.....");
    }
}
