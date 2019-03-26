package com.capgemini.main;

import com.capgemini.Insufficient.exception.InsufficientFundException;

public class SavingAccount extends BankAccount {
	
	private boolean salaryAccount;
	public static final double miniumBalance=1000;
	public SavingAccount() {
	}

	public SavingAccount(long accountId, String accountHolderName, String accountType, double accountBalance, boolean salaryAccount) {
		super(accountId, accountHolderName, accountType, accountBalance);
		this.salaryAccount=salaryAccount;
	}

	public boolean isSalaryAccount() {
		return salaryAccount;
	}

	public void setSalaryAccount(boolean salaryAccount) {
		this.salaryAccount=salaryAccount;
	}
	
	@Override
	public double withdraw(double amount) 
	{
		if(salaryAccount) 
			return super.withdraw(amount);
		else 
		{
			if(getAccountBalance()-amount>= miniumBalance)
				setAccountBalance(getAccountBalance() - amount); 
			else
				throw new InsufficientFundException("you don't have sufficient fund.");
			return getAccountBalance();
		}
	}
}





