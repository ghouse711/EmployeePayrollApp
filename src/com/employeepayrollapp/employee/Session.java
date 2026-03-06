package com.employeepayrollapp.employee;

/*
 Session represents a logged-in user state.
*/
public class Session {

    private String username;
    private long loginTime;
    private long timeoutMillis;

    public Session(String username) {
        this.username = username;
        this.loginTime = System.currentTimeMillis();

        // session timeout set to 5 minutes
        this.timeoutMillis = 5 * 60 * 1000;
    }

    /*
     Checks if session is expired.
    */
    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();
        return currentTime - loginTime > timeoutMillis;
    }

    @Override
    public String toString() {
        return "Session active for user: " + username;
    }
}