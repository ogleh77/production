package com.example.production.entities;


import com.example.production.entities.services.Box;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;

import java.time.LocalDate;


public class Customers {
    private int costumerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private String paidBy;
    private double paid;

    private double discount;
    private String shift;
    private LocalDate expDate;
    private double weight = 65;
    private Box box;
    private boolean poxing;
    private String image;
    private String userAdded;
    private LocalDate dateJoined;
    private JFXButton preview;
    private JFXButton update;
    private JFXButton delete;

    private Circle circle;


    public Customers(int costumerId, String firstName, String lastName, String phone, String gender, String paidBy, double paid, double discount, String shift, LocalDate expDate, double weight, Box box, boolean poxing, String image, String userAdded, LocalDate dateJoined) {
        this.costumerId = costumerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.paidBy = paidBy;
        this.paid = paid;
        this.discount = discount;
        this.shift = shift;
        this.expDate = expDate;
        this.weight = weight;
        this.box = box;
        this.poxing = poxing;
        this.image = image;
        this.userAdded = userAdded;
        this.dateJoined = dateJoined;
        this.preview = new JFXButton("preview");
        this.update = new JFXButton("Edit");
        this.delete = new JFXButton("delete");
        preview.setStyle("-fx-background-color: #06ffe6;-fx-text-fill: white");
        update.setStyle("-fx-background-color: dodgerblue;-fx-text-fill: white");
        delete.setStyle("-fx-background-color: red;-fx-text-fill: white");
        circle = new Circle(20);
    }


    public int getCostumerId() {
        return costumerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getPaid() {
        return paid;
    }

    public double getDiscount() {
        return discount;
    }

    public String getShift() {
        return shift;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public double getWeight() {
        return weight;
    }

    public Box getBox() {
        return box;
    }

    public boolean isPoxing() {
        return poxing;
    }

    public String getImage() {
        return image;
    }

    public String getUserAdded() {
        return userAdded;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public JFXButton getPreview() {
        return preview;
    }

    public JFXButton getUpdate() {
        return update;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public Circle getCircle() {
        return circle;
    }

    public void remove(ObservableList<Customers> customer) {
        customer.remove(this);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "costumerId=" + costumerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", paidBy='" + paidBy + '\'' +
                ", paid=" + paid +
                ", discount=" + discount +
                ", shift='" + shift + '\'' +
                ", expDate='" + expDate + '\'' +
                ", weight=" + weight +
                ", box=" + box +
                ", poxing=" + poxing +
                ", image='" + image + '\'' +
                ", userAdded='" + userAdded + '\'' +
                ", dateJoined='" + dateJoined + '\'' +
                ", preview=" + preview +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
}
