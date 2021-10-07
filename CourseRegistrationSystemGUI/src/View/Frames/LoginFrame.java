package View.Frames;

import Controller.InterfaceController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A login frame to register students into the system.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class LoginFrame extends JFrame {
    /**
     * A text field for the student's name
     */
    private JTextField nameField;

    /**
     * A text field for the student's ID
     */
    private JTextField studentIdField;

    /**
     * A button to initiate student login
     */
    private JButton loginButton;

    /**
     * Constructor to initialize the contents of the login frame
     */
    public LoginFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - Student Login");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Insert top program header
        JLabel titleHeader = new JLabel("Course Registration System");
        titleHeader.setFont(new Font("Arial", Font.BOLD, 24));
        titleHeader.setHorizontalAlignment(JLabel.CENTER);
        titleHeader.setVerticalAlignment(JLabel.CENTER);
        titleHeader.setBorder(new EmptyBorder(25,0,0,0));
        this.add(titleHeader, BorderLayout.NORTH);

        // Format contents panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        loginPanel.setBorder(new EmptyBorder(100, 0, 150, 0));
        this.add(loginPanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("Student Login");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 20,0);
        loginPanel.add(frameHeader, gbc);

        // Insert name text field
        this.nameField = new JTextField("Name", 20);
        this.nameField.setMinimumSize(new Dimension(20, 10));
        this.nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 2,0);
        loginPanel.add(nameField, gbc);

        // Insert student ID text field
        this.studentIdField = new JTextField("Student ID", 20);
        this.studentIdField.setFont(new Font("Arial", Font.PLAIN, 12));
        loginPanel.add(studentIdField);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20,0);
        loginPanel.add(studentIdField, gbc);

        // Insert login button
        this.loginButton = new JButton("Log In");
//        this.loginButton.addActionListener(this);
        gbc.gridy++;
        loginPanel.add(loginButton, gbc);
    }

    /**
     * Enables visibility of the login frame
     */
    public void activate() {
        this.setVisible(true);

    }

    /**
     * Closes the login frame
     */
    public void deactivate() {
        this.dispose();
    }

    public void addActionListeners(InterfaceController controller) {
        this.loginButton.addActionListener(controller);
    }

    public void showInvalidInputErrorDialog() {
        String message = "Student ID must only contain numbers.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }








    public String getName() {
        return this.nameField.getText();
    }

    public String getStudentId() {
        return this.studentIdField.getText();
    }

    public JButton getLoginButton() {
        return this.loginButton;
    }

}