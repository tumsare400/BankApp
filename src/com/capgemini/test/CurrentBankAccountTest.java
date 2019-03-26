package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.debitlimit.exception.DebitLimitExceededException;
import com.capgemini.main.BankAccount;
import com.capgemini.main.CurrentBankAccount;

public class CurrentBankAccountTest {

private BankAccount account;
	
	@Before
	public void setUp()
	{
		account=new CurrentBankAccount(101, "Payal", "SAVING", 45000, 10000);
	}
	
	@Test
	public void testCurrentBankAccount()
	{
		BankAccount account=new CurrentBankAccount();
		assertNotNull(account);
	}
	
	@Test
	public void testSavingBankAccountWithParameterized()
	{
		assertEquals(101, account.getAccount());
		assertEquals("Payal", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance(),0.01);
		assertEquals(10000, ((CurrentBankAccount) account).getDebitLimit(),0.01);
		assertEquals(0, ((CurrentBankAccount) account).getBorrowedAmount(),0.01);
		
	}
	
	@Test
	public void testCurrentBankAccountSufficientFund()
	{
		assertEquals(500, account.withdraw(44500),0.01);
	}
	
	@Test
	public void testCurrentBankAccountWithdrawwithinDebitLimit()
	{
		assertEquals(0, account.withdraw(51000),0.01);
		assertEquals(6000, ((CurrentBankAccount) account).getBorrowedAmount(), 0.01);
	}
	
	@Test(expected = DebitLimitExceededException.class)
	public void testCurrentBankAccountWithdrawwithDebitLimitExceeded()
	{
		assertEquals(45000, account.withdraw(56000),0.01);
		assertEquals(0, ((CurrentBankAccount) account).getBorrowedAmount(), 0.01);
	}
	
	@Test
	public void testDepositNoBorrowedAmount()
	{
		assertEquals(65000, account.deposit(20000),0.01);
	}
	
	@Test
	public void testDepositAmountGreaterThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000),0.01);
		assertEquals(2000, account.getBorrowedAmount(),0.01);
		assertEquals(8000, account.deposit(10000),0.01);
	}
	
	@Test
	public void testDepositAmountLessThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000),0.01);
		assertEquals(2000, account.getBorrowedAmount(),0.01);
		assertEquals(0, account.deposit(1000),0.01);
		assertEquals(1000, account.getBorrowedAmount(),0.01);
	}

}
