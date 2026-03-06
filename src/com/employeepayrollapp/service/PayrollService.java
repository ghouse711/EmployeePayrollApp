package com.employeepayrollapp.service;

import com.employeepayrollapp.employee.Employee;
import com.employeepayrollapp.payroll.Payslip;
import com.employeepayrollapp.payroll.SalaryComponents;

/*
 PayrollService contains salary calculation logic.

 Business rules should not be inside main().
*/
public class PayrollService {

    /*
     Generates a payslip by:
     - Creating salary components
     - Applying calculation rules
     - Returning a completed Payslip object
    */
    public Payslip generatePayslip(Employee employee,
                                   String month,
                                   double basic,
                                   double hra,
                                   double da,
                                   double allowances) {

        SalaryComponents sc =
                new SalaryComponents(basic, hra, da, allowances);

        // Gross salary
        double gross = basic + hra + da + allowances;

        // Statutory deductions
        sc.pf = basic * 0.12;
        sc.tax = gross * 0.10;

        // Net pay
        sc.netPay = gross - (sc.pf + sc.tax);

        return new Payslip(employee, sc, month);
    }
}