package com.example.production.entities.services;

import javafx.collections.ObservableList;

public class Gym {
    private int gymId;
    private String gymName;
    private double fitnessCost;
    private double poxingCost;
    private double maxDiscount;
    private ObservableList<Box> vipBoxes;

    public Gym(int gymId, String gymName, double fitnessCost, double poxingCost, double maxDiscount) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.fitnessCost = fitnessCost;
        this.poxingCost = poxingCost;
        this.maxDiscount = maxDiscount;

    }

    public Gym(String gymName, double fitnessCost, double poxingCost, double maxDiscount) {
        this.gymName = gymName;
        this.fitnessCost = fitnessCost;
        this.poxingCost = poxingCost;
        this.maxDiscount = maxDiscount;
    }


    public int getGymId() {
        return gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public double getFitnessCost() {
        return fitnessCost;
    }

    public double getPoxingCost() {
        return poxingCost;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public ObservableList<Box> getVipBoxes() {
        return vipBoxes;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "gymId=" + gymId +
                ", gymName='" + gymName + '\'' +
                ", fitnessCost=" + fitnessCost +
                ", poxingCost=" + poxingCost +
                ", maxDiscount=" + maxDiscount +
                ", vipBoxes=" + vipBoxes +
                '}';
    }

}
