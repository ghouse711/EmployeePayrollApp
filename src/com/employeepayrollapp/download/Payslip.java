package com.employeepayrollapp.download;

/*
 Payslip represents a finalized salary record.

 Key idea introduced here:
 - Immutability

 Once a payslip is generated:
 - Its data should never change
 - Any operation must use a copy

 Making the class final prevents inheritance-based modification.
*/

public final class Payslip implements Cloneable {

    private final String empId;
    private final String empName;
    private final String month;
    private final double netPay;

    public Payslip(String empId, String empName, String month, double netPay) {
        this.empId = empId;
        this.empName = empName;
        this.month = month;
        this.netPay = netPay;
    }

    public String getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getMonth() { return month; }
    public double getNetPay() { return netPay; }

    /*
     Creates a safe copy of the payslip.
    */
    @Override
    public Object clone() {
        return new Payslip(empId, empName, month, netPay);
    }

    /*
     Defines logical equality between two payslips.

     Two payslips are equal if:
     - Same employee
     - Same month
    */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Payslip)) return false;

        Payslip p = (Payslip) o;

        return empId.equals(p.empId) && month.equals(p.month);
    }

    /*
     hashCode implemented consistent with equals.
    */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + empId.hashCode();
        result = 31 * result + month.hashCode();

        return result;
    }

    /*
     Converts payslip data into readable text.
    */
    @Override
    public String toString() {

        return "PAYSLIP\n"
                + "Employee ID   : " + empId + "\n"
                + "Employee Name : " + empName + "\n"
                + "Month         : " + month + "\n"
                + "Net Pay       : " + netPay + "\n";
    }
}