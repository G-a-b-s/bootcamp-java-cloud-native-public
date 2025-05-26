package br.com.dio.repository;

import br.com.dio.model.BoardColumn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardColumnRepository {
    private final Connection connection;

    public BoardColumnRepository(Connection connection) {
        this.connection = connection;
    }

    public List<BoardColumn> findAllByBoardId(int boardId) throws SQLException {
        List<BoardColumn> columns = new ArrayList<>();
        String sql = "SELECT id, board_id, name, position, type FROM BOARDS_COLUMNS WHERE board_id = ? ORDER BY position";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, boardId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    columns.add(mapResultSetToBoardColumn(rs));
                }
            }
        }
        return columns;
    }

    public BoardColumn findById(int id) throws SQLException {
        String sql = "SELECT id, board_id, name, position, type FROM BOARDS_COLUMNS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBoardColumn(rs);
                }
            }
        }
        return null;
    }

    public void save(BoardColumn column) throws SQLException {
        String sql = "INSERT INTO BOARDS_COLUMNS (board_id, name, position, type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, column.getBoardId());
            stmt.setString(2, column.getName());
            stmt.setInt(3, column.getPosition());
            stmt.setString(4, column.getType());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    column.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(BoardColumn column) throws SQLException {
        String sql = "UPDATE BOARDS_COLUMNS SET name = ?, position = ?, type = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, column.getName());
            stmt.setInt(2, column.getPosition());
            stmt.setString(3, column.getType());
            stmt.setInt(4, column.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM BOARDS_COLUMNS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private BoardColumn mapResultSetToBoardColumn(ResultSet rs) throws SQLException {
        BoardColumn column = new BoardColumn();
        column.setId(rs.getInt("id"));
        column.setBoardId(rs.getInt("board_id"));
        column.setName(rs.getString("name"));
        column.setPosition(rs.getInt("position"));
        column.setType(rs.getString("type"));
        return column;
    }
}