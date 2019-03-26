package com.capgemini.debitlimit.exception;

public class DebitLimitExceededException extends RuntimeException {
	
	public DebitLimitExceededException(String message) {
		super(message);
	}
}
