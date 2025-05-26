package br.com.dio.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class BoardCard {
    private int id;
    private int columnId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean isBlocked;
    private String blockReason;
    private String unblockReason;

    // Getters, setters e construtores
}