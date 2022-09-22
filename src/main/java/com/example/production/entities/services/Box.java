package com.example.production.entities.services;

public class Box {
    private int boxId;
    private String boxTitle;
    private double cost;

    private boolean ready = true;

    public Box(int boxId, String boxTitle, double cost, boolean ready) {
        this.boxId = boxId;
        this.boxTitle = boxTitle;
        this.cost = cost;
        this.ready = ready;
    }

    public int getBoxId() {
        return boxId;
    }

    public String getBoxTitle() {
        return boxTitle;
    }

    public double getCost() {
        return cost;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxId=" + boxId +
                ", boxTitle='" + boxTitle + '\'' +
                ", cost=" + cost +
                ", ready=" + ready +
                '}';
    }
}
