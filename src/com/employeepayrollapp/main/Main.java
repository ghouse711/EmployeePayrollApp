package com.employeepayrollapp.main;
import com.employeepayrollapp.employee.*;

import java.util.Scanner;

import com.employeepayrollapp.exceptions.ValidationException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            Employee employee = new Employee(id, name, email, phone, salary);

            System.out.println("\nEmployee Registered Successfully\n");
            System.out.println(employee);

        }
        catch(ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}