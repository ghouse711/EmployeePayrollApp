package com.employeepayrollapp.employee;

import com.employeepayrollapp.util.PasswordUtil;

/*
 Represents a manager user.
*/
public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password, "MANAGER");
    }

    /*
     Manager authentication logic.
    */
    @Override
    public boolean authenticate(String username, String password) {

        if (!this.username.equals(username))
            return false;

        return PasswordUtil.verify(password, this.passwordHash);
    }
}