package View.Frames;

import Controller.InterfaceController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A main menu frame to select an option in the registration system.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class MainMenuFrame extends JFrame {

    /**
     * Button to open the search catalog window
     */
    JButton searchCatalogButton;

    /**
     * Button to open the add course window
     */
    JButton addCourseButton;

    /**
     * Button to open the remove course window
     */
    JButton removeCourseButton;

    /**
     * Button to open the view catalog window
     */
    JButton viewCatalogButton;

    /**
     * Button to open the view registered courses window
     */
    JButton viewCoursesButton;

    /**
     * Button to quit the program
     */
    JButton quitButton;

    /**
     * Constructor to initialize the contents of the main menu frame
     */
    public MainMenuFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - Main Menu");
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
        JPanel mainMenuPanel = new JPanel(new GridLayout(7, 1, 0,5));
        mainMenuPanel.setBorder(new EmptyBorder(50, 100, 100, 100));
        this.add(mainMenuPanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("Main Menu");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        frameHeader.setHorizontalAlignment(JLabel.CENTER);
        frameHeader.setVerticalAlignment(JLabel.CENTER);
        frameHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainMenuPanel.add(frameHeader);

        // Insert search catalog button
        this.searchCatalogButton = new JButton("Search Catalog Courses");
        mainMenuPanel.add(this.searchCatalogButton);

        // Insert add courses button
        this.addCourseButton = new JButton("Add Course to Student Courses");
        mainMenuPanel.add(this.addCourseButton);

        // Insert remove courses button
        this.removeCourseButton = new JButton("Remove Course to Student Courses");
        mainMenuPanel.add(this.removeCourseButton);

        // Insert view catalog button
        this.viewCatalogButton = new JButton("View All Courses in the Catalog");
        mainMenuPanel.add(this.viewCatalogButton);

        // Insert view registered courses button
        this.viewCoursesButton = new JButton("View Registered Student Courses");
        mainMenuPanel.add(this.viewCoursesButton);

        // Insert quit button
        this.quitButton = new JButton("Exit");
        mainMenuPanel.add(this.quitButton);
    }

    /**
     * Opens the main menu frame
     * Opens the main menu frame
     */
    public void activate() {
        this.setVisible(true);

    }

    /**
     * Closes the main menu frame
     */
    public void deactivate() {
        this.dispose();
    }

    public void addActionListeners(InterfaceController controller) {
        this.searchCatalogButton.addActionListener(controller);
        this.addCourseButton.addActionListener(controller);
        this.removeCourseButton.addActionListener(controller);
        this.viewCatalogButton.addActionListener(controller);
        this.viewCoursesButton.addActionListener(controller);
        this.quitButton.addActionListener(controller);
    }

    /**
     * Getter that retrieves the search catalog button
     * @return the search catalog button
     */
    public JButton getSearchCatalogButton() {
        return searchCatalogButton;
    }

    /**
     * Getter that retrieves the add course button
     * @return the add catalog button
     */
    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    /**
     * Getter that retrieves the remove course button
     * @return the remove catalog button
     */
    public JButton getRemoveCourseButton() {
        return removeCourseButton;
    }

    /**
     * Getter that retrieves the view course catalog button
     * @return the view course catalog button
     */
    public JButton getViewCatalogButton() {
        return viewCatalogButton;
    }

    /**
     * Getter that retrieves the view registered courses button
     * @return the view registered courses button
     */
    public JButton getViewCoursesButton() {
        return viewCoursesButton;
    }

    /**
     * Getter that retrieves the quit button
     * @return the quit button
     */
    public JButton getQuitButton() {
        return quitButton;
    }
}