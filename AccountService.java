package com.bankingapplication.service;
import java.math.BigDecimal;

import com.bankapplication.beans.Customer;
import com.bankapplication.beans.Wallet;
import com.bankingapplication.exceptions.InsufficientBalanceException;
import com.bankingapplication.exceptions.MobileNumberDoesNotExistException;
import com.bankingapplication.exceptions.NumberaAlreadyExistsException;

public interface AccountService {
	
	
	Customer createAccount(String phoneNumber,String name, Wallet wallet) throws NumberaAlreadyExistsException;
	
	String Deposit(String phoneNumber,BigDecimal amount)throws MobileNumberDoesNotExistException;
	
	boolean withdraw(String phoneNumber,BigDecimal amount)throws MobileNumberDoesNotExistException, InsufficientBalanceException;
	
	Customer Show(String phoneNumber) throws MobileNumberDoesNotExistException;
	

}
