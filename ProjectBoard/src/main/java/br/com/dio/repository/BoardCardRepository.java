package br.com.dio.repository;

import br.com.dio.model.BoardCard;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardCardRepository {
    private final Connection connection;

    public BoardCardRepository(Connection connection) {
        this.connection = connection;
    }

    public List<BoardCard> findAllByColumnId(int columnId) throws SQLException {
        List<BoardCard> cards = new ArrayList<>();
        String sql = "SELECT id, column_id, title, description, created_at, is_blocked, block_reason, unblock_reason FROM BOARDS_CARDS WHERE column_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, columnId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapResultSetToBoardCard(rs));
                }
            }
        }
        return cards;
    }

    public BoardCard findById(int id) throws SQLException {
        String sql = "SELECT id, column_id, title, description, created_at, is_blocked, block_reason, unblock_reason FROM BOARDS_CARDS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToBoardCard(rs);
                }
            }
        }
        return null;
    }

    public void save(BoardCard card) throws SQLException {
        String sql = "INSERT INTO BOARDS_CARDS (column_id, title, description, created_at, is_blocked, block_reason, unblock_reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, card.getColumnId());
            stmt.setString(2, card.getTitle());
            stmt.setString(3, card.getDescription());
            stmt.setTimestamp(4, card.getCreatedAt() != null ? Timestamp.valueOf(card.getCreatedAt()) : Timestamp.valueOf(LocalDateTime.now()));
            stmt.setBoolean(5, card.isBlocked());
            stmt.setString(6, card.getBlockReason());
            stmt.setString(7, card.getUnblockReason());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(BoardCard card) throws SQLException {
        String sql = "UPDATE BOARDS_CARDS SET column_id = ?, title = ?, description = ?, is_blocked = ?, block_reason = ?, unblock_reason = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, card.getColumnId());
            stmt.setString(2, card.getTitle());
            stmt.setString(3, card.getDescription());
            stmt.setBoolean(4, card.isBlocked());
            stmt.setString(5, card.getBlockReason());
            stmt.setString(6, card.getUnblockReason());
            stmt.setInt(7, card.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM BOARDS_CARDS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private BoardCard mapResultSetToBoardCard(ResultSet rs) throws SQLException {
        BoardCard card = new BoardCard();
        card.setId(rs.getInt("id"));
        card.setColumnId(rs.getInt("column_id"));
        card.setTitle(rs.getString("title"));
        card.setDescription(rs.getString("description"));
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) card.setCreatedAt(createdAt.toLocalDateTime());
        card.setBlocked(rs.getBoolean("is_blocked"));
        card.setBlockReason(rs.getString("block_reason"));
        card.setUnblockReason(rs.getString("unblock_reason"));
        return card;
    }
}