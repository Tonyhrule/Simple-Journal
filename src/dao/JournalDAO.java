package dao;

import model.JournalEntry;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    private Connection connect() {
        return util.Database.connect();
    }

    public void insertEntry(JournalEntry entry) {
        String sql = "INSERT INTO entries(title, date, content) VALUES(?,?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getDate().toString());
            pstmt.setString(3, entry.getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<JournalEntry> getAllEntries() {
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
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public void updateEntry(JournalEntry entry) {
        String sql = "UPDATE entries SET title = ?, date = ?, content = ? WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getDate().toString());
            pstmt.setString(3, entry.getContent());
            pstmt.setInt(4, entry.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEntry(int id) {
        String sql = "DELETE FROM entries WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
