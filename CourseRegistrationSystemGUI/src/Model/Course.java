package Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Course {

    private String courseName;

    private int courseNum;

    private ArrayList<Course> prereqs;

    private ArrayList<CourseSection> sections;

    public Course(String courseName, int courseNum) {
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.prereqs = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    public static boolean validateCourseCode(String courseCode) {
        // Validate if input String matches the regex pattern
        return Pattern.matches("[a-zA-Z]{4} \\d{3}", courseCode);
    }

    public void addPrereq(Course prereq) {
        this.prereqs.add(prereq);
    }

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

        return output.toString();
    }

    public boolean verifyFullCourse(int sectionNum) {
        // Verify if course is full
        return this.getSections().get(sectionNum - 1).getStudentList().size() ==
                this.getSections().get(sectionNum - 1).getSectionCap();
    }

    @Override
    public String toString() {
        return this.getCourseName() + " " + this.getCourseNum();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if(!(obj instanceof Course)) {
            return false;
        } else {
            Course other = (Course) obj;
            return this.getCourseName().equals(other.getCourseName()) && this.getCourseNum() == other.getCourseNum();
        }
    }

    public void addOffering(CourseSection offering) {
        this.sections.add(offering);
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCourseNum() {
        return this.courseNum;
    }

    public ArrayList<Course> getPrereqs() {
        return this.prereqs;
    }

    public ArrayList<CourseSection> getSections() {
        return this.sections;
    }
}