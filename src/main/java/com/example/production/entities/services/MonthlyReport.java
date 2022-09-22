package com.example.production.entities.services;

public class MonthlyReport {
    private final String reportMonth;
    private final int totalRegistration;
    private final int totalShortRegistration;
    private final int totalMale;
    private final int totalFemale;
    private final int totalVipBox;

    public MonthlyReport(String reportMonth, int totalRegistration, int totalShortRegistration, int totalMale, int totalFemale, int totalVipBox) {
        this.reportMonth = reportMonth;
        this.totalRegistration = totalRegistration;
        this.totalShortRegistration = totalShortRegistration;
        this.totalMale = totalMale;
        this.totalFemale = totalFemale;
        this.totalVipBox = totalVipBox;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public int getTotalRegistration() {
        return totalRegistration;
    }

    public int getTotalShortRegistration() {
        return totalShortRegistration;
    }

    public int getTotalMale() {
        return totalMale;
    }

    public int getTotalFemale() {
        return totalFemale;
    }

    public int getTotalVipBox() {
        return totalVipBox;
    }
}
