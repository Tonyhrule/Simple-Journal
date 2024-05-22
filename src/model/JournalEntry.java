package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private LocalDateTime date;
    private String content;

    public JournalEntry(String title, String content) {
        this.title = title;
        this.date = LocalDateTime.now();
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title + " (" + date + ")";
    }
}
