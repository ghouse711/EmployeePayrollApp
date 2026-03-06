package com.employeepayrollapp.util;


import java.util.regex.Pattern;

import com.employeepayrollapp.exceptions.ValidationException;

// This class is responsible for validating input
public class Validator {
	// Email Validation
	public static void validateEmail(String email) throws ValidationException{
		final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		if(email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null");
		if(!Pattern.matches(emailRegex, email)) throw new ValidationException("Invalid email format");
	}
	// Phone Validation
	public static void validatePhone(String phone) throws ValidationException{
	    final String  phoneRegex = "^(\\+91[-\\s]?)?[6-9]\\d{4}[-\\s]?\\d{5}$";
	    if(phone == null || phone.isBlank()) throw new IllegalArgumentException("Phone cannot be null");
	    if(!Pattern.matches(phoneRegex, phone)) throw new ValidationException("Invalid phone format");
	}
	
}
