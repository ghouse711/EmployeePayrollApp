package com.employeepayrollapp.employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.employeepayrollapp.util.Validator;
import com.employeepayrollapp.exceptions.ValidationException;

public class Employee {

    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private double salary;
    private String accountId;

    // Constructor with minimal information
    public Employee(String employeeId, String name) {

        if(employeeId == null || employeeId.isBlank())
            throw new IllegalArgumentException("Employee ID cannot be empty");

        this.employeeId = employeeId;
        this.name = name;

        this.accountId = generateAccountId();
    }


    // Constructor with full employee details
    // Uses Validator utility class for input validation
    public Employee(String employeeId, String name, String email,
                    String phone, double salary) throws ValidationException {

        if(employeeId == null || employeeId.isBlank())
            throw new IllegalArgumentException("Employee ID cannot be empty");

        if(name == null || name.isBlank())
            throw new IllegalArgumentException("Employee name cannot be empty");

        // Delegating validation responsibility to Validator class
        Validator.validateEmail(email);
        Validator.validatePhone(phone);

        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;

        this.accountId = generateAccountId();
    }



    // Generates a short unique account ID for payroll reference
    private String generateAccountId() {
        return "ACC-" + UUID.randomUUID().toString().substring(0,8);
    }



    // Overriding toString to print employee data
    // Also writes the employee record to a text file
    @Override
    public String toString() {

        String employeeData =
                "Employee ID : " + employeeId + "\n" +
                "Name        : " + name + "\n" +
                "Email       : " + email + "\n" +
                "Phone       : " + phone + "\n" +
                "Salary      : " + salary + "\n" +
                "Account ID  : " + accountId + "\n";

        saveEmployeeRecord(employeeData);

        return employeeData;
    }



    // Simulates persistence by writing employee details into a text file
    private void saveEmployeeRecord(String data) {

        try(FileWriter writer = new FileWriter("employees.txt", true)) {

            writer.write(data);
            writer.write("\n-----------------------------\n");

        } catch (IOException e) {
            System.out.println("Error writing employee data to file");
        }
    }

}