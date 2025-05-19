package com.exercicios.Car;

public class CarController {
    private boolean isOn;
    private int speed;
    private int gear;

    public CarController() {
        this.isOn = false;
        this.speed = 0;
        this.gear = 0;
    }

    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Carro ligado.");
        } else {
            System.out.println("O carro já está ligado.");
        }
    }

    public void turnOff() {
        if (isOn && speed == 0 && gear == 0) {
            isOn = false;
            System.out.println("Carro desligado.");
        } else {
            System.out.println("Erro: Não é possível desligar o carro agora. Certifique-se de que ele está em ponto morto e parado.");
        }
    }

    public void accelerate() {
        if (!isOn) {
            System.out.println("Erro: O carro está desligado. Ligue o carro antes de acelerar.");
            return;
        }

        if (gear == 0) {
            System.out.println("Erro: O carro está em ponto morto. Troque para uma marcha válida antes de acelerar.");
            return;
        }

        if (speed >= 120) {
            System.out.println("Erro: Velocidade máxima atingida.");
            return;
        }

        if (isAtGearLimit()) {
            System.out.println("Você atingiu o limite da marcha " + gear + ". Troque para a próxima marcha para continuar acelerando.");
            return;
        }

        speed++;
        System.out.println("Acelerando... Velocidade atual: " + speed + " km/h");
    }

    public void decelerate() {
        if (speed > 0) {
            speed--;
            System.out.println("Diminuindo velocidade... Velocidade atual: " + speed + " km/h");
        } else {
            System.out.println("Erro: O carro já está parado.");
        }
    }

    public void changeGear(int newGear) {
        if (newGear < 0 || newGear > 6) {
            System.out.println("Erro: Marcha inválida.");
            return;
        }

        if (!isValidGearChange(newGear)) {
            System.out.println("Erro: Velocidade incompatível com a marcha " + newGear + ".");
            return;
        }

        gear = newGear;
        System.out.println("Marcha alterada para: " + gear);
    }

    public void turnLeft() {
        if (!isOn) {
            System.out.println("Erro: O carro está desligado. Ligue o carro antes de virar.");
            return;
        }

        if (speed >= 1 && speed <= 40) {
            System.out.println("Virando à esquerda.");
        } else {
            System.out.println("Erro: Velocidade inadequada para virar à esquerda. A velocidade deve estar entre 1 km/h e 40 km/h.");
        }
    }

    public void turnRight() {
        if (!isOn) {
            System.out.println("Erro: O carro está desligado. Ligue o carro antes de virar.");
            return;
        }

        if (speed >= 1 && speed <= 40) {
            System.out.println("Virando à direita.");
        } else {
            System.out.println("Erro: Velocidade inadequada para virar à direita. A velocidade deve estar entre 1 km/h e 40 km/h.");
        }
    }

    private boolean isAtGearLimit() {
        return switch (gear) {
            case 1 -> speed >= 20;
            case 2 -> speed >= 40;
            case 3 -> speed >= 60;
            case 4 -> speed >= 80;
            case 5 -> speed >= 100;
            case 6 -> speed >= 120;
            default -> false;
        };
    }

    private boolean isValidGearChange(int newGear) {
        return switch (newGear) {
            case 0 -> speed == 0;
            case 1 -> speed >= 0 && speed <= 20;
            case 2 -> speed >= 21 && speed <= 40;
            case 3 -> speed >= 41 && speed <= 60;
            case 4 -> speed >= 61 && speed <= 80;
            case 5 -> speed >= 81 && speed <= 100;
            case 6 -> speed >= 101 && speed <= 120;
            default -> false;
        };
    }

    public boolean isOn() {
        return isOn;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGear() {
        return gear;
    }
}