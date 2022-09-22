package com.example.production.controller;

import com.example.production.entities.Customers;
import com.example.production.general.GeneralClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrationController extends GeneralClass implements Initializable {
    @FXML
    private TextField amountPaid;

    @FXML
    private ComboBox<String> boxChooser;

    @FXML
    private JFXCheckBox checkPoxing;

    @FXML
    private JFXButton clear;

    @FXML
    private TextField discount;

    @FXML
    private DatePicker expPicker;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TextField firstName;

    @FXML
    private VBox imagePane;

    @FXML
    private ImageView imgView;

    @FXML
    private TextField lastName;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ComboBox<String> paidby;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox<String> shift;

    @FXML
    private Label validationLabel;

    @FXML
    private TextField weight;
    @FXML
    private Label phoneValidation;
    @FXML
    private Label discountValidation;

    @FXML
    private Label dateValidation;
    @FXML
    private Label weightValidation;
    boolean discountVal;
    private final ObservableList<Control> mandotaryFields;
    private final ObservableList<Control> preInValidFields;
    private Stage stage;
    private File selectedFile;

    private LocalDate today;

    private ToggleGroup genderGroup;

    public RegistrationController() {
        this.mandotaryFields = FXCollections.observableArrayList();
        this.preInValidFields = FXCollections.observableArrayList();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) firstName.getScene().getWindow();
        });
        mandotaryFields.addAll(firstName, lastName, phone, paidby, amountPaid, shift, expPicker, discount);
        preInValidFields.addAll(weight, discount, phone, expPicker);
        shift.setItems(getShift());
        paidby.setItems(getPaidBy());
        genderGroup = new ToggleGroup();

        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
//        discount.setText(String.valueOf(0));
//        weight.setText(String.valueOf(65));

//        System.out.println(discount.getText());
//        System.out.println(weight.getText());
        discountValidation(discount, discountValidation, 3.0);
        phoneValidation(phone, phoneValidation);
        dateValidation(expPicker, dateValidation);
        weightValidation(weight, weightValidation);


    }


    @FXML
    void saveHandler(ActionEvent event) {
//        System.out.println("discount validation is " + isDiscountValid());
//        System.out.println("phone validation is " + isPhoneValid());
//        System.out.println("Date validation is " + isDateValid());
//        System.out.println("Wehight validation is " + isWeightValid());
        String gender = chooseGender(male, female);


        if (isValid(mandotaryFields, genderGroup) && isDiscountValid() && isPhoneValid() && isDateValid() && isWeightValid()) {
            System.out.println("You good to go..");
            boolean poxing = checkPoxing.isSelected();
            double disc = Double.parseDouble(discount.getText());
            double amount = (Double.parseDouble(amountPaid.getText()) - disc);

            try {
                double weghter = Double.parseDouble(weight.getText().trim());
                Customers insertedCustomer = new Customers(0,
                        firstName.getText().trim(), lastName.getText().trim(), phone.getText().trim(),
                        gender, paidby.getValue(), amount,
                        disc, shift.getValue(), expPicker.getValue(), weghter,
                        null, poxing, "Empty", "Ogleh", LocalDate.now()
                );


                System.out.println(insertedCustomer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Invalid");
        }

    }

    @FXML
    void imageUploadHandler(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Soo dooro sawirka");
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imgView.setImage(image);
            //anImage.setImage(image1);
            imgView.setFitWidth(400);
            imgView.setFitHeight(200);
            imgView.setPreserveRatio(true);
            imgView.setSmooth(true);
            imgView.setCache(true);
        } else {

        }


//Sax image ka
    }

    @FXML
    void update(ActionEvent event) {

    }
}
