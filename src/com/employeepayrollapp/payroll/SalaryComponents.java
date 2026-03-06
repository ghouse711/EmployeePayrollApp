package com.employeepayrollapp.payroll;

/*
 SalaryComponents groups all salary related values.

 This introduces COMPOSITION:
 Payslip owns SalaryComponents.
 SalaryComponents has no meaning without Payslip.
*/
public class SalaryComponents {

    public double basicSalary;
    public double hra;
    public double da;
    public double allowances;

    public double pf;
    public double tax;
    public double netPay;

    /*
     Constructor initializes earnings only.

     Deductions and net pay are calculated later.
    */
    public SalaryComponents(double basicSalary, double hra,
                            double da, double allowances) {

        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
    }
}