package LibraryManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {
    private ArrayList<Book> books = new ArrayList<>();
    private JTextArea bookArea;
    private JTextField titleField, authorField;
    private JButton addButton, issueButton, returnButton, viewButton;

    public App() {
        setTitle("Library Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230)); // Light Blue
        setLocationRelativeTo(null);

        // Title Label
        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Dark Blue
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Create a text area for displaying books
        bookArea = new JTextArea();
        bookArea.setEditable(false);
        bookArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        bookArea.setBackground(new Color(224, 255, 255)); // Light Cyan
        bookArea.setForeground(new Color(0, 51, 102)); // Darker Blue
        JScrollPane scrollPane = new JScrollPane(bookArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Create input panel for adding books
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(new Color(173, 216, 230)); // Light Blue
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel titleLbl = new JLabel("Title:");
        titleLbl.setForeground(new Color(0, 51, 102)); // Darker Blue
        inputPanel.add(titleLbl);
        titleField = new JTextField();
        titleField.setBackground(new Color(224, 255, 255)); // Light Cyan
        titleField.setForeground(new Color(0, 51, 102)); // Darker Blue
        inputPanel.add(titleField);

        JLabel authorLbl = new JLabel("Author:");
        authorLbl.setForeground(new Color(0, 51, 102)); // Darker Blue
        inputPanel.add(authorLbl);
        authorField = new JTextField();
        authorField.setBackground(new Color(224, 255, 255)); // Light Cyan
        authorField.setForeground(new Color(0, 51, 102)); // Darker Blue
        inputPanel.add(authorField);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(173, 216, 230)); // Light Blue
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton = createStyledButton("Add Book");
        issueButton = createStyledButton("Issue Book");
        returnButton = createStyledButton("Return Book");
        viewButton = createStyledButton("View Books");

        buttonPanel.add(addButton);
        buttonPanel.add(issueButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(viewButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 102, 204)); // Dark Blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102))); // Darker Blue Border
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add Book":
                addBook();
                break;
            case "Issue Book":
                issueBook();
                break;
            case "Return Book":
                returnBook();
                break;
            case "View Books":
                viewBooks();
                break;
        }
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        if (!title.isEmpty() && !author.isEmpty()) {
            books.add(new Book(title, author));
            titleField.setText("");
            authorField.setText("");
            JOptionPane.showMessageDialog(this, "Book added.");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both title and author.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void issueBook() {
        String title = JOptionPane.showInputDialog(this, "Enter the book title to issue:");
        if (title != null && !title.isEmpty()) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                    book.setIssued(true);
                    JOptionPane.showMessageDialog(this, "Book issued.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not available or already issued.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        String title = JOptionPane.showInputDialog(this, "Enter the book title to return:");
        if (title != null && !title.isEmpty()) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                    book.setIssued(false);
                    JOptionPane.showMessageDialog(this, "Book returned.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found or not issued.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            bookArea.setText("No books in the library.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Book book : books) {
                String status = book.isIssued() ? "Issued" : "Available";
                sb.append("Title: ").append(book.getTitle())
                        .append(", Author: ").append(book.getAuthor())
                        .append(", Status: ").append(status)
                        .append("\n");
            }
            bookArea.setText(sb.toString());
        }
    }

    // Inner class to represent a Book
    static class Book {
        private String title;
        private String author;
        private boolean issued;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.issued = false;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
