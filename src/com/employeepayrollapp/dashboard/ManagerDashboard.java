package com.employeepayrollapp.dashboard;

import java.util.*;

/*
 ManagerDashboard provides aggregate information.
*/

public class ManagerDashboard implements Dashboard {

    @Override
    public void display(ArrayList<Payslip> payslips, Employee employee) {

        System.out.println("\n=== MANAGER DASHBOARD ===");
        System.out.println("Manager: " + employee.getName());

        System.out.println("Dashboard Type: " + this.getClass().getName());

        double total = 0;

        for (Payslip p : payslips) {

            total += p.getNetPay();
        }

        System.out.println("\nTeam Total YTD Earnings: " + total);
    }
}