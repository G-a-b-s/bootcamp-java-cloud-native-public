package com.exercicios.petshop;

public class PetMachine {
    private static final int MAX_WATER = 30;
    private static final int MAX_SHAMPOO = 10;
    private static final int SHOWER_WATER_CONSUMPTION = 10;
    private static final int SHOWER_SHAMPOO_CONSUMPTION = 2;
    private static final int CLEAN_WATER_CONSUMPTION = 3;
    private static final int CLEAN_SHAMPOO_CONSUMPTION = 1;

    private int water;
    private int shampoo;
    private Pet pet;

    public PetMachine() {
        this.water = 0;
        this.shampoo = 0;
        this.pet = null;
    }

    public boolean addWater() {
        if (water + 2 <= MAX_WATER) {
            water += 2;
            return true;
        }
        return false;
    }

    public boolean addShampoo() {
        if (shampoo + 2 <= MAX_SHAMPOO) {
            shampoo += 2;
            return true;
        }
        return false;
    }

    public boolean canTakeShower() {
        return water >= SHOWER_WATER_CONSUMPTION && shampoo >= SHOWER_SHAMPOO_CONSUMPTION;
    }

    public boolean cleanMachine() {
        if (water >= CLEAN_WATER_CONSUMPTION && shampoo >= CLEAN_SHAMPOO_CONSUMPTION) {
            water -= CLEAN_WATER_CONSUMPTION;
            shampoo -= CLEAN_SHAMPOO_CONSUMPTION;
            return true;
        }
        return false;
    }

    public void takeShower() {
        if (canTakeShower()) {
            water -= SHOWER_WATER_CONSUMPTION;
            shampoo -= SHOWER_SHAMPOO_CONSUMPTION;
        }
    }

    public int getWater() {
        return water;
    }

    public int getShampoo() {
        return shampoo;
    }

    public boolean hasPet() {
        return pet != null;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet removePet() {
        Pet removedPet = this.pet;
        this.pet = null;
        return removedPet;
    }
}