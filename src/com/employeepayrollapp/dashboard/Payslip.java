package com.employeepayrollapp.dashboard;

/*
 Payslip represents a simplified salary record.

 Dashboard only needs:
 - Month
 - Net pay
*/

public class Payslip {

    private String month;
    private double netPay;

    public Payslip(String month, double netPay) {
        this.month = month;
        this.netPay = netPay;
    }

    public String getMonth() {
        return month;
    }

    public double getNetPay() {
        return netPay;
    }

    @Override
    public String toString() {
        return month + " : " + netPay;
    }
}