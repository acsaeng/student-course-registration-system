package View.Frames;

import Controller.CatalogController;
import Model.Course;
import Model.CourseCatalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.catalog.Catalog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A view course catalog frame to view all the courses in the catalog.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 3rd, 2021
 */
public class ViewCatalogFrame extends JFrame {

    /**
     * Combo box to select a course name
     */
    private JComboBox<String> courseNameComboBox;


    private JTextArea catalogInfoArea;

    /**
     * Button to return to the main menu
     */
    private JButton mainMenuButton;

    /**
     * Constructor to initialize the contents of the view course catalog frame
     */
    public ViewCatalogFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - View Course Catalog");
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
        JPanel viewCatalogPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        this.add(viewCatalogPanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("View Course Catalog");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 20,0);
        viewCatalogPanel.add(frameHeader, gbc);

        // Insert the course name label
        JLabel courseNameLabel = new JLabel("Please select a department:");
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 5,0);
        viewCatalogPanel.add(courseNameLabel, gbc);

        // Insert the course name combo box
        this.courseNameComboBox = new JComboBox<>();
        this.courseNameComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 15,0);
        viewCatalogPanel.add(this.courseNameComboBox, gbc);

        // Insert the catalog information text area
        this.catalogInfoArea = new JTextArea(16, 25);
        this.catalogInfoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        this.catalogInfoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.catalogInfoArea.setEditable(false);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20,0);
        viewCatalogPanel.add(this.catalogInfoArea, gbc);

        // Insert the main menu button
        this.mainMenuButton = new JButton("Main Menu");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0,0);
        viewCatalogPanel.add(this.mainMenuButton, gbc);
    }

    /**
     * Enables visibility of the login frame
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

    public void reset(CourseCatalog catalog) {
        this.courseNameComboBox.removeAllItems();
        this.refreshCourseNameComboBox(catalog);
        this.catalogInfoArea.setText(null);
    }

    public void addActionListeners(CatalogController controller) {
        this.courseNameComboBox.addActionListener(controller);
        this.mainMenuButton.addActionListener(controller);
    }

    public void refreshCourseNameComboBox(CourseCatalog catalog) {
        ArrayList<String> courseNames = new ArrayList<>();

        // Add the course names to the combo box
        for (Course course : catalog.getCourseList()) {
            if (!courseNames.contains(course.getCourseName())) {
                courseNames.add(course.getCourseName());
                this.courseNameComboBox.addItem(course.getCourseName());
            }
        }
    }

    public JComboBox getCourseNameComboBox() {
        return courseNameComboBox;
    }

    public JTextArea getCatalogInfoArea() {
        return catalogInfoArea;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
