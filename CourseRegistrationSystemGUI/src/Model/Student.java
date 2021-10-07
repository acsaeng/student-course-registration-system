package Model;

import java.util.ArrayList;

public class Student {

    private String studentName;

    private int studentId;

    private ArrayList<Course> completedCourses;

    private ArrayList<Registration> registeredCourses;

    public Student() {
        this.completedCourses = Database.generateUserCompletedCourses();
        this.registeredCourses = new ArrayList<>();
    }

    public Student(String studentName, int studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.completedCourses = Database.generateUserCompletedCourses();
        this.registeredCourses = new ArrayList<>();
    }

    public boolean verifyCompletedCourses(Course course) {
        // Verify if course was already completed by the student
        return this.getCompletedCourses().contains(course);
    }

    public boolean verifyPrereqRequirements(Course course) {
        // Verify if student has completed the prerequisites
        for (Course prereq: course.getPrereqs()) {
            if (!this.completedCourses.contains(prereq)) {
                return false; // Return false if prerequisite is not in the list of completed courses
            }
        }

        return true; // Return true if all prerequisite requirements are met
    }

    public boolean verifyExistingRegistration(Course course) {
        // Verify if student is already enrolled in course
        for(Registration reg: this.registeredCourses) {
            if(reg.getCourseSection().getCourse().equals(course)) {
                return true; // Return true if course is found
            }
        }

        return false; // Return false if course was not found
    }

    public boolean verifyMaxEnrollment() {
        // Verify if student is already enrolled in the maximum number of courses
        return this.getRegisteredCourses().size() == 6;
    }

    public void registerForCourse(Course course, int sectionNum) {
        // Register student in the course section
        if (course != null) {
            CourseSection offering = course.getSections().get(sectionNum - 1);
            new Registration(this, offering);
        }
    }

    public void addRegistration(Registration reg) {
        // Add registration to student's registered courses
        this.registeredCourses.add(reg);
    }

    public void removeRegistration(int courseIndex) {
        // Remove student registration from course
        Registration reg = this.registeredCourses.get(courseIndex);
        reg.getCourseSection().removeRegistration(reg);
        this.registeredCourses.remove(courseIndex);
    }




    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public ArrayList<Course> getCompletedCourses() {
        return this.completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public ArrayList<Registration> getRegisteredCourses() {
        return this.registeredCourses;
    }

    public void setRegisteredCourses(ArrayList<Registration> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
}