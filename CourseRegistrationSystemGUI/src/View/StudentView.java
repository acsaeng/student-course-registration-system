package View;

import View.Frames.*;

public class StudentView {

    private AddCourseFrame addCourseWindow;

    private RemoveCourseFrame removeCourseWindow;

    private ViewCoursesFrame viewCoursesWindow;

    public StudentView() {
        this.addCourseWindow = new AddCourseFrame();
        this.removeCourseWindow = new RemoveCourseFrame();
        this.viewCoursesWindow = new ViewCoursesFrame();
    }

    public AddCourseFrame getAddCourseWindow() {
        return this.addCourseWindow;
    }

    public RemoveCourseFrame getRemoveCourseWindow() {
        return this.removeCourseWindow;
    }

    public ViewCoursesFrame getViewCoursesWindow() {
        return this.viewCoursesWindow;
    }


}
