import java.awt.*;

import javax.swing.*;


package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuestFormView extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField numberOfGuestsField = new JTextField(5);
    private JButton submitButton = new JButton("Submit");

    public GuestFormView() {
        // Set up the frame
        setTitle("Guest Form");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the form components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Number of Guests:"));
        panel.add(numberOfGuestsField);
        panel.add(submitButton);

        // Add the panel to the frame
        add(panel);
    }

    public String getName() {
        return nameField.getText();
    }

    public int getNumberOfGuests() {
        try {
            return Integer.parseInt(numberOfGuestsField.getText());
        } catch (NumberFormatException e) {
            return 0; // or some other default value or handling
        }
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}