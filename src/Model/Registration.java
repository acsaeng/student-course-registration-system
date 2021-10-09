package Model;

/**
 * A registration information of a student in a course.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class Registration {

    /**
     * Student registered in the course
     */
    private Student student;

    /**
     * Course section the student is registered in
     */
    private CourseSection section;

    /**
     * Grade the student achieves in the course
     */
    private char grade;

    /**
     * Constructor to initialize the student registration
     * @param student student registered in the course
     * @param section course section the student is registered in
     */
    public Registration(Student student, CourseSection section) {
        this.student = student;
        this.section = section;
        this.addRegistration();
    }

    /**
     * Adds the registration to the student and course section
     */
    private void addRegistration() {
        // Automatically add registration to the Model.Student and Offering objects
        student.addRegistration(this);
        section.addRegistration(this);
    }

    /**
     * Getter that retrieves the student
     * @return the registered student
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Setter that stores the student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Getter that retrieves the course section
     * @return the course section number
     */
    public CourseSection getCourseSection() {
        return this.section;
    }
}