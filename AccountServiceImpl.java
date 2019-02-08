package com.bankingapplication.service;
import com.bankingapplication.exceptions.*;

import java.math.BigDecimal;

import com.bakapplication.AccountRepo.AccountRepo;
import com.bakapplication.AccountRepo.AccountRepoImpl;
import com.bankapplication.beans.Customer;
import com.bankapplication.beans.Wallet;

public class AccountServiceImpl implements AccountService {

	AccountRepo accountrepoImpl;
	public AccountServiceImpl(AccountRepoImpl accountRepo) {
	
		super();
		this.accountrepoImpl=accountRepo;
	}
	
	@Override
	public Customer createAccount(String phoneNumber,String name, Wallet wallet)throws NumberaAlreadyExistsException{
		
		Customer customer = new Customer();
		customer.setMobileNumber(phoneNumber);
		customer.setWallet(wallet);
		customer.setName(name);
		if(accountrepoImpl.save(customer))
		{
			System.out.println("Done");
			return customer;
		}
		return null;
	}

	@Override
	public String Deposit(String accountNumber, BigDecimal amount) throws MobileNumberDoesNotExistException {
		
			Customer customer = accountrepoImpl.searchAccount(accountNumber);
			
			customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
			
			return "Balance after Deposit: "+customer.getWallet().getBalance();
			
	}

	@Override
	public boolean withdraw(String phoneNumber, BigDecimal amount) throws MobileNumberDoesNotExistException,InsufficientBalanceException {
	
		Customer account = accountrepoImpl.searchAccount(phoneNumber);
		
		if((account.getWallet().getBalance().subtract(amount).compareTo(new BigDecimal(0)))<0) {
			throw new InsufficientBalanceException();
		}
		
			account.getWallet().setBalance(account.getWallet().getBalance().subtract(amount));
			//return "Balance after deduction: "+account.getWallet().getBalance();
			return true;
			
		
	}

	public String fundsTransfer(String ac1, String ac2, BigDecimal amount) throws MobileNumberDoesNotExistException,InsufficientBalanceException {
		Customer cs1=accountrepoImpl.searchAccount(ac1);
		Customer cs2=accountrepoImpl.searchAccount(ac2);
		BigDecimal c=cs1.getWallet().getBalance().subtract(amount);
		int b= c.compareTo(new BigDecimal(0));
		if(b>=0) {
			
			cs1.getWallet().setBalance(cs1.getWallet().getBalance().subtract(amount));
			cs2.getWallet().setBalance(cs2.getWallet().getBalance().add(amount));
			return "Balance after deduction and transfer respectively: "+cs1.getWallet().getBalance()+" "+cs2.getWallet().getBalance();
		}
		return ac2;

	}

	@Override
	public Customer Show(String phoneNumber) throws MobileNumberDoesNotExistException {
		
		Customer account = accountrepoImpl.searchAccount(phoneNumber);
		
		return account;
	}

}