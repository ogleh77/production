module com.example.production {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires AnimateFX;

    opens com.example.production to javafx.fxml;
    opens com.example.production.controller to javafx.fxml;
    exports com.example.production;
}