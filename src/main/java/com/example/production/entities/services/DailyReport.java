package com.example.production.entities.services;

public class DailyReport {
    private String day;
    private int registrations;
    private int shortRegistrations;
    private int male;
    private int female;
    private int vipBox;

    public DailyReport(String day, int registrations, int shortRegistrations, int male, int female, int vipBox) {
        this.day = day;
        this.registrations = registrations;
        this.shortRegistrations = shortRegistrations;
        this.male = male;
        this.female = female;
        this.vipBox = vipBox;
    }

    public String getDay() {
        return day;
    }

    public int getRegistrations() {
        return registrations;
    }

    public int getShortRegistrations() {
        return shortRegistrations;
    }

    public int getMale() {
        return male;
    }

    public int getFemale() {
        return female;
    }

    public int getVipBox() {
        return vipBox;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "day='" + day + '\'' +
                ", registrations=" + registrations +
                ", shortRegistrations=" + shortRegistrations +
                ", male=" + male +
                ", female=" + female +
                ", vipBox=" + vipBox +
                '}';
    }
}
