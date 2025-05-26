package br.com.dio.service;

import br.com.dio.model.Board;
import br.com.dio.repository.BoardRepository;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> listBoards() {
        try {
            return boardRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error listing boards", e);
        }
    }

    public Board getBoardById(int id) {
        try {
            return boardRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding board by id", e);
        }
    }

    public void createBoard(Board board) {
        try {
            boardRepository.save(board);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating board", e);
        }
    }

    public void updateBoard(Board board) {
        try {
            boardRepository.update(board);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating board", e);
        }
    }

    public void deleteBoard(int id) {
        try {
            boardRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting board", e);
        }
    }
}