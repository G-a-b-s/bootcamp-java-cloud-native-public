package com.account;

import com.agency.Agency;
import com.customer.Customer;
import com.interfaces.IAccount;
import lombok.*;

@Getter
@Setter
@ToString
public abstract class Account implements IAccount {

    private static int numberSequence = 0;
    protected Agency agency;
    protected int number;
    protected double balance;
    protected Customer customer;

    public Account(Agency agency, double balance, Customer customer) {
        this.agency = agency;
        this.balance = balance;
        this.customer = customer;
        this.number = numberSequence++;
    }
}
