package com.eteration.simplebanking.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String accountNumber;
    public String owner;
    public double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    public List<Transaction> transactions = new ArrayList<>();

    public Account() {}

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }
    public void post(Transaction transaction) throws InsufficientBalanceException {
        transactions.add(transaction);
        try {
            transaction.execute(this);
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Yetersiz bakiye");
        }
    }


    public void deposit(double amount) {
    System.out.println("amount" + amount);
        this.balance =this.balance +  amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Yetersiz bakiye");
        }
        this.balance -= amount;
    }
}