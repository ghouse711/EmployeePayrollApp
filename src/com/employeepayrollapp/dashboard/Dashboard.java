package com.employeepayrollapp.dashboard;

import java.util.ArrayList;

/*
 Dashboard defines a contract for all dashboards.
*/

public interface Dashboard {

    void display(ArrayList<Payslip> payslips, Employee employee);
}