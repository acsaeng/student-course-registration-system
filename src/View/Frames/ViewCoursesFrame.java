package View.Frames;

import Controller.StudentController;
import Model.Registration;
import Model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A view registered courses frame to view the student's courses.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 3rd, 2021
 */
public class ViewCoursesFrame extends JFrame {

    /**
     * Text area to display the registered course information
     */
    JTextArea registeredCoursesInfoArea;

    /**
     * Button to return to the main menu
     */
    JButton mainMenuButton;

    /**
     * Constructor to initialize the contents of the view registered courses frame
     */
    public ViewCoursesFrame() {
        // Set properties of frame
        this.setTitle("Course Registration System - View Registered Courses");
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
        JLabel frameHeader = new JLabel("View Registered Courses");
        frameHeader.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.insets = new Insets(0, 0, 25,0);
        viewCatalogPanel.add(frameHeader, gbc);

        // Insert the registered courses label
        JLabel registeredCoursesLabel = new JLabel("Current registered courses:");
        registeredCoursesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10,0);
        viewCatalogPanel.add(registeredCoursesLabel, gbc);

        // Insert the registered courses text area
        registeredCoursesInfoArea = new JTextArea(10, 25);
        registeredCoursesInfoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        registeredCoursesInfoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        registeredCoursesInfoArea.setEditable(false);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 50,0);
        viewCatalogPanel.add(registeredCoursesInfoArea, gbc);

        // Insert the main menu button
        this.mainMenuButton = new JButton("Main Menu");
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0,0);
        viewCatalogPanel.add(this.mainMenuButton, gbc);
    }

    /**
     * Opens the view registered courses frame
     */
    public void activate() {
        this.setVisible(true);
    }

    /**
     * Closes the view registered courses frame
     */
    public void deactivate() {
        this.dispose();
    }

    /**
     * Resets the view registered courses frame
     */
    public void reset(Student student) {
        this.displayRegisteredCourses(student);
    }

    /**
     * Adds action listeners to the controller
     * @param controller student controller controlling the frame
     */
    public void addActionListeners(StudentController controller) {
        this.mainMenuButton.addActionListener(controller);
    }

    /**
     * Displays the registered course in the text area
     * @param student
     */
    public void displayRegisteredCourses(Student student) {
        // Print all the courses the student is currently registered in
        if (student.getRegisteredCourses().size() == 0) {
            this.registeredCoursesInfoArea.setText(" No registered courses");
        } else {
            StringBuilder output = new StringBuilder();

            for (Registration reg: student.getRegisteredCourses()) {
                output.append(" ").append(reg.getCourseSection().getCourse()).append(" (Section ").append(reg.getCourseSection().getSectionNum()).append(")").append("\n");
            }

            this.registeredCoursesInfoArea.setText(output.toString());
        }
    }

    /**
     * Getter that retrieves the main menu button
     * @return the main menu button
     */
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
