package com.interfaces;

import com.account.Account;
import com.customer.Customer;

public interface IAccount {
    void deposit(double amount);
    void withdraw(double amount);
    void transferMoney(double amount, Account destination);
    void print();
}
