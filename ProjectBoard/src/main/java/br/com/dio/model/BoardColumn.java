package br.com.dio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardColumn {
    private int id;
    private int boardId;
    private String name;
    private int position;
    private String type; // INICIAL, PENDENTE, FINAL, CANCELAMENTO

    // Getters, setters e construtores
}