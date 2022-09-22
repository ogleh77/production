package com.example.production.general;

import animatefx.animation.Shake;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class GeneralClass {
    private boolean discountValid = false;
    private boolean phoneValid = false;

    private boolean dateValid = false;

    private boolean weightValid = false;

    private String message = "Fadlan Xarfo qudha loo ogol yahay box kan";
    private final Shake shake;
    private final ObservableList<String> shift;
    private final ObservableList<String> paidBy;

    public GeneralClass() {
        shift = FXCollections.observableArrayList();
        paidBy = FXCollections.observableArrayList();
        shift.addAll("Morning", "Noon", "Afternoon", "Night");
        paidBy.addAll("eDahab", "Zaad service");

        this.shake = new Shake();
    }

    public String chooseGender(RadioButton male, RadioButton female) {
        if (male.isSelected()) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String role(RadioButton superAdmin, RadioButton admin, RadioButton user) {
        if (superAdmin.isSelected()) {
            return "super admin";
        } else if (admin.isSelected()) {
            return "admin";
        } else {
            return "user";
        }
    }

    protected File getImage() {
        File selectedFile = new File("/com/example/project02/images/27796e0eb8742d4465f6fb3ffac420d4.jpg");
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);

        return selectedFile;
    }


    public boolean discountValidation(TextField textField, Label discountVal, Double maxDiscount) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (textField.getText().matches("[0-9]*")) {
                    discountValid = true;
                    discountVal.setVisible(false);

//                    if ((textField.getText().isEmpty()) && (textField.getText().isBlank())) {
                    try {
                        double discountValue = Double.parseDouble(textField.getText().trim());
                        if (discountValue > maxDiscount) {

                            discountValid = false;
                            discountVal.setVisible(true);
                            discountVal.setText("Qiime dhimistu kama badnan karto $" + maxDiscount);

                        } else {
                            discountValid = true;
                            discountVal.setVisible(false);
                        }

                    } catch (Exception e){
                        System.out.println("Ex occur "+e.getLocalizedMessage());
                    }


                } else {
                    discountValid = false;
                    discountVal.setVisible(true);
                    // discountVal.setText(message);
                    // System.out.println("Is input valid " + discountValid);
                }


            }
        });


        return discountValid;
    }

    public boolean phoneValidation(TextField textField, Label phone) {


        textField.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (textField.getText().matches("[0-9]*")) {
                    phoneValid = true;
                    phone.setVisible(false);
                } else {
                    phoneValid = false;
                    phone.setVisible(true);
                    //   phone.setText(message);
                    // System.out.println("Is input valid " + phoneValid);
                }


            }
        });


        return phoneValid;
    }

    public boolean weightValidation(TextField textField, Label weight) {


        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (textField.getText().matches("[0-9]*")||(textField.getText().isEmpty() && textField.getText().isBlank())) {
                    weightValid = true;
                    weight.setVisible(false);

                } else {
                    weightValid = false;
                    shake.setNode(textField);
                    shake.play();
                    weight.setVisible(true);
                    // weight.setText("Onl..");
                    //  System.out.println("Is input valid " + phoneValid);
                }


            }
        });


        return weightValid;
    }

    public boolean dateValidation(DatePicker datePicker, Label date) {
        LocalDate today = LocalDate.now();
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (datePicker.getValue().isAfter(today)) {
                    dateValid = true;
                    date.setVisible(false);
                } else {
                    dateValid = false;
                    date.setVisible(true);
                    date.setText("Fadlan date ku haka danbeeyo tar:" + today + " oo manta ah");
                    //System.out.println("Is input valid " + phoneValid);
                }
            }
        });


        return dateValid;
    }


    public boolean isValid(ObservableList<Control> mandotoryFileds, ToggleGroup group) {
        boolean isValid = true;

        try {

            for (Control control : mandotoryFileds) {
                if (control instanceof TextInputControl) {
                    if ((((TextInputControl) control).getText().isBlank() || ((TextInputControl) control).getText().isEmpty())) {
                        shake.setNode(control);
                        control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                        shake.play();
                        isValid = false;
                    } else {
                        control.setStyle(null);
                    }
                } else if (control instanceof ComboBoxBase<?> && (((ComboBoxBase<?>) control).getValue() == null)) {
                    shake.setNode(control);
                    shake.play();
                    control.setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                    isValid = false;
                } else {
                    control.setStyle(null);
                }
            }
            if (group.getSelectedToggle() == null) {
                shake.setNode((Node) group.getToggles().get(0));
                shake.play();
                shake.setNode((Node) group.getToggles().get(1));
                ((Node) group.getToggles().get(0)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                ((Node) group.getToggles().get(1)).setStyle("-fx-border-color: #cb3d3d;-fx-border-width: 2");
                shake.play();

                isValid = false;
            } else {
                ((Node) group.getToggles().get(0)).setStyle(null);
                ((Node) group.getToggles().get(1)).setStyle(null);
            }


        } catch (NullPointerException e) {
            //  System.out.println(e.getMessage());
        }


        return isValid;
    }


    public ObservableList<String> getShift() {
        return shift;
    }

    public ObservableList<String> getPaidBy() {
        return paidBy;
    }

    public boolean isDiscountValid() {
        return discountValid;
    }

    public boolean isPhoneValid() {
        return phoneValid;
    }

    public boolean isDateValid() {
        return dateValid;
    }

    public boolean isWeightValid() {
        return weightValid;
    }
}
