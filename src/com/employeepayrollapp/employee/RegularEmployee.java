package com.employeepayrollapp.employee;

import com.employeepayrollapp.util.PasswordUtil;

/*
 Represents a regular employee user.
*/
public class RegularEmployee extends User {

    public RegularEmployee(String username, String password) {
        super(username, password, "EMPLOYEE");
    }

    /*
     Employee authentication logic.
    */
    @Override
    public boolean authenticate(String username, String password) {

        if (!this.username.equals(username))
            return false;

        return PasswordUtil.verify(password, this.passwordHash);
    }
}