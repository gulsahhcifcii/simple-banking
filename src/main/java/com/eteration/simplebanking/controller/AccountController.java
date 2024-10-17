package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) {
        Account account = accountService.findAccount(accountNumber);
        accountService.credit(accountNumber,depositTransaction);
        return ResponseEntity.ok(new TransactionStatus("OK", UUID.randomUUID().toString()));
    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody  WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        accountService.debit(accountNumber,withdrawalTransaction);
        return ResponseEntity.ok(new TransactionStatus("OK", UUID.randomUUID().toString()));
     }
}