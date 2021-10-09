package Model;

import java.util.ArrayList;

/**
 * A section of a university course offered at the institution.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class CourseSection {

    /**
     * Course the section belongs to
     */
    Course course;

    /**
     * Number of the section
     */
    private int sectionNum;

    /**
     * Maximum number of students that can be enrolled in the section
     */
    private int sectionCap;

    /**
     * List of student registrations enrolled in the section
     */
    private ArrayList<Registration> studentList;

    /**
     * Constructor to initialize the course section
     * @param course course the section belongs to
     * @param sectionNum number of the section
     * @param sectionCap maximum number of students that can be enrolled in the section
     */
    public CourseSection(Course course, int sectionNum, int sectionCap) {
        this.course = course;
        this.sectionNum = sectionNum;
        this.sectionCap = sectionCap;
        this.studentList = new ArrayList<>();
    }

    /**
     * Adds a student registration to the section
     * @param registration the student registration to be added
     */
    public void addRegistration(Registration registration) {
        // Add registration to student list
        studentList.add(registration);
    }

    /**
     * Removes a student registration from the section
     * @return the student registration to be removed
     */
    public void removeRegistration(Registration reg) {
        // Remove registration from student list
        studentList.remove(reg);
    }

    /**
     * Getter that retrieves the course
     * @return the course the section is in
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Setter that stores the course
     * @param course the course to be stored
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Getter that retrieves the course list
     * @return the course list
     */
    public int getSectionNum() {
        return this.sectionNum;
    }

    /**
     * Getter that retrieves the section capacity
     * @return the section capacity
     */
    public int getSectionCap() {
        return this.sectionCap;
    }

    /**
     * Getter that retrieves the student registration list
     * @return the student registration list
     */
    public ArrayList<Registration> getStudentList() {
        return this.studentList;
    }
}