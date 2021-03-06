package Model;

import java.util.ArrayList;

/**
 * A database that stores the university course and student information.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class Database {

    /**
     * Loads the catalog courses into a list
     * @return a list containing the catalog database
     */
    public static ArrayList<Course> loadCatalogDatabase() {
        ArrayList<Course> catalogDatabase = new ArrayList<>();

        // Define courses and add prerequisite requirements
        Course acct217 = new Course("ACCT", 217);

        Course acct323 = new Course("ACCT", 323);
        acct323.addPrereq(acct217);

        Course chem201 = new Course("CHEM", 201);

        Course chem213 = new Course("CHEM", 213);
        chem213.addPrereq(chem201);

        Course math211 = new Course("MATH", 211);

        Course math275 = new Course("MATH", 275);

        Course math277 = new Course("MATH", 277);
        math277.addPrereq(math211);
        math277.addPrereq(math275);

        Course engg201= new Course("ENGG", 201);

        Course engg202 = new Course("ENGG", 202);

        Course engg311 = new Course("ENGG", 311);
        engg311.addPrereq(engg201);
        engg311.addPrereq(math275);

        Course engg349 = new Course("ENGG", 349);
        engg349.addPrereq(engg202);
        engg349.addPrereq(math275);
        engg349.addPrereq(math277);

        Course phys221 = new Course("PHYS", 221);

        Course phys259 = new Course("PHYS", 259);
        phys259.addPrereq(math211);
        phys259.addPrereq(math275);

        // Add courses to the database
        catalogDatabase.add(acct217);
        catalogDatabase.add(acct323);
        catalogDatabase.add(chem201);
        catalogDatabase.add(chem213);
        catalogDatabase.add(math211);
        catalogDatabase.add(math275);
        catalogDatabase.add(math277);
        catalogDatabase.add(engg201);
        catalogDatabase.add(engg202);
        catalogDatabase.add(engg311);
        catalogDatabase.add(engg349);
        catalogDatabase.add(phys221);
        catalogDatabase.add(phys259);

        // Create section offerings for each course in the database
        for(Course course: catalogDatabase) {
            // Generate 1 to 4 sections for each course
            int numOfferings = (int) (Math.random() * 4 + 1);

            for(int i = 1; i <= numOfferings; i++) {
                // Create section with a capacity of 25 to 100 students
                course.addOffering(new CourseSection(course, i, (int) (Math.random() * 75 + 25)));
            }
        }

        return catalogDatabase;
    }

    /**
     * Loads the registration information of each student into the courses
     * @param catalogDatabase a list containing the catalog database
     * @return the updated catalog database with student registrations
     */
    public static ArrayList<Course> loadRegistrationDatabase(ArrayList<Course> catalogDatabase) {
        ArrayList<Student> studentList = new ArrayList<>();

        // Generate an imaginary student list
        for(int i = 0; i < 25; i++) {
            studentList.add(new Student("Student " + (i + 1), i + 1));
        }

        // Register each student in a random set of courses
        for(Student student: studentList) {
            for(int i = (int) (Math.random() * 4); i < catalogDatabase.size(); i = i + (int) (Math.random() * 2 + 1)) {
                Course course = catalogDatabase.get(i);
                new Registration(student, course.getSections().get((int) (Math.random() * course.getSections().size())));
            }
        }

        return catalogDatabase;
    }

    /**
     * Generates a list of completed courses completed by the student user
     * @return a list of courses completed by the student
     */
    public static ArrayList<Course> generateUserCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<Course>();

        // Add courses the student (user) has already completed
        completedCourses.add(new Course("CHEM", 201));
        completedCourses.add(new Course("ENGG", 201));
        completedCourses.add(new Course("MATH", 211));
        // student.getCompletedCourses().add(new Course("Model.Course name", 000));
        // TODO: Add additional courses here

        return completedCourses;
    }
}