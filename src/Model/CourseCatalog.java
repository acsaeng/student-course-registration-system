package Model;

import java.util.ArrayList;

/**
 * A university course catalog offered by the institution.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class CourseCatalog {

    /**
     * List of offered courses
     */
    private final ArrayList<Course> courseList;

    /**
     * Constructor to initialize the catalog
     */
    public CourseCatalog() {
        // Create an imaginary database for the offered courses
        this.courseList = Database.loadRegistrationDatabase(Database.loadCatalogDatabase());

    }

    /**
     * Searches the catalog for a specific course
     * @param courseCode the course code
     * @return the specified course if found, null otherwise
     */
    public Course searchCatalog(String courseCode) {
        // Search the entire course catalog database
        for(Course course: this.courseList) {
            if(courseCode.equals(course.toString())) {
                // Return the course if it is found
                return course;
            }
        }

        return null; // Return 'null' if the course was not found
    }

    /**
     * Generates a list of courses belonging to a specified department
     * @param courseName the course code of the department
     * @return a list of courses offered by the department
     */
    public String printCourseList(String courseName) {
        StringBuilder output = new StringBuilder();

        // Generate string that lists all the courses offered by the department
        for (Course course: this.courseList) {
            if (courseName.equals(course.getCourseName())) {
                output.append(course.printCourseInfo()).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Getter that retrieves the course list
     * @return the course list
     */
    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }
}
