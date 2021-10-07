package Controller;

import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceController implements ActionListener {

    private InterfaceView interfaceView;

    private StudentView studentView;

    private CatalogView catalogView;

    private CatalogController catalogController;

    private StudentController studentController;

    public InterfaceController() {
        this.interfaceView = new InterfaceView();
        this.studentView = new StudentView();
        this.catalogView = new CatalogView();

        this.interfaceView.getLoginWindow().addActionListeners(this);
        this.interfaceView.getMainMenuWindow().addActionListeners(this);

        this.catalogController = new CatalogController(this.interfaceView, this.catalogView);
        this.studentController = new StudentController(this.catalogController, this.interfaceView, this.studentView);

        this.interfaceView.getLoginWindow().activate();
    }

    /**
     * Responds to an action event in the frame
     *
     * @param evt action event of an element in the frame
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedLogin(evt);
        this.actionPerformedMainMenu(evt);
    }

    public void actionPerformedLogin(ActionEvent evt) {
        if (evt.getSource() == this.interfaceView.getLoginWindow().getLoginButton()) {
            try {
                this.studentController.getStudent().setStudentName(this.interfaceView.getLoginWindow().getName());
                this.studentController.getStudent().setStudentId(Integer.parseInt(this.interfaceView.getLoginWindow().getStudentId()));
            } catch (NumberFormatException err) {
                this.interfaceView.getLoginWindow().showInvalidInputErrorDialog();
                return;
            }

            // If login button is pressed, open the main menu
            this.interfaceView.getLoginWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void actionPerformedMainMenu(ActionEvent evt) {
        // Performs the next action if the respective main menu button is pressed
        if (evt.getSource() == this.interfaceView.getMainMenuWindow().getSearchCatalogButton()) {
            // Activate the search catalog frame
            this.interfaceView.getMainMenuWindow().deactivate();
            this.catalogView.getSearchCatalogWindow().activate();
            this.catalogView.getSearchCatalogWindow().reset();
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getAddCourseButton()) {
            // Activate the add course frame if button is pressed
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentView.getAddCourseWindow().activate();
            this.studentView.getAddCourseWindow().reset(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getRemoveCourseButton()) {
            // Activate the remove course frame if button is pressed
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentView.getRemoveCourseWindow().activate();
            this.studentView.getRemoveCourseWindow().reset(this.studentController.getStudent());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getViewCatalogButton()) {
            // Activate the view course catalog frame if button is pressed
            this.interfaceView.getMainMenuWindow().deactivate();
            this.catalogView.getViewCatalogWindow().activate();
            this.catalogView.getViewCatalogWindow().reset(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getViewCoursesButton()) {
            // Activate the view registered courses frame if button is pressed
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentView.getViewCoursesWindow().activate();
            this.studentView.getViewCoursesWindow().reset(this.studentController.getStudent());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getQuitButton()) {
            // Quit the program
            System.exit(0);
        }
    }
}