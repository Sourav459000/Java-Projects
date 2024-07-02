package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton squareButton, sqrtButton, expButton;
    private JTextField num1Field, num2Field, resultField;

    public App() {
        setTitle("Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel num1Label = new JLabel("Number 1: ");
        JLabel num2Label = new JLabel("Number 2: ");
        JLabel resultLabel = new JLabel("Result: ");
        num1Field = new JTextField(10);
        num2Field = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        squareButton = new JButton("x^2");
        sqrtButton = new JButton("√x");
        expButton = new JButton("x^y");

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        squareButton.addActionListener(this);
        sqrtButton.addActionListener(this);
        expButton.addActionListener(this);

        addButton.setBackground(Color.CYAN);
        subtractButton.setBackground(Color.PINK);
        multiplyButton.setBackground(Color.YELLOW);
        divideButton.setBackground(Color.LIGHT_GRAY);
        squareButton.setBackground(Color.GREEN);
        sqrtButton.setBackground(Color.ORANGE);
        expButton.setBackground(Color.MAGENTA);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        contentPane.add(num1Label, gbc);
        gbc.gridx = 1;
        contentPane.add(num1Field, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        contentPane.add(num2Label, gbc);
        gbc.gridx = 1;
        contentPane.add(num2Field, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        contentPane.add(resultLabel, gbc);
        gbc.gridx = 1;
        contentPane.add(resultField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        contentPane.add(addButton, gbc);
        gbc.gridx = 1;
        contentPane.add(subtractButton, gbc);
        gbc.gridx = 2;
        contentPane.add(multiplyButton, gbc);
        gbc.gridx = 3;
        contentPane.add(divideButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        contentPane.add(squareButton, gbc);
        gbc.gridx = 1;
        contentPane.add(sqrtButton, gbc);
        gbc.gridx = 2;
        contentPane.add(expButton, gbc);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = num2Field.getText().isEmpty() ? 0 : Double.parseDouble(num2Field.getText());

            if (e.getSource() == addButton) {
                resultField.setText(Double.toString(num1 + num2));
            } else if (e.getSource() == subtractButton) {
                resultField.setText(Double.toString(num1 - num2));
            } else if (e.getSource() == multiplyButton) {
                resultField.setText(Double.toString(num1 * num2));
            } else if (e.getSource() == divideButton) {
                resultField.setText(Double.toString(num1 / num2));
            } else if (e.getSource() == squareButton) {
                resultField.setText(Double.toString(num1 * num1));
            } else if (e.getSource() == sqrtButton) {
                resultField.setText(Double.toString(Math.sqrt(num1)));
            } else if (e.getSource() == expButton) {
                resultField.setText(Double.toString(Math.pow(num1, num2)));
            }
        } catch (NumberFormatException ex) {
            resultField.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        new App();
    }
}