package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

   AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }
  public Account findAccount(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }

  public void credit(String accountNumber, DepositTransaction depositTransaction) {
    Account account = findAccount(accountNumber);
    depositTransaction.execute(account);
  }

  public void debit(String accountNumber, WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
    Account account = findAccount(accountNumber);
    withdrawalTransaction.execute(account);
  }
}
