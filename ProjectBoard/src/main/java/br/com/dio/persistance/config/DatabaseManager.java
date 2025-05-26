package br.com.dio.persistance.config;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseManager {

    private static final String DB_PATH = "/Users/gabrielrocha/development/Board/identifier.sqlite";
    @Getter
    private static Connection connection;

    private DatabaseManager() {
        // Prevent instantiation
    }

    public static void init() throws SQLException {
        if (connection == null) {
            String url = "jdbc:sqlite:" + DB_PATH;
            connection = DriverManager.getConnection(url);
        }
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}