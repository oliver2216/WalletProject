package com.bakapplication.AccountRepo;

import java.util.HashMap;
import java.util.Iterator;

import com.bankapplication.beans.Customer;
import com.bankingapplication.exceptions.MobileNumberDoesNotExistException;
import com.bankingapplication.exceptions.NumberaAlreadyExistsException;

public class AccountRepoImpl implements AccountRepo {

	HashMap<String,Customer> accounts= new HashMap<String,Customer>();
	@Override
	public boolean save(Customer customer) throws NumberaAlreadyExistsException  {
			if(accounts.containsKey(customer.getMobileNumber()))
				throw new NumberaAlreadyExistsException();
			accounts.put(customer.getMobileNumber(),customer);
			return true;
		
	}

	@Override
	public Customer searchAccount(String phoneNumber) throws MobileNumberDoesNotExistException {
		
		if(accounts.containsKey(phoneNumber)) {
			Iterator<Customer> iterator =accounts.values().iterator();
			while(iterator.hasNext()) {
				Customer customer = iterator.next();
				if(customer.getMobileNumber().equals(phoneNumber)) {
					System.out.println("Found");
					return customer;
				}	
			}
		}
		else
			throw new MobileNumberDoesNotExistException();
		return null;
	}
}
