package com.employeepayrollapp.main;

import java.util.Scanner;

import com.employeepayrollapp.employee.Employee;
import com.employeepayrollapp.employee.Session;
import com.employeepayrollapp.exceptions.ValidationException;
import com.employeepayrollapp.service.AuthenticationService;
import com.employeepayrollapp.service.PayrollService;
import com.employeepayrollapp.payroll.Payslip;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AuthenticationService authService = new AuthenticationService();
        PayrollService payrollService = new PayrollService();

        Employee employee = null;
        Payslip payslip = null;
        String month = null;

        while (true) {

        	System.out.println("\n===== Employee Payroll System =====");
        	System.out.println("1 Register Employee");
        	System.out.println("2 Login");
        	System.out.println("3 Generate Payslip");
        	System.out.println("4 Download Payslip");
        	System.out.println("5 Dashboard");
        	System.out.println("6 Exit");

            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Employee ID: ");
                        String id = scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();

                        System.out.print("Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine());

                        System.out.print("Username: ");
                        String username = scanner.nextLine();

                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        employee = new Employee(id, name, email, phone, salary);

                        authService.registerUser(username, password);

                        System.out.println("Employee registered successfully.");

                        break;

                    case 2:

                        Session session = authService.login();

                        if (session != null) {
                            System.out.println(session);
                        }

                        break;

                    case 3:

                        if (employee == null) {
                            System.out.println("Please register employee first.");
                            break;
                        }

                        System.out.print("Month: ");
                        month = scanner.nextLine();

                        System.out.print("Basic Salary: ");
                        double basic = Double.parseDouble(scanner.nextLine());

                        System.out.print("HRA: ");
                        double hra = Double.parseDouble(scanner.nextLine());

                        System.out.print("DA: ");
                        double da = Double.parseDouble(scanner.nextLine());

                        System.out.print("Allowances: ");
                        double allowances = Double.parseDouble(scanner.nextLine());

                        payslip =
                                payrollService.generatePayslip(
                                        employee,
                                        month,
                                        basic,
                                        hra,
                                        da,
                                        allowances
                                );

                        System.out.println(payslip);

                        break;
                    
                    case 4:

                        if (payslip == null) {
                            System.out.println("Please generate a payslip first.");
                            break;
                        }

                        try {

                            // Create immutable download copy
                            com.employeepayrollapp.download.Payslip downloadPayslip =
                                    new com.employeepayrollapp.download.Payslip(
                                            employee.getEmpId(),
                                            employee.getName(),
                                            month,
                                            payslip.getComponents().netPay
                                    );

                            // Clone for safe download
                            com.employeepayrollapp.download.Payslip cloned =
                                    (com.employeepayrollapp.download.Payslip) downloadPayslip.clone();

                            // Validate token
                            com.employeepayrollapp.download.DownloadToken token =
                                    new com.employeepayrollapp.download.DownloadToken();

                            if (token.isExpired()) {
                                System.out.println("Download link expired.");
                                break;
                            }

                            // Save files
                            com.employeepayrollapp.service.FileService fileService =
                                    new com.employeepayrollapp.service.FileService();

                            String txt = fileService.savePayslipAsText(cloned);
                            String pdf = fileService.savePayslipAsPdf(cloned);

                            System.out.println("Payslip saved:");
                            System.out.println("TXT → " + txt);
                            System.out.println("PDF → " + pdf);

                            System.out.println("\nDownloaded Payslip:");
                            System.out.println(cloned);

                        } catch (Exception e) {
                            System.out.println("Download failed.");
                        }

                        break;
                    
                    case 5:

                        System.out.print("Enter Employee ID: ");
                        String empId = scanner.nextLine();

                        System.out.print("Enter Employee Name: ");
                        String empName = scanner.nextLine();

                        System.out.print("Enter Role (EMPLOYEE/MANAGER): ");
                        String role = scanner.nextLine();

                        com.employeepayrollapp.dashboard.Employee dashEmployee =
                                new com.employeepayrollapp.dashboard.Employee(empId, empName, role);

                        ArrayList<com.employeepayrollapp.dashboard.Payslip> payslips =
                                new ArrayList<>();

                        // Sample payslip data
                        payslips.add(new com.employeepayrollapp.dashboard.Payslip("Jan", 30000));
                        payslips.add(new com.employeepayrollapp.dashboard.Payslip("Feb", 32000));
                        payslips.add(new com.employeepayrollapp.dashboard.Payslip("Mar", 31000));
                        payslips.add(new com.employeepayrollapp.dashboard.Payslip("Apr", 33000));
                        payslips.add(new com.employeepayrollapp.dashboard.Payslip("May", 34000));

                        com.employeepayrollapp.dashboard.Dashboard dashboard =
                                com.employeepayrollapp.dashboard.DashboardFactory.getDashboard(role);

                        if (dashboard != null) {

                            dashboard.display(payslips, dashEmployee);
                        }

                        break;

                    case 6:

                        System.out.println("Exiting application...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (ValidationException e) {

                System.out.println("Validation error: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}