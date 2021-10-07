package Controller;

import Model.Course;
import Model.Student;
import View.InterfaceView;
import View.StudentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentController implements ActionListener {

    private Student student;

    private CatalogController catalogController;

    private InterfaceView interfaceView;

    private StudentView studentView;

    public StudentController(CatalogController catalogController, InterfaceView interfaceView, StudentView studentView) {
        this.student = new Student();

        this.catalogController = catalogController;

        this.interfaceView = interfaceView;

        this.studentView = studentView;
        this.studentView.getAddCourseWindow().addActionListeners(this);
        this.studentView.getRemoveCourseWindow().addActionListeners(this);
        this.studentView.getViewCoursesWindow().addActionListeners(this);
    }

    /**
     * Responds to an action event in one of the frames
     * @param evt action event of an element in a frame
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedAddCourse(evt);
        this.actionPerformedRemoveCourse(evt);
        this.actionPerformedViewCourses(evt);
    }

    public void actionPerformedAddCourse(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getAddCourseWindow().getCourseNameComboBox()) {
            // If course name is selected, show the course numbers
            this.studentView.getAddCourseWindow().refreshCourseNumComboBox(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getCourseNumComboBox()) {
            // If course number is selected, show the course sections
            this.studentView.getAddCourseWindow().refreshCourseSectionComboBox(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getAddCourseButton()) {
            // If add course button is pressed, add selected course to student courses
            this.addCourse();
        } else if (evt.getSource() == this.studentView.getAddCourseWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getAddCourseWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void actionPerformedRemoveCourse(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getRemoveCourseWindow().getRemoveCourseButton()) {
            // If remove course button is pressed, remove selected course from student courses
            this.removeCourse();
        } else if (evt.getSource() == this.studentView.getRemoveCourseWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getRemoveCourseWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void actionPerformedViewCourses(ActionEvent evt) {
        if (evt.getSource() == this.studentView.getViewCoursesWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.studentView.getViewCoursesWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void addCourse() {
        Course course = this.catalogController.getCatalog().searchCatalog(this.studentView.getAddCourseWindow().getCourseNameComboBox().getSelectedItem() +
                " " + this.studentView.getAddCourseWindow().getCourseNumComboBox().getSelectedItem());
        int sectionNum = this.studentView.getAddCourseWindow().getCourseSectionComboBox().getSelectedIndex() + 1;

        if (this.validateEnrollment(course, sectionNum)) {
            this.student.registerForCourse(course, sectionNum);
            this.studentView.getAddCourseWindow().showSuccessfulRegistrationDialog();

            // Warn user of course cancellation requirements
            if (course.getSections().get(sectionNum - 1).getStudentList().size() < 8) {
                this.studentView.getAddCourseWindow().showCourseCancellationWarningDialog();
            }

            this.studentView.getAddCourseWindow().reset(this.catalogController.getCatalog());
        }
    }

    public boolean validateEnrollment(Course course, int sectionNum) {
        boolean isEligible = false;

        // Validate enrollment and print error message if student is ineligible
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
            isEligible = true;
        }

        return isEligible;
    }

    public void removeCourse() {
        this.student.removeRegistration(this.studentView.getRemoveCourseWindow().getCourseComboBox().getSelectedIndex());
        this.studentView.getRemoveCourseWindow().showSuccessfulRegistrationRemovalDialog();
        this.studentView.getRemoveCourseWindow().reset(student);
    }

    public Student getStudent() {
        return this.student;
    }
}
