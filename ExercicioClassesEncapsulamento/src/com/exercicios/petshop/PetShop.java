package com.exercicios.petshop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetShop {
    public static void StartPetShop(Scanner scanner) {
        PetMachine petMachine = new PetMachine();

        int option = -1;
        do {
            try {
                System.out.println("=========== Controle do PetShop ===========");
                System.out.println("1 - Dar banho no pet");
                System.out.println("2 - Abastecer com água");
                System.out.println("3 - Abastecer com shampoo");
                System.out.println("4 - Verificar nível de água");
                System.out.println("5 - Verificar nível de shampoo");
                System.out.println("6 - Verificar se há pet na máquina");
                System.out.println("7 - Colocar pet na máquina");
                System.out.println("8 - Retirar pet da máquina");
                System.out.println("9 - Limpar máquina");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> {
                        if (!petMachine.hasPet()) {
                            System.out.println("Erro: Não há pet na máquina para dar banho.");
                        } else if (!petMachine.canTakeShower()) {
                            System.out.println("Erro: Recursos insuficientes (água ou shampoo) para dar banho.");
                        } else {
                            petMachine.takeShower();
                            System.out.println("Banho realizado com sucesso!");
                        }
                    }
                    case 2 -> {
                        if (petMachine.addWater()) {
                            System.out.println("Água abastecida com sucesso.");
                        } else {
                            System.out.println("Erro: A máquina já está com a capacidade máxima de água.");
                        }
                    }
                    case 3 -> {
                        if (petMachine.addShampoo()) {
                            System.out.println("Shampoo abastecido com sucesso.");
                        } else {
                            System.out.println("Erro: A máquina já está com a capacidade máxima de shampoo.");
                        }
                    }
                    case 4 -> System.out.println("Nível de água: " + petMachine.getWater() + " litros.");
                    case 5 -> System.out.println("Nível de shampoo: " + petMachine.getShampoo() + " litros.");
                    case 6 -> {
                        if (petMachine.hasPet()) {
                            System.out.println("Há um pet na máquina.");
                        } else {
                            System.out.println("Não há pet na máquina.");
                        }
                    }
                    case 7 -> {
                        if (petMachine.hasPet()) {
                            System.out.println("Erro: Já há um pet na máquina. Retire-o antes de colocar outro.");
                        } else {
                            System.out.print("Informe o nome do pet: ");
                            scanner.nextLine(); // Limpa o buffer
                            String petName = scanner.nextLine();
                            petMachine.setPet(new Pet(petName));
                            System.out.println("O pet " + petName + " foi colocado na máquina.");
                        }
                    }
                    case 8 -> {
                        if (!petMachine.hasPet()) {
                            System.out.println("Erro: Não há pet na máquina para retirar.");
                        } else {
                            Pet removedPet = petMachine.removePet();
                            System.out.println("O pet " + removedPet.getNome() + " foi retirado da máquina.");
                        }
                    }
                    case 9 -> {
                        if (petMachine.cleanMachine()) {
                            System.out.println("Máquina limpa com sucesso.");
                        } else {
                            System.out.println("Erro: Recursos insuficientes (água ou shampoo) para limpar a máquina.");
                        }
                    }
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