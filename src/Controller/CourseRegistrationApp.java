package Controller;

/**
 * Program that serves as the interface for a course registration application.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class CourseRegistrationApp {

    /**
     * Runs the Course Registration Application
     */
    public void run() {
        // Create a new interface controller to initialize and display the application login
        new InterfaceController();
    }

    /**
     * Initializes and starts the Course Registration Application
     * @param args none
     */
    public static void main(String[] args) {
        CourseRegistrationApp app = new CourseRegistrationApp();
        app.run();
    }
}