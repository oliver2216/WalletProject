package com.bankapplication.test;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import com.bakapplication.AccountRepo.AccountRepoImpl;
import com.bankapplication.beans.Customer;
import com.bankapplication.beans.Wallet;
import com.bankingapplication.exceptions.InsufficientBalanceException;
import com.bankingapplication.exceptions.MobileNumberDoesNotExistException;
import com.bankingapplication.exceptions.NumberaAlreadyExistsException;
import com.bankingapplication.service.AccountServiceImpl;

public class BankApplicationTests {

	Customer account = new Customer();
	AccountRepoImpl accountRepo= new AccountRepoImpl();
	AccountServiceImpl accountServiceimpl= new AccountServiceImpl(accountRepo);
	
	
	

	@Before
	public void setUp() throws Exception {
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		accountServiceimpl.createAccount("112","Sagar", wallet);
		
		Wallet wallet2 = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		account=accountServiceimpl.createAccount("12","Sagar", wallet2);
		
	}
	
	
	/* create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
//	@Test
//	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws NumberaAlreadyExistsException
//	{
//		Wallet wallet = new Wallet();
//		wallet.setBalance(new BigDecimal(500) );
//		assertEquals(account, accountServiceimpl.createAccount("112","Sagar", wallet));
//	}
	
	@Test(expected=com.bankingapplication.exceptions.NumberaAlreadyExistsException.class)
	
	public void whenSameNumberIsUsedToCreateMultiPleAccounts() throws NumberaAlreadyExistsException {
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500) );
		accountServiceimpl.createAccount("12", "Sagar", wallet);
		
	}
	
	
	@Test(expected=com.bankingapplication.exceptions.MobileNumberDoesNotExistException.class)
	
  public void whenInvalidAccountNumberIsPassedForDeposit() throws MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
		
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		accountServiceimpl.Deposit("105", new BigDecimal(2000));
		
	}
	
@Test
	public void whenValidAccountNumberIsPassedSystemShouldDepositMoney() throws MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
	Wallet wallet2 = new Wallet();
	wallet2.setBalance(new BigDecimal(500));
	account=accountServiceimpl.createAccount("121","Sagar", wallet2);
		String str="Balance after Deposit: "+(account.getWallet().getBalance().add(new BigDecimal(2000)));
		assertEquals(str,accountServiceimpl.Deposit("121", new BigDecimal(2000)));
	}
	
	@Test(expected=com.bankingapplication.exceptions.InsufficientBalanceException.class)
	
	public void whenBalanceIsNotSufficientSystemShouldThrowException() throws InsufficientBalanceException, MobileNumberDoesNotExistException, NumberaAlreadyExistsException{
		
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		accountServiceimpl.createAccount("102","Sagar", wallet);
		accountServiceimpl.withdraw("102",new BigDecimal(1500));
		
	}
	
	@Test
	public void whenBalanceIsSufficientAndAccountNumberIsCorrectSysytemShouldDeductMoney() throws MobileNumberDoesNotExistException, InsufficientBalanceException, NumberaAlreadyExistsException{
		accountServiceimpl= new AccountServiceImpl(accountRepo);
		Wallet wallet = new Wallet();
		wallet.setBalance(new BigDecimal(500));
		accountServiceimpl.createAccount("102","Sagar", wallet);
		assertEquals(true,accountServiceimpl.withdraw("102",new BigDecimal(500)));
	
	}	
//	@Test
//	public void when
}


