package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.Insufficient.exception.InsufficientFundException;
import com.capgemini.main.BankAccount;
import com.capgemini.main.SavingAccount;

 public class SavingAccountTest {

	 private BankAccount account;
		
		@Before
		public void setUp()
		{
			account=new SavingAccount(101, "Payal", "CURRENT", 45000, true);
		}
		
		@Test
		public void testSavingBankAccount()
		{
			BankAccount account=new SavingAccount();
			assertNotNull(account);
		}
		
		@Test
		public void testSavingBankAccountWithParameterized()
		{
			BankAccount account=new SavingAccount(101, "Payal", "CURRENT", 45000, true);
			assertEquals(101, account.getAccount());
			assertEquals("Payal", account.getAccountHolderName());
			assertEquals("CURRENT", account.getAccountType());
			assertEquals(45000, account.getAccountBalance(),0.01);
			assertEquals(true, ((SavingAccount) account).isSalaryAccount());
		}
		
		@Test
		public void testWithdrawWithSalaryAccount()
		{
			assertEquals(500, account.withdraw(44500),0.01);
		}
		
		@Test
		public void testWithdrawWithSalaryAccountWithInsufficientFund()
		{
			assertEquals(45000, account.withdraw(45001),0.01);
		}
		
		@Test
		public void testWithdrawWithNormalAccount()
		{
			BankAccount account=new SavingAccount(101, "Payal", "CURRENT", 45000, false);
			assertEquals(1200, account.withdraw(43800),0.01);
		}

		@Test(expected=InsufficientFundException.class)
		public void testSavingBankAccountWithdrawWithNormalAccountWithInsufficientFund()
		{
			BankAccount account=new SavingAccount(101, "Payal", "CURRENT", 45000, false);
			assertEquals(45000, account.withdraw(44100),0.01);
		}
		
		@Test
		public void testSavingBankAccountDeposit()
		{
			BankAccount account=new SavingAccount(101, "Payal","CURRENT", 45000, false);
			assertEquals(90000, account.deposit(45000),0.01);
		}

}
