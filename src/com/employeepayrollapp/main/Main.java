package com.employeepayrollapp.main;

import java.util.Scanner;

import com.employeepayrollapp.employee.Employee;
import com.employeepayrollapp.employee.Session;
import com.employeepayrollapp.exceptions.ValidationException;
import com.employeepayrollapp.service.AuthenticationService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AuthenticationService authService = new AuthenticationService();

        while (true) {

            System.out.println("\n===== Employee Payroll System =====");
            System.out.println("1. Register Employee");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Enter Employee ID: ");
                        String id = scanner.nextLine();

                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine());

                        System.out.print("Create Username: ");
                        String username = scanner.nextLine();

                        System.out.print("Create Password: ");
                        String password = scanner.nextLine();

                        /*
                         Create employee object (UC1)
                        */
                        Employee employee = new Employee(id, name, email, phone, salary);

                        /*
                         Register the user for authentication (UC2)
                        */
                        authService.registerUser(username, password);

                        System.out.println("\nEmployee Registered Successfully");
                        System.out.println(employee);

                        break;

                    case 2:

                        Session session = authService.login();

                        if (session != null) {

                            System.out.println(session);

                            if (session.isExpired()) {
                                System.out.println("Session expired");
                            } else {
                                System.out.println("Session still active");
                            }
                        }

                        break;

                    case 3:

                        System.out.println("Exiting application...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice");

                }

            } catch (ValidationException e) {

                System.out.println("Validation Error: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}