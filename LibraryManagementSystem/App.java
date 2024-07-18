package LibraryManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class App extends JFrame implements ActionListener {
    private ArrayList<Book> books = new ArrayList<>();
    private JTextField titleField, authorField, genreField, quantityField, issuedByField, issueQuantityField, expectedReturnDateField, returnTitleField, deleteTitleField;
    private JLabel bookCountLabel;
    private JButton addButton, issueButton, returnButton, viewButton, deleteButton;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private static final String DATA_FILE = "books_data.dat";

    public App() {
        setTitle("Library Management System");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(255, 255, 255));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Book Table
        String[] columnNames = {"Title", "Author", "Genre", "Quantity", "Issued", "Issued By", "Issued Date", "Expected Return Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        // Book Count Label
        bookCountLabel = new JLabel("Total Books: 0");
        bookCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bookCountLabel.setHorizontalAlignment(JLabel.CENTER);
        add(bookCountLabel, BorderLayout.SOUTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(224, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Author:"), gbc);
        authorField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Genre:"), gbc);
        genreField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(genreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Quantity:"), gbc);
        quantityField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Issued By:"), gbc);
        issuedByField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(issuedByField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Issue Quantity:"), gbc);
        issueQuantityField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(issueQuantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Expected Return Date (dd-MM-yyyy):"), gbc);
        expectedReturnDateField = new JTextField(20); // Adjusted size
        gbc.gridx = 1;
        gbc.gridy = 6;
        inputPanel.add(expectedReturnDateField, gbc);

        addButton = new JButton("Add Book");
        addButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        inputPanel.add(addButton, gbc);

        issueButton = new JButton("Issue Book");
        issueButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        inputPanel.add(issueButton, gbc);

        returnButton = new JButton("Return Book");
        returnButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        inputPanel.add(returnButton, gbc);

        viewButton = new JButton("View Books");
        viewButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        inputPanel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        inputPanel.add(new JLabel("Delete Book Title:"), gbc);
        deleteTitleField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 11;
        inputPanel.add(deleteTitleField, gbc);

        deleteButton = new JButton("Delete Book");
        deleteButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        inputPanel.add(deleteButton, gbc);

        add(inputPanel, BorderLayout.EAST);

        loadData();
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addBook();
        } else if (e.getSource() == issueButton) {
            issueBook();
        } else if (e.getSource() == returnButton) {
            returnBook();
        } else if (e.getSource() == viewButton) {
            viewBooks();
        } else if (e.getSource() == deleteButton) {
            deleteBook();
        }
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String quantityStr = quantityField.getText();

        if (!title.isEmpty() && !quantityStr.isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityStr);
                books.add(new Book(title, author, genre, quantity));
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                quantityField.setText("");
                updateBookTable();
                saveData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Title and Quantity cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void issueBook() {
        String title = titleField.getText();
        String issuedBy = issuedByField.getText();
        String expectedReturnDate = expectedReturnDateField.getText();
        String issueQuantityStr = issueQuantityField.getText();

        if (!title.isEmpty() && !issueQuantityStr.isEmpty()) {
            try {
                int issueQuantity = Integer.parseInt(issueQuantityStr);
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() >= issueQuantity) {
                        book.setIssued(true);
                        book.setIssuedBy(issuedBy);
                        book.setIssuedDate(new Date());
                        book.setExpectedReturnDate(expectedReturnDate);
                        book.setQuantity(book.getQuantity() - issueQuantity);
                        titleField.setText("");
                        issuedByField.setText("");
                        issueQuantityField.setText("");
                        expectedReturnDateField.setText("");
                        updateBookTable();
                        saveData();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Book not found or insufficient quantity", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Issue Quantity must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Title and Issue Quantity cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        String title = titleField.getText();
        String returnQuantityStr = issueQuantityField.getText();

        if (!title.isEmpty() && !returnQuantityStr.isEmpty()) {
            try {
                int returnQuantity = Integer.parseInt(returnQuantityStr);
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                        book.setIssued(false);
                        book.setIssuedBy("");
                        book.setIssuedDate(null);
                        book.setExpectedReturnDate("");
                        book.setQuantity(book.getQuantity() + returnQuantity);
                        titleField.setText("");
                        issueQuantityField.setText("");
                        updateBookTable();
                        saveData();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Book not found or not issued", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Return Quantity must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Title and Return Quantity cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        String title = deleteTitleField.getText();

        if (!title.isEmpty()) {
            books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
            deleteTitleField.setText("");
            updateBookTable();
            saveData();
        } else {
            JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewBooks() {
        updateBookTable();
    }

    private void updateBookTable() {
        tableModel.setRowCount(0);
        for (Book book : books) {
            String issuedDate = book.getIssuedDate() != null ? new SimpleDateFormat("dd-MM-yyyy").format(book.getIssuedDate()) : "";
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getGenre(), book.getQuantity(), book.isIssued(), book.getIssuedBy(), issuedDate, book.getExpectedReturnDate()});
        }
        bookCountLabel.setText("Total Books: " + books.size());
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            books = (ArrayList<Book>) ois.readObject();
            updateBookTable();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty library.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Book implements Serializable {
        private String title;
        private String author;
        private String genre;
        private int quantity;
        private boolean issued;
        private String issuedBy;
        private Date issuedDate;
        private String expectedReturnDate;

        public Book(String title, String author, String genre, int quantity) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.quantity = quantity;
            this.issued = false;
            this.issuedBy = "";
            this.issuedDate = null;
            this.expectedReturnDate = "";
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getGenre() {
            return genre;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }

        public String getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
        }

        public Date getIssuedDate() {
            return issuedDate;
        }

        public void setIssuedDate(Date issuedDate) {
            this.issuedDate = issuedDate;
        }

        public String getExpectedReturnDate() {
            return expectedReturnDate;
        }

        public void setExpectedReturnDate(String expectedReturnDate) {
            this.expectedReturnDate = expectedReturnDate;
        }
    }
}