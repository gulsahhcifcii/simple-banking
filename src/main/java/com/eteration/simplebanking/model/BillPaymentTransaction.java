package com.eteration.simplebanking.model;


import javax.persistence.Entity;

@Entity
public class BillPaymentTransaction extends Transaction{

    private String payee;

    public BillPaymentTransaction(double v) {
        super();
    }
    public BillPaymentTransaction() {
    }

    @Override
    public void execute(Account account) {

    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}
