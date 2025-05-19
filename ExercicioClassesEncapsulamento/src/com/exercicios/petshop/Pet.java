package com.exercicios.petshop;
public class Pet {
    private final String nome;
    private boolean clean;

    public Pet(String nome) {
        this.nome = nome;
        this.clean = true;
    }
    public String getNome() {
        return nome;
    }
    public boolean isClean() {
        return clean;
    }
    public void setClean(boolean clean) {
        this.clean = clean;
    }
}
