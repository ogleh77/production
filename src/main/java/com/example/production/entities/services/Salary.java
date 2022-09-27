package com.example.production.entities.services;

public class Salary {
    private String salaryMonth;
    private double amount;
    private double discount;
    private double poxing;
    private double vipBox;


    public Salary(String salaryMonth, double amount, double discount, double poxing, double vipBox) {
        this.salaryMonth = salaryMonth;
        this.amount = amount;
        this.discount = discount;
        this.poxing = poxing;
        this.vipBox = vipBox;

    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public double getAmount() {
        return amount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPoxing() {
        return poxing;
    }

    public double getVipBox() {
        return vipBox;
    }



    @Override
    public String toString() {
        return "Salary{" +
                "salaryMonth='" + salaryMonth + '\'' +
                ", amount=" + amount +
                ", discount=" + discount +
                ", poxing=" + poxing +
                ", vipBox=" + vipBox +

                '}';
    }
}
