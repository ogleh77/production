package com.example.production.controller.service;

import com.example.production.entities.Customers;
import com.example.production.entities.services.Box;
import com.example.production.general.GeneralClass;
import com.example.production.models.Model;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.synedra.validatorfx.Check;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateController extends GeneralClass implements Initializable {

    @FXML
    private TextField amountPaid;

    @FXML
    private Label amountValidation;

    @FXML
    private ComboBox<Box> boxChooser;

    @FXML
    private JFXCheckBox checkPoxing;

    @FXML
    private Label dateValidationLabel;

    @FXML
    private TextField discount;

    @FXML
    private Label discountValidationLabel;

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
    private Label phoneValidationLabel;

    @FXML
    private ComboBox<String> shift;

    @FXML
    private TextField weight;

    @FXML
    private Label weightValidation;

    @FXML
    private Label weightValidationLabel;

    private Model model;
    private Customers customer;
    private final Validator validator;
    private final ToggleGroup genderGroup;
    private final ObservableList<Control> mandotaryFields;

    public UpdateController() {
        validator = new Validator();
        this.genderGroup = new ToggleGroup();
        this.mandotaryFields = FXCollections.observableArrayList();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            paidby.setItems(getPaidBy());
            shift.setItems(getShift());
            male.setToggleGroup(genderGroup);
            female.setToggleGroup(genderGroup);
            mandotaryFields.addAll(firstName, lastName, phone, paidby, amountPaid, shift, expPicker, discount);

            //Check if the fields are validate
            checkkFields();

            try {
                for (Box box : model.getBoxDAO().getAll()) {
                    if (box.isReady()) {
                        boxChooser.getItems().add(box);
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    void updateHandler(ActionEvent event) throws SQLException {
        if (isValid(mandotaryFields, genderGroup) && validator.getValidationResult().getMessages().isEmpty()) {
            System.out.println("Good to go");
            String ganderVal = chooseGender(male, female);
            double aPaidVal = Double.parseDouble(get(amountPaid));
            double disCountVal = Double.parseDouble(get(amountPaid));
            double weightVal = Double.parseDouble(get(weight));
            Box box = null;
            if (boxChooser.getValue() != null) {
                box = boxChooser.getValue();

            } else if (customer.getBox() != boxChooser.getValue()) {
                model.getBoxDAO().outDatedBoxUpdate(customer.getBox());
                box = boxChooser.getValue();
            } else {
                box = new Box(0, null, 0, false);
            }


            try {
                Customers updateCustomer = new Customers(customer.getCostumerId(), get(firstName), get(lastName), get(phone), ganderVal,
                        paidby.getValue(), aPaidVal, disCountVal, shift.getValue(), expPicker.getValue(), weightVal,
                        box, checkPoxing.isSelected(), "Empry", customer.getUserAdded(), LocalDate.now());
                model.getCustomerDAO().update(updateCustomer);

//                Customers updateCustomer = new Customers(0, get(firstName), get(lastName), get(phone), ganderVal,
//                        paidby.getValue(), aPaidVal, disCountVal, shift.getValue(), expPicker.getValue(), weightVal,
//                        box, checkPoxing.isSelected(), "Empry", customer.getUserAdded(), LocalDate.now());

                //  model.getBoxDAO().update(box);
                //System.out.println(updateCustomer);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        } else {
            System.out.println("Not valid...");
        }


    }

    @FXML
    void clearHandler(ActionEvent event) {

    }


    @FXML
    void changeImage(ActionEvent event) {

    }

    public void setModel(Model model, Customers customer) {
        this.model = model;
        this.customer = customer;
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        phone.setText(customer.getPhone().trim());

        if (customer.getGender().equals("Male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        shift.setValue(customer.getShift());
        paidby.setValue(customer.getPaidBy());
        discount.setText(String.valueOf(customer.getDiscount()));
        amountPaid.setText(String.valueOf(customer.getPaid()));
        weight.setText(String.valueOf(customer.getWeight()));
        expPicker.setValue(customer.getExpDate());
        ///
        // expPicker.setValue(customer.getExpDate().);
        if (customer.getBox() != null) {
            boxChooser.setValue(customer.getBox());
        }
        if (customer.isPoxing()) {
            checkPoxing.setSelected(true);
        }


    }


    private void phoneCheck(Check.Context c) {
        if (checkDigitsAndNullable(phone)) {
            c.error("Fadlan lanbaro qudha loo ogol yahay halkan!");
        } else if (phone.getText().length() < 7) {
            c.error("Lanbarku 7 digit kama yaraan karo!");
        }

    }

    private void weightCheck(Check.Context c) {
        if (checkDigitsAndNullable(weight)) {
            c.error("Fadlan lanbaro qudha loo ogol yahay halkan!");
        } else if (weight.getText().length() > 2) {
            c.error("Ma qofbaa jira 100kg dhafay ");
        }

    }

    private void discountCheck(Check.Context c) {
        double discountAmount;
        if (checkDigitsAndNullable(discount)) {
            c.error("Fadlan lanbaro qudha loo ogol yahay halkan!");

        } else if (!discount.getText().isBlank() && !discount.getText().isBlank()) {
            discountAmount = Double.parseDouble(discount.getText().trim());
            if (discountAmount > 3) {
                c.error("Fadlan discount ku kama badan karo " + 3);
            }

        }


    }

    private void dateCheck(Check.Context c) {
        // double discountAmount;

        if (expPicker.getValue() != null && expPicker.getValue().isBefore(LocalDate.now())) {
            c.error("Dooro wakhti ka danbeya manta tar: " + LocalDate.now());

        }

    }

    private void checkkFields() {
        validator.createCheck().dependsOn("phone", phone.textProperty())
                .withMethod(c -> {
                }).withMethod(this::phoneCheck)
                .decoratingWith(this::messageDecorator)
                .decorates(phoneValidationLabel)
                //.decorates(discountValidation).
                .immediate();

        validator.createCheck().dependsOn("discount", discount.textProperty())
                .withMethod(c -> {
                }).withMethod(this::discountCheck)
                .decoratingWith(this::messageDecorator)
                .decorates(discountValidationLabel)
                //.decorates(discountValidation).
                .immediate();

        validator.createCheck().dependsOn("weight", weight.textProperty())
                .withMethod(c -> {
                }).withMethod(this::weightCheck)
                .decoratingWith(this::messageDecorator)
                .decorates(weightValidationLabel)
                //.decorates(discountValidation).
                .immediate();

        validator.createCheck().dependsOn("date", expPicker.valueProperty())
                .withMethod(c -> {
                }).withMethod(this::dateCheck)
                .decoratingWith(this::messageDecorator)
                .decorates(dateValidationLabel)
                //.decorates(discountValidation).
                .immediate();


    }
}


