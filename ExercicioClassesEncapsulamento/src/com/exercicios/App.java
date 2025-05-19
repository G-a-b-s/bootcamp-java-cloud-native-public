package com.exercicios;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.exercicios.Car.CarApp;
import com.exercicios.bank.BankApp;
import com.exercicios.petshop.PetShop;

public class App {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        scanner.useDelimiter("\n");
        int option = -1;

        do {
            try {
                System.out.println("=========== Menu Principal ===========");
                System.out.println("1 - Conta Bancária");
                System.out.println("2 - Controle de Carro");
                System.out.println("3 - PetShop");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> BankApp.startBankApp(scanner);
                    case 2 -> CarApp.startCarApp(scanner);
                    case 3 -> PetShop.StartPetShop(scanner);
                    case 0 -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        } while (option != 0);

        scanner.close();
    }
}