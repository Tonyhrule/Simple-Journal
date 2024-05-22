package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import dao.JournalDAO;
import model.JournalEntry;
import util.Database;

public class JournalApp {
    private JFrame frame;
    private JTextField titleField;
    private JTextArea entryArea;
    private JTextField searchField;
    private JButton newButton, saveButton, editButton, deleteButton, viewButton, searchButton, backButton;
    private JList<String> entryList;
    private DefaultListModel<String> listModel;
    private JournalDAO journalDAO;
    private List<JournalEntry> entries;

    public JournalApp(String mode) {
        journalDAO = new JournalDAO();
        entries = journalDAO.getAllEntries();

        frame = new JFrame("Journal App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        titleField = new JTextField(20);
        entryArea = new JTextArea(15, 40);
        searchField = new JTextField(20);
        newButton = new JButton("New");
        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");
        searchButton = new JButton("Search");
        backButton = new JButton("Back to Home");
        listModel = new DefaultListModel<>();
        entryList = new JList<>(listModel);
        updateEntryList(entries);

        styleComponents();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel entryListPanel = new JPanel(new BorderLayout());
        entryListPanel.add(new JScrollPane(entryList), BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(entryListPanel, BorderLayout.CENTER);

        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BorderLayout());
        entryPanel.add(new JLabel("Title:"), BorderLayout.NORTH);
        entryPanel.add(titleField, BorderLayout.NORTH);
        entryPanel.add(new JScrollPane(entryArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        frame.add(topPanel, BorderLayout.EAST);
        frame.add(entryPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        if (mode.equals("new")) {
            titleField.setText("");
            entryArea.setText("");
        } else if (mode.equals("view")) {
            viewButton.doClick();
        }

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleField.setText("");
                entryArea.setText("");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = entryArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    JournalEntry entry = new JournalEntry(title, content);
                    journalDAO.insertEntry(entry);
                    entries = journalDAO.getAllEntries();
                    updateEntryList(entries);
                    titleField.setText("");
                    entryArea.setText("");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = entryList.getSelectedIndex();
                if (selectedIndex != -1) {
                    JournalEntry entry = entries.get(selectedIndex);
                    entry.setTitle(titleField.getText());
                    entry.setContent(entryArea.getText());
                    journalDAO.updateEntry(entry);
                    entries = journalDAO.getAllEntries();
                    updateEntryList(entries);
                    titleField.setText("");
                    entryArea.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = entryList.getSelectedIndex();
                if (selectedIndex != -1) {
                    JournalEntry entry = entries.get(selectedIndex);
                    journalDAO.deleteEntry(entry.getId());
                    entries = journalDAO.getAllEntries();
                    updateEntryList(entries);
                    titleField.setText("");
                    entryArea.setText("");
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = entryList.getSelectedIndex();
                if (selectedIndex != -1) {
                    JournalEntry entry = entries.get(selectedIndex);
                    titleField.setText(entry.getTitle());
                    entryArea.setText(entry.getContent());
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                if (!query.isEmpty()) {
                    List<JournalEntry> filteredEntries = entries.stream()
                            .filter(entry -> entry.getContent().contains(query) || entry.getTitle().contains(query))
                            .collect(Collectors.toList());
                    updateEntryList(filteredEntries);
                } else {
                    updateEntryList(entries);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomePage();
            }
        });

        frame.setVisible(true);
    }

    private void styleComponents() {
        Font font = new Font("Arial", Font.PLAIN, 18);
        Color buttonColor = new Color(70, 130, 180);
        Color buttonTextColor = Color.WHITE;

        titleField.setFont(font);
        entryArea.setFont(font);
        searchField.setFont(font);

        JButton[] buttons = { newButton, saveButton, editButton, deleteButton, viewButton, searchButton, backButton };

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setFocusPainted(false);
        }
    }

    private void updateEntryList(List<JournalEntry> entries) {
        listModel.clear();
        for (JournalEntry entry : entries) {
            listModel.addElement(entry.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("JournalApp main method started");
        Database.createNewTable(); // Ensure the table is created
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
