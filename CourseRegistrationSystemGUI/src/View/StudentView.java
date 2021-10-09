package View;

import View.Frames.*;

/**
 * View of the student windows.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class StudentView {

    /**
     * The add course window
     */
    private AddCourseFrame addCourseWindow;

    /**
     * The remove course window
     */
    private RemoveCourseFrame removeCourseWindow;

    /**
     * The view student courses window
     */
    private ViewCoursesFrame viewCoursesWindow;

    /**
     * Constructor to initialize the student view
     */
    public StudentView() {
        this.addCourseWindow = new AddCourseFrame();
        this.removeCourseWindow = new RemoveCourseFrame();
        this.viewCoursesWindow = new ViewCoursesFrame();
    }

    /**
     * Getter to retrieve the add course window
     * @return the add course window
     */
    public AddCourseFrame getAddCourseWindow() {
        return this.addCourseWindow;
    }

    /**
     * Getter to retrieve the remove course window
     * @return the remove course window
     */
    public RemoveCourseFrame getRemoveCourseWindow() {
        return this.removeCourseWindow;
    }

    /**
     * Getter to retrieve the view student courses window
     * @return the view student courses window
     */
    public ViewCoursesFrame getViewCoursesWindow() {
        return this.viewCoursesWindow;
    }
}