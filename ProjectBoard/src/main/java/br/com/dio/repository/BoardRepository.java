package br.com.dio.repository;

import br.com.dio.model.Board;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
    private final Connection connection;

    public BoardRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Board> findAll() throws SQLException {
        List<Board> boards = new ArrayList<>();
        String sql = "SELECT id, name, created_at, updated_at FROM BOARDS";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                boards.add(mapResultSetToBoard(rs));
            }
        }
        return boards;
    }

    public Board findById(int id) throws SQLException {
        String sql = "SELECT id, name, created_at, updated_at FROM BOARDS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBoard(rs);
                }
            }
        }
        return null;
    }

    public void save(Board board) throws SQLException {
        String sql = "INSERT INTO BOARDS (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, board.getName());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    board.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Board board) throws SQLException {
        String sql = "UPDATE BOARDS SET name = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, board.getName());
            stmt.setInt(2, board.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM BOARDS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Board mapResultSetToBoard(ResultSet rs) throws SQLException {
        Board board = new Board();
        board.setId(rs.getInt("id"));
        board.setName(rs.getString("name"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (createdAt != null) board.setCreatedAt(createdAt.toLocalDateTime());
        if (updatedAt != null) board.setUpdatedAt(updatedAt.toLocalDateTime());
        return board;
    }
}