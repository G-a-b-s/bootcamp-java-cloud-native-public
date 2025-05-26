package br.com.dio.service;

import br.com.dio.model.BoardCard;
import br.com.dio.repository.BoardCardRepository;

import java.sql.SQLException;
import java.util.List;

public class BoardCardService {
    private final BoardCardRepository boardCardRepository;

    public BoardCardService(BoardCardRepository boardCardRepository) {
        this.boardCardRepository = boardCardRepository;
    }

    public List<BoardCard> listCardsByColumnId(int columnId) {
        try {
            return boardCardRepository.findAllByColumnId(columnId);
        } catch (SQLException e) {
            throw new RuntimeException("Error listing cards for column", e);
        }
    }

    public BoardCard getCardById(int id) {
        try {
            return boardCardRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding card by id", e);
        }
    }

    public void createCard(BoardCard card) {
        try {
            boardCardRepository.save(card);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating card", e);
        }
    }

    public void updateCard(BoardCard card) {
        try {
            boardCardRepository.update(card);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating card", e);
        }
    }

    public void deleteCard(int id) {
        try {
            boardCardRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting card", e);
        }
    }
}