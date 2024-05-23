package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:journal.db";

    public static Connection connect() throws SQLException {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database", e);
        }
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS entries (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " title text NOT NULL,\n"
                + " date text NOT NULL,\n"
                + " content text NOT NULL\n"
                + ");";

        try (Connection conn = connect();
                java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
