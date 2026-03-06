package com.employeepayrollapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 This utility class handles password hashing.

 Passwords are converted into SHA-256 hashes
 before being stored or compared.
*/
public class PasswordUtil {

    /*
     Converts plain password into SHA-256 hash.
    */
    public static String hash(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password");
        }
    }

    /*
     Verifies password by hashing input and comparing.
    */
    public static boolean verify(String password, String storedHash) {

        String newHash = hash(password);

        return newHash.equals(storedHash);
    }
}