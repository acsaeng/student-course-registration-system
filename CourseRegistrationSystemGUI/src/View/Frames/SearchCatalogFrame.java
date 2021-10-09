package View.Frames;

import Controller.CatalogController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A search catalog frame to search for a course in the catalog.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class SearchCatalogFrame extends JFrame {

    /**
     * Combo box to select a course name
     */
    JTextField courseNameTextField;

    /**
     * Combo box to select a course number
     */
    JTextField courseNumTextField;

    JTextArea courseInfoArea;

    /**
     * Button to initiate the course search in the catalog
     */
    JButton searchButton;

    /**
     * Button to return to the main menu
     */
    JButton mainMenuButton;

    /**
     * Constructor to initialize the contents of the search catalog frame
     */
    public SearchCatalogFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - Search Catalog");
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
        JPanel searchCatalogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        this.add(searchCatalogPanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("Search Catalog");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 20,0);
        searchCatalogPanel.add(frameHeader, gbc);

        // Insert the course name label
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3,0);
        searchCatalogPanel.add(courseNameLabel, gbc);

        // Insert the course name combo box
        this.courseNameTextField = new JTextField(4);
        this.courseNameTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        searchCatalogPanel.add(this.courseNameTextField, gbc);

        // Insert the course number header
        JLabel courseNumLabel = new JLabel("Course Number:");
        courseNumLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3,0);
        searchCatalogPanel.add(courseNumLabel, gbc);

        // Insert the course number combo box
        this.courseNumTextField = new JTextField(3);
        this.courseNumTextField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        searchCatalogPanel.add(this.courseNumTextField, gbc);

        // Insert the search button
        this.searchButton = new JButton("Search");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20,0);
        searchCatalogPanel.add(this.searchButton, gbc);

        // Insert the course information text area
        this.courseInfoArea = new JTextArea(6, 25);
        courseInfoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        courseInfoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        courseInfoArea.setEditable(false);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 50,0);
        searchCatalogPanel.add(courseInfoArea, gbc);

        // Insert the main menu button
        this.mainMenuButton = new JButton("Main Menu");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0,0);
        searchCatalogPanel.add(this.mainMenuButton, gbc);
    }

    /**
     * Opens the search catalog frame
     */
    public void activate() {
        this.setVisible(true);
    }

    /**
     * Closes the search catalog frame
     */
    public void deactivate() {
        this.dispose();
    }

    /**
     * Resets the search catalog frame
     */
    public void reset() {
        this.courseNameTextField.setText("");
        this.courseNumTextField.setText("");
        this.courseInfoArea.setText("");
    }

    /**
     * Adds action listeners to the controller
     * @param controller catalog controller controlling the frame
     */
    public void addActionListeners(CatalogController controller) {
        this.searchButton.addActionListener(controller);
        this.mainMenuButton.addActionListener(controller);
    }

    /**
     * Displays a dialog box that communicates that the course could not be found
     */
    public void showNullCourseDialog() {
        String message = "Course could not be found.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates an invalid input in the course code
     */
    public void showInvalidInputErrorDialog() {
        String message = "Course name must contain 4 letters and course number must contain 3 numbers.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Getter that retrieves the course name text field
     * @return the course name text field
     */
    public JTextField getCourseNameTextField() {
        return this.courseNameTextField;
    }

    /**
     * Getter that retrieves the course number text field
     * @return the course number text field
     */
    public JTextField getCourseNumTextField() {
        return this.courseNumTextField;
    }

    /**
     * Getter that retrieves the search button
     * @return the search button
     */
    public JButton getSearchButton() {
        return this.searchButton;
    }

    /**
     * Getter that retrieves the course info text area
     * @return the course info text area
     */
    public JTextArea getCourseInfoArea() {
        return this.courseInfoArea;
    }

    /**
     * Getter that retrieves the main menu button
     * @return the main menu button
     */
    public JButton getMainMenuButton() {
        return this.mainMenuButton;
    }
}