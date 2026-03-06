package com.employeepayrollapp.dashboard;

/*
 Factory for dashboard creation.
*/

public class DashboardFactory {

    public static Dashboard getDashboard(String role) {

        if ("EMPLOYEE".equals(role)) {

            return new EmployeeDashboard();
        }

        else if ("MANAGER".equals(role)) {

            return new ManagerDashboard();
        }

        return null;
    }
}