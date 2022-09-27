package com.example.production.entities.services;

public class Loan {
    private int loanId;
    private String gaveName;
    private String takerName;
    private String takerPhone;
    private double amountTake;
    private String loanDate;

    public Loan(int loanId, String gaveName, String takerName, String takerPhone, double amountTake, String loanDate) {
        this.loanId = loanId;
        this.gaveName = gaveName;
        this.takerName = takerName;
        this.takerPhone = takerPhone;
        this.amountTake = amountTake;
        this.loanDate = loanDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public String getGaveName() {
        return gaveName;
    }

    public String getTakerName() {
        return takerName;
    }

    public String getTakerPhone() {
        return takerPhone;
    }

    public double getAmountTake() {
        return amountTake;
    }

    public String getLoanDate() {
        return loanDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", gaveName='" + gaveName + '\'' +
                ", takerName='" + takerName + '\'' +
                ", takerPhone='" + takerPhone + '\'' +
                ", amountTake=" + amountTake +
                ", loanDate='" + loanDate + '\'' +
                '}';
    }
}
