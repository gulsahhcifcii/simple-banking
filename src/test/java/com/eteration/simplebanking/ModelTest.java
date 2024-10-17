package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.owner.equals("Kerem Karaca"));
		assertTrue(account.accountNumber.equals("17892"));
		assertTrue(account.balance == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.balance == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		account.deposit(100);
		assertTrue(account.balance == 100);
		account.withdraw(50);
		assertTrue(account.balance == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			account.deposit(100);
			account.withdraw(500);
		  });

	}
	
	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.transactions.size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		assertTrue(depositTrx.date != null);
		account.post(depositTrx);
		assertTrue(account.balance == 100);
		assertTrue(account.transactions.size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
		assertTrue(withdrawalTrx.date != null);
		account.post(withdrawalTrx);
		assertTrue(account.balance == 40);
		assertTrue(account.transactions.size() == 2);
	}
}
