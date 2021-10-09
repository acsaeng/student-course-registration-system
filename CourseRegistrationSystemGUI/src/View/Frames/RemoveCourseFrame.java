package View.Frames;

import Controller.StudentController;
import Model.Registration;
import Model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A remove course frame to remove a student course.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class RemoveCourseFrame extends JFrame {

    /**
     * Combo box to select a registered course
     */
    JComboBox<String> courseComboBox;

    /**
     * Button to remove a selected course
     */
    JButton removeCourseButton;

    /**
     * Button to return to the main menu
     */
    JButton mainMenuButton;

    /**
     * Constructor to initialize the contents of the remove course frame
     */
    public RemoveCourseFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - Remove Course");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Insert top program header
        JLabel titleHeader = new JLabel("Course Registration System");
        titleHeader.setFont(new Font("Arial",Font.BOLD, 24));
        titleHeader.setHorizontalAlignment(JLabel.CENTER);
        titleHeader.setVerticalAlignment(JLabel.CENTER);
        titleHeader.setBorder(new EmptyBorder(25,0,0,0));
        this.add(titleHeader, BorderLayout.NORTH);

        // Format contents panel
        JPanel addCoursePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        this.add(addCoursePanel, BorderLayout.CENTER);

        // Insert frame header
        JLabel frameHeader = new JLabel("Remove Course");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 50,0);
        addCoursePanel.add(frameHeader, gbc);

        // Insert the course label
        JLabel courseLabel = new JLabel("Please select a course to remove:");
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        addCoursePanel.add(courseLabel, gbc);

        // Insert the course combo box
        this.courseComboBox = new JComboBox<>();
        this.courseComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 25,0);
        addCoursePanel.add(this.courseComboBox, gbc);

        // Insert the remove course button
        this.removeCourseButton = new JButton("Remove Course");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 100,0);
        addCoursePanel.add(this.removeCourseButton, gbc);

        // Insert the main menu button
        this.mainMenuButton = new JButton("Main Menu");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0,0);
        addCoursePanel.add(this.mainMenuButton, gbc);
    }

    /**
     * Opens the remove course frame
     */
    public void activate() {
        this.setVisible(true);
    }

    /**
     * Closes the remove course
     */
    public void deactivate() {
        this.dispose();
    }

    public void reset(Student student) {
        this.courseComboBox.removeAllItems();
        this.refreshCourseComboBox(student);
    }

    /**
     * Adds action listeners to the controller
     * @param controller student controller controlling the frame
     */
    public void addActionListeners(StudentController controller) {
        this.removeCourseButton.addActionListener(controller);
        this.mainMenuButton.addActionListener(controller);

    }

    /**
     * Refreshes the course combo box
     * @param student the student containing the registered courses
     */
    public void refreshCourseComboBox(Student student) {
        if (student.getRegisteredCourses().size() == 0) {
            // Show dialog if student is not registered in any courses
            this.showNoCoursesErrorDialog();
            this.getCourseComboBox().setEnabled(false);
            this.getRemoveCourseButton().setEnabled(false);
        } else {
            // Add the course names to the combo box
            for (Registration reg : student.getRegisteredCourses()) {
                this.courseComboBox.addItem(reg.getCourseSection().getCourse() + " - Section " + reg.getCourseSection().getSectionNum());
            }

            this.getRemoveCourseButton().setEnabled(true);
        }
    }

    /**
     * Displays a dialog box that communicates that the user is not registered in any courses
     */
    public void showNoCoursesErrorDialog() {
        String message = "No courses to remove.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Displays a dialog box that communicates that a course has been successfully removed
     */
    public void showSuccessfulRegistrationRemovalDialog() {
        String message = "Successfully removed!";
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    /**
     * Getter that retrieves the course combo box
     * @return the course combo box
     */
    public JComboBox<String> getCourseComboBox() {
        return courseComboBox;
    }

    /**
     * Getter that retrieves the remove course button
     * @return the remove course button
     */
    public JButton getRemoveCourseButton() {
        return removeCourseButton;
    }

    /**
     * Getter that retrieves the main menu button
     * @return the main menu button
     */
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
