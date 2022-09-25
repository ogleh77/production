package com.example.production.controller;

import animatefx.animation.FadeIn;
import com.example.production.controller.service.SplashScreenController;
import com.example.production.entities.Users;
import com.example.production.models.UserDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Users> userCombo;

    @FXML
    private Label validionText;

    private final UserDAO usersDAO;

    private Users activeUser;

    public LoginController() throws SQLException {
        this.usersDAO = new UserDAO();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            try {
                userCombo.setItems(usersDAO.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void loginHandler(ActionEvent event) throws IOException, SQLException {
        activeUser = userCombo.getValue();
        if (activeUser.getPassword().equals(password.getText())) {
            System.out.println("Matched.....");
            Stage thisStage = (Stage) password.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/production/views/services/splash-screen.fxml"));
            Scene scene = new Scene(loader.load());
            SplashScreenController controller = loader.getController();
            controller.setModel(activeUser);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            thisStage.close();
        } else {
            FadeIn fadeIn = new FadeIn();
            fadeIn.setNode(validionText);
            validionText.setVisible(true);
            fadeIn.play();
        }
    }


    @FXML
    void exitHandler(MouseEvent event) {

    }

}
