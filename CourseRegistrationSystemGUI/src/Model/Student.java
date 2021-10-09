package Model;

import java.util.ArrayList;

/**
 * A university student attending the institution.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class Student {

    /**
     * Name of the student
     */
    private String studentName;

    /**
     * ID number of the student
     */
    private int studentId;

    /**
     * List of courses the student has completed
     */
    private ArrayList<Course> completedCourses;

    /**
     * List of courses the student is registered in
     */
    private ArrayList<Registration> registeredCourses;

    public Student() {
        this.completedCourses = Database.generateUserCompletedCourses();
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * Constructor to initialize the student registration
     * @param studentName name of the student
     * @param studentId ID number of the student
     */
    public Student(String studentName, int studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.completedCourses = Database.generateUserCompletedCourses();
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * Verifies if a specified course was already completed by the student
     * @param course the course to be verified
     * @return true if the student has completed the course, false otherwise
     */
    public boolean verifyCompletedCourse(Course course) {
        return this.getCompletedCourses().contains(course);
    }

    /**
     * Verifies if student has completed the prerequisites for a specified course
     * @param course the course to be verified
     * @return true if the student has completed the prerequisites, false otherwise
     */
    public boolean verifyPrereqRequirements(Course course) {
        // Iterate through the prerequisite courses to see if the student has completed them
        for (Course prereq: course.getPrereqs()) {
            if (!this.completedCourses.contains(prereq)) {
                return false;
            }
        }

        return true; // Return true if all prerequisite requirements are met
    }

    /**
     * Verifies if student is already enrolled in a course
     * @param course the course to be verified
     * @return true of the student is already enrolled in the course, false otherwise
     */
    public boolean verifyExistingRegistration(Course course) {
        // Iterate through the student registered course to see if they are already registered in the course
        for(Registration reg: this.registeredCourses) {
            if(reg.getCourseSection().getCourse().equals(course)) {
                return true;
            }
        }

        return false; // Return false if course was not found
    }

    /**
     * Verifies if student is already enrolled in the maximum number of courses
     * @return true if the student is already enrolled in six courses, false otherwise
     */
    public boolean verifyMaxEnrollment() {
        return this.getRegisteredCourses().size() == 6;
    }

    /**
     * Registers the student in a course
     * @param course the course the student is registering in
     * @param sectionNum the course section the student is registering in
     */
    public void registerForCourse(Course course, int sectionNum) {
        if (course != null) {
            CourseSection offering = course.getSections().get(sectionNum - 1);
            new Registration(this, offering);
        }
    }

    /**
     * Adds a registration to the student's registered courses
     * @param reg registration to be added
     */
    public void addRegistration(Registration reg) {
        this.registeredCourses.add(reg);
    }

    /**
     * Removes a registration to the student's registered courses
     * @param courseIndex the index of the registration to be removed
     */
    public void removeRegistration(int courseIndex) {
        Registration reg = this.registeredCourses.get(courseIndex);
        reg.getCourseSection().removeRegistration(reg);
        this.registeredCourses.remove(courseIndex);
    }

    /**
     * Setter that stores the student's name
     * @param studentName the student's name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Setter that stores the student's ID number
     * @param studentId the student's ID number
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter that retrieves the student's completed courses
     * @return the student's completed courses
     */
    public ArrayList<Course> getCompletedCourses() {
        return this.completedCourses;
    }

    /**
     * Getter that retrieves the student's registered courses
     * @return the student's current registered courses
     */
    public ArrayList<Registration> getRegisteredCourses() {
        return this.registeredCourses;
    }

}