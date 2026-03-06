package com.employeepayrollapp.dashboard;

import java.util.*;

/*
 EmployeeDashboard provides a personal view of payslip data.
*/

public class EmployeeDashboard implements Dashboard {

    @Override
    public void display(ArrayList<Payslip> payslips, Employee employee) {

        System.out.println("\n=== EMPLOYEE DASHBOARD ===");
        System.out.println("Welcome, " + employee.getName());

        System.out.println("Dashboard Type: " + this.getClass().getName());

        // Sort descending by net pay
        Collections.sort(payslips, new Comparator<Payslip>() {
            public int compare(Payslip p1, Payslip p2) {
                return (int)(p2.getNetPay() - p1.getNetPay());
            }
        });

        System.out.println("\nRecent Payslips (Top 3):");

        int count = 0;

        for (Payslip p : payslips) {

            if (count >= 3) break;

            System.out.println(p);

            count++;
        }

        double total = 0;

        for (Payslip p : payslips) {
            total += p.getNetPay();
        }

        System.out.println("\nYear-To-Date Earnings: " + total);
    }
}