package Model;

import java.util.ArrayList;

public class CourseCatalog {

    private final ArrayList<Course> courseList;

    public CourseCatalog() {
        // Create an imaginary database for the offered courses
        this.courseList = Database.loadRegistrationDatabase(Database.loadCatalogDatabase());

    }

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

    public String printCourseList(String courseName) {
        StringBuilder output = new StringBuilder();

        // Print information for all courses in the catalog
        for (Course course: this.courseList) {
            if (courseName.equals(course.getCourseName())) {
                output.append(course.printCourseInfo()).append("\n\n");
            }
        }

        output.deleteCharAt(output.length() - 1);

        return output.toString();
    }

    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }
}
