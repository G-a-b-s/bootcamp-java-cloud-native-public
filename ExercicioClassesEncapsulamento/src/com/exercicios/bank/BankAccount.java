package com.exercicios.bank;

public class BankAccount {
    private String ownerName;
    private double balance;
    private double overdraftLimit;
    private double overdraftUsed;

    public BankAccount(String ownerName, double initialDeposit) {
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        if (initialDeposit <= 500) {
            this.overdraftLimit = 50;
        } else {
            this.overdraftLimit = initialDeposit * 0.5;
        }
        this.overdraftUsed = 0;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean isUsingOverdraft() {
        return overdraftUsed > 0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Erro: O valor do depósito deve ser maior que zero.");
            return;
        }

        if (overdraftUsed > 0) {
            double overdraftRepayment = Math.min(amount, overdraftUsed * 1.2);
            overdraftUsed -= overdraftRepayment / 1.2;
            amount -= overdraftRepayment;
        }

        balance += amount;
        System.out.println("Depósito realizado com sucesso. Saldo atual: R$" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Erro: O valor do saque deve ser maior que zero.");
            return;
        }

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Saque realizado com sucesso. Saldo atual: R$" + balance);
        } else if (amount <= balance + overdraftLimit - overdraftUsed) {
            overdraftUsed += (amount - balance);
            balance = 0;
            System.out.println("Saque realizado utilizando cheque especial. Valor usado do cheque especial: R$" + overdraftUsed);
        } else {
            System.out.println("Erro: Saldo insuficiente para realizar o saque.");
        }
    }

    public void payBill(double amount) {
        if (amount <= 0) {
            System.out.println("Erro: O valor do boleto deve ser maior que zero.");
            return;
        }

        System.out.println("Pagando boleto no valor de R$" + amount + "...");
        withdraw(amount);
    }

    public void printAccountDetails() {
        System.out.println("=========== Detalhes da Conta ===========");
        System.out.println("Titular: " + ownerName);
        System.out.println("Saldo: R$" + balance);
        System.out.println("Limite de Cheque Especial: R$" + overdraftLimit);
        System.out.println("Valor Usado do Cheque Especial: R$" + overdraftUsed);
        System.out.println("=========================================");
    }
}