package com.employeepayrollapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.employeepayrollapp.employee.Manager;
import com.employeepayrollapp.employee.RegularEmployee;
import com.employeepayrollapp.employee.Session;
import com.employeepayrollapp.employee.User;

public class AuthenticationService {

    // Map storing users with username as key
    private Map<String, User> users = new HashMap<>();

    // Maximum login attempts allowed
    private int maxAttempts = 3;

    public AuthenticationService() {

        // Default users for demonstration
        users.put("emp1", new RegularEmployee("emp1", "Emp01234"));
        users.put("manager1", new Manager("manager1", "Mng01234"));
    }


    /*
     Registers a new user account.
     Called when employee registration happens.
    */
    public void registerUser(String username, String password) {

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        users.put(username, new RegularEmployee(username, password));

        System.out.println("User account created successfully.");
    }


    /*
     Handles login flow.
    */
    public Session login() {

        Scanner scanner = new Scanner(System.in);

        int attempts = 0;

        while (attempts < maxAttempts) {

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = users.get(username);

            if (user == null) {
                System.out.println("User not found.");
                attempts++;
                continue;
            }

            // Polymorphism happens here
            if (user.authenticate(username, password)) {

                System.out.println("Login successful.");

                Session session = new Session(username);

                showDashboard(user.getRole());

                return session;
            }

            attempts++;

            System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
        }

        System.out.println("Maximum login attempts exceeded.");

        return null;
    }


    /*
     Shows dashboard based on role.
    */
    private void showDashboard(String role) {

        if (role.equals("MANAGER")) {
            System.out.println("Manager Dashboard Loaded.");
        } else {
            System.out.println("Employee Dashboard Loaded.");
        }
    }
}