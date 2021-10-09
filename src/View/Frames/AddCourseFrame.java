package View.Frames;

import Controller.StudentController;
import Model.Course;
import Model.CourseCatalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * An add course frame to register for a student course.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class AddCourseFrame extends JFrame {

    /**
     * Combo box to select a course name
     */
    JComboBox<String> courseNameComboBox;

    /**
     * Combo box to select a course number
     */
    JComboBox<Integer> courseNumComboBox;

    /**
     * Combo box to select a course section
     */
    JComboBox<String> courseSectionComboBox;

    /**
     * Button to add a selected course
     */
    JButton addCourseButton;

    /**
     * Button to return to the main menu
     */
    JButton mainMenuButton;

    /**
     * Constructor to initialize the contents of the add course frame
     */
    public AddCourseFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - Add Course");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Insert top program header
        JLabel titleHeader = new JLabel("Course Registration System");
        titleHeader.setFont(new Font("Arial",Font.BOLD, 24));
        titleHeader.setHorizontalAlignment(JLabel.CENTER);
        titleHeader.setVerticalAlignment(JLabel.CENTER);
        titleHeader.setBorder(new EmptyBorder(25,0,25,0));
        this.add(titleHeader, BorderLayout.NORTH);

        // Format contents panel
        JPanel addCoursePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        this.add(addCoursePanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("Add Course");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 20,0);
        addCoursePanel.add(frameHeader, gbc);

        // Insert the course name label
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3,0);
        addCoursePanel.add(courseNameLabel, gbc);

        // Insert the course name combo box
        this.courseNameComboBox = new JComboBox<>();
        this.courseNameComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        addCoursePanel.add(this.courseNameComboBox, gbc);

        // Insert the course number label
        JLabel courseNumLabel = new JLabel("Course Number:");
        courseNumLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3,0);
        addCoursePanel.add(courseNumLabel, gbc);

        // Insert the course number combo box
        this.courseNumComboBox = new JComboBox<>();
        this.courseNumComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        addCoursePanel.add(this.courseNumComboBox, gbc);

        // Insert the course section label
        JLabel courseSectionLabel = new JLabel("Section:");
        courseSectionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 3,0);
        addCoursePanel.add(courseSectionLabel, gbc);

        // Insert the course section combo box
        this.courseSectionComboBox = new JComboBox<>();
        this.courseSectionComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 25,0);
        addCoursePanel.add(this.courseSectionComboBox, gbc);

        // Insert the add course button
        this.addCourseButton = new JButton("Add Course");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 75,0);
        addCoursePanel.add(this.addCourseButton, gbc);

        // Insert the main menu button
        this.mainMenuButton = new JButton("Main Menu");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0,0);
        addCoursePanel.add(this.mainMenuButton, gbc);
    }

    /**
     * Opens the add course frame
     */
    public void activate() {
        this.setVisible(true);
    }

    /**
     * Closes the add course frame
     */
    public void deactivate() {
        this.dispose();
    }

    /**
     * Resets the add course frame
     */
    public void reset(CourseCatalog catalog) {
        this.courseNameComboBox.removeAllItems();
        this.courseNumComboBox.removeAllItems();
        this.courseSectionComboBox.removeAllItems();

        ArrayList<String> courseNames = new ArrayList<>();

        // Add the course names to the combo box
        for (Course course : catalog.getCourseList()) {
            if (!courseNames.contains(course.getCourseName())) {
                courseNames.add(course.getCourseName());
                this.courseNameComboBox.addItem(course.getCourseName());
            }
        }
    }

    /**
     * Adds action listeners to the controller
     * @param controller student controller controlling the frame
     */
    public void addActionListeners(StudentController controller) {
        this.courseNameComboBox.addActionListener(controller);
        this.courseNumComboBox.addActionListener(controller);
        this.addCourseButton.addActionListener(controller);
        this.mainMenuButton.addActionListener(controller);
    }

    /**
     * Refreshes the course number combo box
     * @param catalog a course catalog containing all the offered courses
     */
    public void refreshCourseNumComboBox(CourseCatalog catalog) {
        this.courseNumComboBox.removeAllItems();

        // Update the course number combo box from the catalog
        for (Course course : catalog.getCourseList()) {
            if (course.getCourseName().equals(this.courseNameComboBox.getSelectedItem())) {
                this.courseNumComboBox.addItem(course.getCourseNum());
            }
        }
    }

    /**
     * Refreshes the course section combo box
     * @param catalog a course catalog containing all the offered courses
     */
    public void refreshCourseSectionComboBox(CourseCatalog catalog) {
        this.courseSectionComboBox.removeAllItems();

        // Update the course section combo box from the catalog
        for (Course course: catalog.getCourseList()) {
            if (course.toString().equals(this.courseNameComboBox.getSelectedItem() + " " + this.courseNumComboBox.getSelectedItem())) {
                for (int i = 0; i < course.getSections().size(); i++) {
                    this.courseSectionComboBox.addItem("Section " + (i + 1) + " (Enrollment total: " +
                            course.getSections().get(i).getStudentList().size() + "/" + course.getSections().get(i).getSectionCap() + ")");
                }
            }
        }
    }

    /**
     * Displays a dialog box that communicates a successful registration
     */
    public void showSuccessfulRegistrationDialog() {
        String message = "Successfully registered!";
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    /**
     * Displays a dialog box that communicates a possible course cancellation
     */
    public void showCourseCancellationWarningDialog() {
        String message = "Courses with less than 8 students may be subject to cancellation.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a user has already completed a course
     */
    public void showCompletedCourseErrorDialog() {
        String message = "You have already completed this course.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a user has not met the prerequisite requirements
     */
    public void showPrereqErrorDialog() {
        String message = "You have not completed the prerequisites to enroll in this course.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a user is already registered in a course
     */
    public void showExistingRegistrationErrorDialog() {
        String message = "You are already enrolled in this course.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a course section is full
     */
    public void showFullCourseErrorDialog() {
        String message = "This course section is full.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a user is enrolled in the maximum number of courses
     */
    public void showMaxEnrollmentErrorDialog() {
        String message = "You are already enrolled in the maximum number of courses.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Getter that retrieves the course name combo box
     * @return the course name combo box
     */
    public JComboBox getCourseNameComboBox() {
        return courseNameComboBox;
    }

    /**
     * Getter that retrieves the course number combo box
     * @return the course number combo box
     */
    public JComboBox getCourseNumComboBox() {
        return courseNumComboBox;
    }

    /**
     * Getter that retrieves the course section combo box
     * @return the course section combo box
     */
    public JComboBox getCourseSectionComboBox() {
        return courseSectionComboBox;
    }

    /**
     * Getter that retrieves the add course button
     * @return the add course button
     */
    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    /**
     * Getter that retrieves the main menu button
     * @return the main menu button
     */
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}