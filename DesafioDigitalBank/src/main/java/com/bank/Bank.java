package com.bank;

import com.account.Account;
import com.agency.Agency;
import com.customer.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Bank {
    private List<Customer> customers;
    private List<Account> accounts;
    private List<Agency> agencies;

    protected String name;
    public Bank(String name){
        this.name = name;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.agencies = new ArrayList<>();
    }
    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }
    public void addAgency(Agency agency){
        this.agencies.add(agency);
    }
    public void transferMoney(Account from, Account to, double amount){
        Account fromAccount = accounts.stream().filter(x->x==from).findFirst().get();
        Account toAccount = accounts.stream().filter(x->x==to).findFirst().get();
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
    public void withdrawMoney(Account account, double amount){
        Account fromAccount = accounts.stream().filter(x->x==account).findFirst().get();
        fromAccount.withdraw(amount);
    }
    public void depositMoney(Account account, double amount){
        Account fromAccount = accounts.stream().filter(x->x==account).findFirst().get();
        fromAccount.deposit(amount);
    }

}
