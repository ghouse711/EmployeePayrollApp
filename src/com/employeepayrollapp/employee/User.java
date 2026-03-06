package com.employeepayrollapp.employee;

import com.employeepayrollapp.util.PasswordUtil;

/*
 User represents a generic system user.

 Introduces:
 - Abstract classes
 - Shared fields
 - Common authentication structure
*/
public abstract class User {

    protected String username;
    protected String passwordHash;
    protected String role;

    /*
     Constructor initializes shared user data.
     Password is immediately hashed.
    */
    public User(String username, String password, String role) {
        this.username = username;
        this.passwordHash = PasswordUtil.hash(password);
        this.role = role;
    }

    /*
     Subclasses must implement authentication logic.
    */
    public abstract boolean authenticate(String username, String password);

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}