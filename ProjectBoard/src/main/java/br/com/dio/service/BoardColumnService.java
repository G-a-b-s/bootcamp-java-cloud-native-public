package br.com.dio.service;

import br.com.dio.model.BoardColumn;
import br.com.dio.repository.BoardColumnRepository;

import java.sql.SQLException;
import java.util.List;

public class BoardColumnService {
    private final BoardColumnRepository boardColumnRepository;

    public BoardColumnService(BoardColumnRepository boardColumnRepository) {
        this.boardColumnRepository = boardColumnRepository;
    }

    public List<BoardColumn> listColumnsByBoardId(int boardId) {
        try {
            return boardColumnRepository.findAllByBoardId(boardId);
        } catch (SQLException e) {
            throw new RuntimeException("Error listing columns for board", e);
        }
    }

    public BoardColumn getColumnById(int id) {
        try {
            return boardColumnRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding column by id", e);
        }
    }

    public void createColumn(BoardColumn column) {
        try {
            boardColumnRepository.save(column);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating column", e);
        }
    }

    public void updateColumn(BoardColumn column) {
        try {
            boardColumnRepository.update(column);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating column", e);
        }
    }

    public void deleteColumn(int id) {
        try {
            boardColumnRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting column", e);
        }
    }
}