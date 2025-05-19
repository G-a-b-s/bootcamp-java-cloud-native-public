package com.exercicios.bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApp {
    public static void startBankApp(Scanner scanner) {
        BankAccount account = null;

        int option = -1;
        do {
            try {
                if (account == null) {
                    System.out.println("=========== Criação da Conta ===========");
                    System.out.print("Digite o nome do titular da conta: ");
                    scanner.nextLine(); // Limpa o buffer
                    String ownerName = scanner.nextLine();
                    System.out.print("Digite o valor inicial para depósito: ");
                    double initialDeposit = scanner.nextDouble();
                    account = new BankAccount(ownerName, initialDeposit);
                    System.out.println("Conta criada com sucesso!");
                    account.printAccountDetails();
                }

                System.out.println("=========== Menu Conta Bancária ===========");
                System.out.println("1 - Consultar saldo");
                System.out.println("2 - Consultar cheque especial");
                System.out.println("3 - Depositar dinheiro");
                System.out.println("4 - Sacar dinheiro");
                System.out.println("5 - Pagar boleto");
                System.out.println("6 - Verificar uso do cheque especial");
                System.out.println("7 - Detalhes da conta");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> System.out.println("Saldo atual: R$" + account.getBalance());
                    case 2 -> System.out.println("Limite de cheque especial: R$" + account.getOverdraftLimit());
                    case 3 -> {
                        System.out.print("Digite o valor para depósito: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                    }
                    case 4 -> {
                        System.out.print("Digite o valor para saque: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    }
                    case 5 -> {
                        System.out.print("Digite o valor do boleto: ");
                        double amount = scanner.nextDouble();
                        account.payBill(amount);
                    }
                    case 6 -> {
                        if (account.isUsingOverdraft()) {
                            System.out.println("Você está utilizando o cheque especial.");
                        } else {
                            System.out.println("Você não está utilizando o cheque especial.");
                        }
                    }
                    case 7 -> account.printAccountDetails();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        } while (option != 0);
    }
}