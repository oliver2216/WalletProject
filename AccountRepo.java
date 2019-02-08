package com.bakapplication.AccountRepo;
import com.bankingapplication.exceptions.*;
import com.bankapplication.beans.Customer;

public interface AccountRepo {
	
	boolean save (Customer customer) throws NumberaAlreadyExistsException;
	
	Customer searchAccount(String phoneNumber) throws MobileNumberDoesNotExistException;

}
