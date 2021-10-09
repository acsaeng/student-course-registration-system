package Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A university course taught at the institution.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class Course {

    /**
     * Abbreviation of the department
     */
    private String courseName;

    /**
     * The unique course number
     */
    private int courseNum;

    /**
     * List of prerequisite courses required before taking this course
     */
    private ArrayList<Course> prereqs;

    /**
     * Number of offering for this course
     */
    private ArrayList<CourseSection> sections;

    /**
     * Constructor to initialize the course
     * @param courseName abbreviation of the department
     * @param courseNum the unique course number
     */
    public Course(String courseName, int courseNum) {
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.prereqs = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    /**
     * Checks if a course code is valid
     * @param courseCode an inputted course code
     * @return true if course code consists of four letters, a space, and then three number; false otherwise
     */
    public static boolean validateCourseCode(String courseCode) {
        // Validates if input String matches the regex pattern
        return Pattern.matches("[a-zA-Z]{4} \\d{3}", courseCode);
    }

    /**
     * Adds a course as a prerequisite
     * @param prereq a prerequisite course
     */
    public void addPrereq(Course prereq) {
        this.prereqs.add(prereq);
    }

    /**
     * Generates a string containing all the information about the course
     * @return the course information
     */
    public String printCourseInfo() {
        StringBuilder output = new StringBuilder();

        // Append all the course information
        output.append(" Course code: ").append(this).append("\n");
        output.append(" Number of sections: ").append(this.sections.size()).append("\n");
        output.append(" Prerequisites: ");

        // Append all the prerequisite courses
        if (this.getPrereqs().size() == 0) {
            output.append("None");
        } else {
            for (int i = 0; i < this.getPrereqs().size(); i++) {
                if (i == this.getPrereqs().size() - 1) {
                    output.append(this.getPrereqs().get(i));
                } else {
                    output.append(this.getPrereqs().get(i)).append(", ");
                }
            }
        }

        output.append("\n");
        return output.toString();
    }

    /**
     * Checks if a course section is full
     * @param sectionNum a section number of the course
     * @return true if the course is full, false otherwise
     */
    public boolean verifyFullCourse(int sectionNum) {
        // Verify if course is full
        return this.getSections().get(sectionNum - 1).getStudentList().size() == this.getSections().get(sectionNum - 1).getSectionCap();
    }

    /**
     * Prints the course code
     * @return the course code
     */
    @Override
    public String toString() {
        return this.getCourseName() + " " + this.getCourseNum();
    }

    /**
     * Checks if two courses are equivalent
     * @param obj a course to be compared
     * @return true if the two course codes are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            // Check if two objects are equivalent
            return true;
        } else if(!(obj instanceof Course)) {
            // Checks if the object is an instance of Course
            return false;
        } else {
            // Casts the Object to Course and compares the course codes
            Course other = (Course) obj;
            return this.getCourseName().equals(other.getCourseName()) && this.getCourseNum() == other.getCourseNum();
        }
    }

    /**
     * Add a section to the course
     * @param offering a course section
     */
    public void addOffering(CourseSection offering) {
        this.sections.add(offering);
    }

    /**
     * Getter that retrieves the course name
     * @return the course name
     */
    public String getCourseName() {
        return this.courseName;
    }

    /**
     * Getter that retrieves the course number
     * @return the course number
     */
    public int getCourseNum() {
        return this.courseNum;
    }

    /**
     * Getter that retrieves the prerequisite courses
     * @return the prerequisites of the course
     */
    public ArrayList<Course> getPrereqs() {
        return this.prereqs;
    }

    /**
     * Getter that retrieves the course sections
     * @return the course sections
     */
    public ArrayList<CourseSection> getSections() {
        return this.sections;
    }
}