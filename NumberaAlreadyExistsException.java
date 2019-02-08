package com.bankingapplication.exceptions;

@SuppressWarnings("serial")
public class NumberaAlreadyExistsException extends Exception {
	@Override
	public String getMessage() {

		return "Mobile number already exists!";
	}
	
	

}
