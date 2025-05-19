package com.main;

import com.account.CurrentAccount;
import com.account.SavingsAccount;
import com.agency.Agency;
import com.bank.Bank;
import com.customer.Customer;
import com.interfaces.IAccount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       //Criando o banco
        Bank bank = new Bank("Novo Banco");
        //Criando Agencias
        Agency agency = new Agency("Agencia 1","Lugar 1");
        Agency agency2 = new Agency("Agencia 2","Lugar 2");
        Agency agency3 = new Agency("Agencia 3","Lugar 3");
        bank.addAgency(agency);
        bank.addAgency(agency2);
        bank.addAgency(agency3);

        //Criando um cliente
            //Dados de um cliente
            String nome = "Gabriel";
            String cpf = "1234567890";
            String surname = "Gabriel Rocha de Oliveira";
            Customer customer = new Customer(nome, cpf, surname);
        bank.addCustomer(customer);
            //Dados de mais um cliente
            String nome1 = "Leirbag";
            String cpf2 = "0987654321";
            String surname3 = "Leirbag Rocha de Oliveira";
            Customer customer2 = new Customer(nome, cpf, surname);
        bank.addCustomer(customer2);

        System.out.println("Criando uma conta corrente para esse cliente na Agencia 2");
        CurrentAccount account = new CurrentAccount(agency2, 0, customer);
        bank.addAccount(account);

        System.out.println("Criando uma conta poupança para o cliente Gabriel na Agencia 3");
        SavingsAccount account2 = new SavingsAccount(agency3, 0, customer);
        bank.addAccount(account2);

        System.out.println("Criando uma conta poupanca para o cliente Leirbag na Agencia 1");
        SavingsAccount account3 = new SavingsAccount(agency,0,customer2);
        bank.addAccount(account3);

        System.out.println("Listando as contas no banco");
        PrintBankAccounts(bank);

        System.out.println("Colocando 100 reais de credito em cada conta");
        bank.getAccounts().forEach(x -> x.deposit(100));
        PrintBankAccounts(bank);

        System.out.println("Colocando 50 reais na conta poupanca de Gabriel");
        bank.depositMoney(account2,50);
        PrintBankAccounts(bank);


        System.out.println("Sacando 30 reais da conta corrente de Gabriel");
        bank.withdrawMoney(account,30);
        PrintBankAccounts(bank);


        System.out.println("Passando 50 reais da conta corrente de Gabriel uma conta para para conta poupança de Gabriel");
        bank.transferMoney(account,account2,50);
        PrintBankAccounts(bank);


    }
    public static void PrintBankAccounts(Bank bank){
        bank.getAccounts().forEach(IAccount::print);
    }
}