package Controller;

import View.InterfaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller that regulates communication between the controller model and controller view.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class InterfaceController implements ActionListener {

    /**
     * Views for the login and main menu windows
     */
    private InterfaceView interfaceView;

    /**
     * Controller for the associated catalog objects
     */
    private CatalogController catalogController;

    /**
     * Controller for the associated student objects
     */
    private StudentController studentController;

    /**
     * Constructor to initialize the interface controller
     */
    public InterfaceController() {
        this.interfaceView = new InterfaceView();

        // Add action listeners to the login and main menu windows
        this.interfaceView.getLoginWindow().addActionListeners(this);
        this.interfaceView.getMainMenuWindow().addActionListeners(this);

        this.catalogController = new CatalogController(this.interfaceView);
        this.studentController = new StudentController(this.catalogController, this.interfaceView);

        // Begin by opening the login window
        this.interfaceView.getLoginWindow().activate();
    }

    /**
     * Responds to an action event in one of the frames
     * @param evt action event of an element in one of the frames
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedLogin(evt);
        this.actionPerformedMainMenu(evt);
    }

    /**
     * Responds to an action event in the login frame
     * @param evt action event of an element in the login frame
     */
    public void actionPerformedLogin(ActionEvent evt) {
        if (evt.getSource() == this.interfaceView.getLoginWindow().getLoginButton()) {
            // If the login button is pressed, read input and store student data
            try {
                this.studentController.getStudent().setStudentName(this.interfaceView.getLoginWindow().getName());
                this.studentController.getStudent().setStudentId(Integer.parseInt(this.interfaceView.getLoginWindow().getStudentId()));
            } catch (NumberFormatException err) {
                // Show dialog if student ID input is not a number
                this.interfaceView.getLoginWindow().showInvalidInputErrorDialog();
                return;
            }

            // Open the main menu
            this.interfaceView.getLoginWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Responds to an action event in the main menu frame
     * @param evt action event of an element in the main menu frame
     */
    public void actionPerformedMainMenu(ActionEvent evt) {
        // Performs one of the following actions if the respective button is pressed
        if (evt.getSource() == this.interfaceView.getMainMenuWindow().getSearchCatalogButton()) {
            // Open the search catalog window
            this.interfaceView.getMainMenuWindow().deactivate();
            this.catalogController.getCatalogView().getSearchCatalogWindow().activate();
            this.catalogController.getCatalogView().getSearchCatalogWindow().reset();
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getAddCourseButton()) {
            // Open the add course window
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentController.getStudentView().getAddCourseWindow().activate();
            this.studentController.getStudentView().getAddCourseWindow().reset(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getRemoveCourseButton()) {
            // Open the remove course window
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentController.getStudentView().getRemoveCourseWindow().activate();
            this.studentController.getStudentView().getRemoveCourseWindow().reset(this.studentController.getStudent());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getViewCatalogButton()) {
            // Open the view course catalog window
            this.interfaceView.getMainMenuWindow().deactivate();
            this.catalogController.getCatalogView().getViewCatalogWindow().activate();
            this.catalogController.getCatalogView().getViewCatalogWindow().reset(this.catalogController.getCatalog());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getViewCoursesButton()) {
            // Open the view registered courses window
            this.interfaceView.getMainMenuWindow().deactivate();
            this.studentController.getStudentView().getViewCoursesWindow().activate();
            this.studentController.getStudentView().getViewCoursesWindow().reset(this.studentController.getStudent());
        } else if (evt.getSource() == this.interfaceView.getMainMenuWindow().getQuitButton()) {
            // Close the program
            System.exit(0);
        }
    }
}