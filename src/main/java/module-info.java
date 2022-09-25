module com.example.production {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires AnimateFX;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens com.example.production to javafx.fxml;
    opens com.example.production.controller to javafx.fxml;
    opens com.example.production.controller.service to javafx.fxml;
    exports com.example.production;
    exports com.example.production.entities.services;
    exports com.example.production.entities;
}