package com.eteration.simplebanking.model;


import javax.persistence.Entity;

@Entity
public class WithdrawalTransaction  extends Transaction{

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    public WithdrawalTransaction() {

    }
    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        try {
            account.withdraw(this.amount);
        } catch (InsufficientBalanceException e) {
      throw new InsufficientBalanceException(e.getMessage());
        }

    }
}


