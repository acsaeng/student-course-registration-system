package Model;

import java.util.ArrayList;

public class CourseSection {

    Course course;

    private int sectionNum;

    private int sectionCap;

    private ArrayList<Registration> studentList;

    public CourseSection(Course course, int sectionNum, int sectionCap) {
        this.course = course;
        this.sectionNum = sectionNum;
        this.sectionCap = sectionCap;
        this.studentList = new ArrayList<>();
    }

    public void addRegistration(Registration registration) {
        // Add registration to student list
        studentList.add(registration);
    }

    public void removeRegistration(Registration reg) {
        // Remove registration from student list
        studentList.remove(reg);
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSectionNum() {
        return this.sectionNum;
    }

    public int getSectionCap() {
        return this.sectionCap;
    }

    public ArrayList<Registration> getStudentList() {
        return this.studentList;
    }
}
