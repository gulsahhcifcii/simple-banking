package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {

  public DepositTransaction(double amount) {
    super(amount);
  }

  public DepositTransaction() {
    super(0);
  }

  @Override
  public void execute(Account account) {
    account.deposit(this.amount);
  }
}
