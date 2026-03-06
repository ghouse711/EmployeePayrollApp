package com.employeepayrollapp.service;

import java.io.FileWriter;
import java.io.IOException;

import com.employeepayrollapp.download.Payslip;

/*
 FileService handles saving payslip data to files.
*/

public class FileService {

    /*
     Saves payslip as text file.
    */
    public String savePayslipAsText(Payslip payslip) throws IOException {

        String fileName =
                "Payslip_" + payslip.getEmpId() + "_"
                        + System.currentTimeMillis()
                        + ".txt";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;
    }

    /*
     Saves payslip as PDF file (simulated).
    */
    public String savePayslipAsPdf(Payslip payslip) throws IOException {

        String fileName =
                "Payslip_" + payslip.getEmpId() + "_"
                        + System.currentTimeMillis()
                        + ".pdf";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;
    }
}