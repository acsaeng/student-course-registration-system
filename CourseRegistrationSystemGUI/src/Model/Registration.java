package Model;

public class Registration {

    private Student student;

    private CourseSection section;

    private char grade;

    public Registration(Student student, CourseSection section) {
        this.student = student;
        this.section = section;
        this.addRegistration();
    }

    private void addRegistration() {
        // Automatically add registration to the Model.Student and Offering objects
        student.addRegistration(this);
        section.addRegistration(this);
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseSection getCourseSection() {
        return this.section;
    }

    public void setCourseSection(CourseSection section) {
        this.section = section;
    }

    public char getGrade() {
        return this.grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
