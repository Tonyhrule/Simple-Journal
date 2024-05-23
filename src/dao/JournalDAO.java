package dao;

import model.JournalEntry;
import util.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JournalDAO {
    private static final Logger logger = Logger.getLogger(JournalDAO.class.getName());

    private Connection connect() throws SQLException {
        try {
            return Database.connect();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error", e);
            throw new SQLException("Failed to connect to the database", e);
        }
    }

    public void insertEntry(JournalEntry entry) throws SQLException {
        String sql = "INSERT INTO entries(title, date, content) VALUES(?,?,?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getDate().toString());
            pstmt.setString(3, entry.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting journal entry", e);
            throw new SQLException("Failed to insert journal entry", e);
        }
    }

    public List<JournalEntry> getAllEntries() throws SQLException {
        String sql = "SELECT id, title, date, content FROM entries";
        List<JournalEntry> entries = new ArrayList<>();

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JournalEntry entry = new JournalEntry(
                        rs.getString("title"),
                        rs.getString("content"));
                entry.setId(rs.getInt("id"));
                entry.setDate(LocalDateTime.parse(rs.getString("date")));
                entries.add(entry);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving journal entries", e);
            throw new SQLException("Failed to retrieve journal entries", e);
        }

        return entries;
    }

    public void updateEntry(JournalEntry entry) throws SQLException {
        String sql = "UPDATE entries SET title = ?, date = ?, content = ? WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getDate().toString());
            pstmt.setString(3, entry.getContent());
            pstmt.setInt(4, entry.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating journal entry", e);
            throw new SQLException("Failed to update journal entry", e);
        }
    }

    public void deleteEntry(int id) throws SQLException {
        String sql = "DELETE FROM entries WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting journal entry", e);
            throw new SQLException("Failed to delete journal entry", e);
        }
    }
}
