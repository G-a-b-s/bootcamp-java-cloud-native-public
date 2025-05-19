package com.account;

import com.agency.Agency;
import com.customer.Customer;

public class SavingsAccount extends Account {

    public SavingsAccount(Agency agency, double balance, Customer client) {
        super(agency, balance, client);
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount < this.balance)
            this.balance -= amount;
        else
            throw new RuntimeException("Insufficient balance");
    }

    @Override
    public void transferMoney(double amount, Account destination) {
        if(amount < this.balance){
            this.balance -= amount;
            destination.withdraw(amount);
        }
        else
            throw new RuntimeException("Not enough money");
    }

    @Override
    public void print() {
        System.out.println("-- Conta Poupança --");
        System.out.println("Cliente: " + this.customer.getSurname());
        System.out.println("Agencia: " + this.agency.getName() + "/" + this.agency.getNumber());
        System.out.println("Balance: " + this.balance);

    }
}
