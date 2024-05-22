package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        frame = new JFrame("Welcome to Simple Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Simple Journal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea(
                "Simple Journal is an intuitive and user-friendly application that allows you to keep track of your daily thoughts and experiences. With features like creating new journal entries, editing, deleting, and searching through your journals, you can easily manage and reflect on your personal journey. Start capturing your moments with Simple Journal!");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        frame.add(descriptionArea, BorderLayout.CENTER);

        JButton emanateButton = new JButton("Emanate");
        emanateButton.setFont(new Font("Arial", Font.BOLD, 20));
        emanateButton.setPreferredSize(new Dimension(200, 50));
        emanateButton.setBackground(new Color(70, 130, 180));
        emanateButton.setForeground(Color.WHITE);
        emanateButton.setFocusPainted(false);
        emanateButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emanateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new JournalApp("new");
            }
        });

        JLabel instructionLabel = new JLabel("Press the button to get started", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        instructionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(emanateButton, BorderLayout.CENTER);
        buttonPanel.add(instructionLabel, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
