package com.exercicios.Car;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarApp {
    public static void startCarApp(Scanner scanner) {
        CarController car = new CarController();

        int option = -1;
        do {
            try {
                System.out.println("=========== Controle de Carro ===========");
                System.out.println("1 - Ligar o carro");
                System.out.println("2 - Desligar o carro");
                System.out.println("3 - Acelerar");
                System.out.println("4 - Diminuir velocidade");
                System.out.println("5 - Virar para esquerda");
                System.out.println("6 - Virar para direita");
                System.out.println("7 - Verificar velocidade");
                System.out.println("8 - Trocar marcha");
                System.out.println("0 - Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> car.turnOn();
                    case 2 -> car.turnOff();
                    case 3 -> car.accelerate();
                    case 4 -> car.decelerate();
                    case 5 -> car.turnLeft();
                    case 6 -> car.turnRight();
                    case 7 -> System.out.println("Velocidade atual: " + car.getSpeed() + " km/h");
                    case 8 -> {
                        System.out.print("Digite a marcha (0 a 6): ");
                        int gear = scanner.nextInt();
                        car.changeGear(gear);
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