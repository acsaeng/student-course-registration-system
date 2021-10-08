package Controller;

import Model.Course;
import Model.Student;
import View.InterfaceView;
import View.StudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller that regulates communication between the student model and student view.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class StudentController implements ActionListener {

    /**
     * Student accessing the course registration system
     */
    private Student student;

    /**
     * Controller for the associated catalog objects
     */
    private CatalogController catalogController;

    /**
     * View for the main menu window
     */
    private InterfaceView interfaceView;

    /**
     * Views for the associated student windows
     */
    private StudentView studentView;

    /**
     * Constructor to initialize the catalog controller
     * @param catalogController Controller for the associated catalog objects
     * @param interfaceView View for the main menu window
     */
    public StudentController(CatalogController catalogController, InterfaceView interfaceView) {
        this.student = new Student();
        this.catalogController = catalogController;
        this.interfaceView = interfaceView;
        this.studentView = new StudentView();

        // Add action listeners to the student views
        this.studentView.getAddCourseWindow().addActionListeners(this);
        this.studentView.getRemoveCourseWindow().addActionListeners(this);
        this.studentView.getViewCoursesWindow().addActionListeners(this);
    }

    /**
     * Responds to an action event in one of the frames
     * @param evt action event of an element in one of the frame
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedAddCourse(evt);
        this.actionPerformedRemoveCourse(evt);
        this.actionPerformedViewCourses(evt);
    }

    /**
     * Responds to an action event in the add course frame
     * @param evt action event of an element in the add course frame
     */
    public void actionPerformedAddCourse(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getAddCourseWindow().getCourseNameComboBox()) {
            // If the course name combo box is selected, refresh the course number combo box
            this.studentView.getAddCourseWindow().refreshCourseNumComboBox(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getCourseNumComboBox()) {
            // If the course number combo box is selected, refresh the course section combo box
            this.studentView.getAddCourseWindow().refreshCourseSectionComboBox(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getAddCourseButton()) {
            // If add course button is pressed, add the selected course to student courses
            this.addCourse();
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getAddCourseWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Responds to an action event in the remove course frame
     * @param evt action event of an element in the remove course frame
     */
    public void actionPerformedRemoveCourse(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getRemoveCourseWindow().getRemoveCourseButton()) {
            // If remove course button is pressed, remove selected course from student registered courses
            this.removeCourse();
        } else if (evt.getSource() == this.studentView.getRemoveCourseWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getRemoveCourseWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Responds to an action event in the view registered courses frame
     * @param evt action event of an element in the add view registered courses frame
     */
    public void actionPerformedViewCourses(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getViewCoursesWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getViewCoursesWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Adds a selected course to the list of student registered courses
     */
    public void addCourse() {
        // Obtain course code and section number from the combo boxes
        Course course = this.catalogController.getCatalog().searchCatalog(this.studentView.getAddCourseWindow().getCourseNameComboBox().getSelectedItem() +
                " " + this.studentView.getAddCourseWindow().getCourseNumComboBox().getSelectedItem());
        int sectionNum = this.studentView.getAddCourseWindow().getCourseSectionComboBox().getSelectedIndex() + 1;

        // Validate the enrollment of the student in the selected course
        if (this.validateEnrollment(course, sectionNum)) {
            // Register student in course
            this.student.registerForCourse(course, sectionNum);
            this.studentView.getAddCourseWindow().showSuccessfulRegistrationDialog();

            // Warn user of course cancellation requirements
            if (course.getSections().get(sectionNum - 1).getStudentList().size() < 8) {
                this.studentView.getAddCourseWindow().showCourseCancellationWarningDialog();
            }

            // Reset the add course window
            this.studentView.getAddCourseWindow().reset(this.catalogController.getCatalog());
        }
    }

    /**
     * Checks if student is eligible to enroll in a selected course
     * @param course course the student requested to register in
     * @param sectionNum section number of the student selected course
     * @return true if student is eligible to enroll in course, false otherwise
     */
    public boolean validateEnrollment(Course course, int sectionNum) {
        boolean isEligible = false;

        //  Check each requirement and display dialog if student is ineligible
        if (this.student.verifyCompletedCourses(course)) {
            this.studentView.getAddCourseWindow().showCompletedCourseErrorDialog();
        } else if (!this.student.verifyPrereqRequirements(course)) {
            this.studentView.getAddCourseWindow().showPrereqErrorDialog();
        } else if (this.student.verifyExistingRegistration(course)) {
            this.studentView.getAddCourseWindow().showExistingRegistrationErrorDialog();
        } else if (course.verifyFullCourse(sectionNum)) {
            this.studentView.getAddCourseWindow().showFullCourseErrorDialog();
        } else if (this.student.verifyMaxEnrollment()){
            this.studentView.getAddCourseWindow().showMaxEnrollmentErrorDialog();
        } else {
            // Set eligibility to true if student passes all requirements
            isEligible = true;
        }

        return isEligible;
    }

    /**
     * Removes a selected course from the list of student registered courses
     */
    public void removeCourse() {
        // Remove the student selected course in the combo box and reset the window
        this.student.removeRegistration(this.studentView.getRemoveCourseWindow().getCourseComboBox().getSelectedIndex());
        this.studentView.getRemoveCourseWindow().showSuccessfulRegistrationRemovalDialog();
        this.studentView.getRemoveCourseWindow().reset(this.student);
    }

    /**
     * Getter that retrieves the student
     * @return the student accessing the application
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Getter that retrieves the student view
     * @return the student view
     */
    public StudentView getStudentView() {
        return studentView;
    }
}