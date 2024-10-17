package com.eteration.simplebanking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  public Date date;
  public Double amount;

  @ManyToOne
  private Account account;

  public Transaction() {}

  public void setAccount(Account account) {
    this.account = account;
  }

  public abstract void execute(Account account) throws InsufficientBalanceException;
  public Transaction(double amount) {
    this.date = new Date();
    this.amount = amount;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
