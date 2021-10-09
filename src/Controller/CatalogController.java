package Controller;

import Model.Course;
import Model.CourseCatalog;
import View.CatalogView;
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
public class CatalogController implements ActionListener {

    /**
     * Catalog that contains the offered courses
     */
    private CourseCatalog catalog;

    /**
     * View for the main menu window
     */
    private InterfaceView interfaceView;

    /**
     * Views for the associated catalog windows
     */
    private CatalogView catalogView;

    /**
     * Constructor to initialize the catalog controller
     * @param interfaceView Views for the login and main menu windows
     */
    public CatalogController(InterfaceView interfaceView) {
        this.catalog = new CourseCatalog();
        this.interfaceView = interfaceView;
        this.catalogView = new CatalogView();

        // Add action listeners to the catalog views
        this.catalogView.getSearchCatalogWindow().addActionListeners(this);
        this.catalogView.getViewCatalogWindow().addActionListeners(this);
    }

    /**
     * Responds to an action event in one of the frames
     * @param evt action event of an element in one of the frame
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedSearchCatalog(evt);
        this.actionPerformedViewCatalog(evt);
    }

    /**
     * Responds to an action event in the search catalog frame
     * @param evt action event of an element in the search catalog frame
     */
    public void actionPerformedSearchCatalog(ActionEvent evt) {
        if (evt.getSource() == this.catalogView.getSearchCatalogWindow().getSearchButton()) {
            // If the search button is pressed, display the course search result
            this.searchForCourse();
        } else if (evt.getSource() == this.catalogView.getSearchCatalogWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.catalogView.getSearchCatalogWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Responds to an action event in the view catalog frame
     * @param evt action event of an element in the view catalog frame
     */
    public void actionPerformedViewCatalog(ActionEvent evt) {
        if (evt.getSource() == this.catalogView.getViewCatalogWindow().getCourseNameComboBox()) {
            // If an option in the combo box is selected, display all the courses offered by that department
            this.displayCourseList();
        } else if (evt.getSource() == this.catalogView.getViewCatalogWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.catalogView.getViewCatalogWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    /**
     * Displays the search result of a course
     */
    public void searchForCourse() {
        // Read the input from the text box
        String courseCodeInput = this.catalogView.getSearchCatalogWindow().getCourseNameTextField().getText().toUpperCase() +
                " " + this.catalogView.getSearchCatalogWindow().getCourseNumTextField().getText().toUpperCase();

        // Validate that the course code is in the right format
        if (Course.validateCourseCode(courseCodeInput)) {
            // If course name is valid, iterate through the course catalog to find the inputted course
            for (Course course: this.catalog.getCourseList()) {
                if(course.toString().equals(courseCodeInput)) {
                    // If course is found, display the course information
                    this.catalogView.getSearchCatalogWindow().getCourseInfoArea().setText(course.printCourseInfo());
                    return;
                }
            }

            // Show dialog if course could not be found
            this.catalogView.getSearchCatalogWindow().showNullCourseDialog();
        } else {
            // If course name is not valid, display error dialog
            this.catalogView.getSearchCatalogWindow().showInvalidInputErrorDialog();
        }
    }

    /**
     * Displays the course listings for a specified department
     */
    public void displayCourseList() {
        // Display the course list of the department selected in the combo box
        String courseName = "" + this.catalogView.getViewCatalogWindow().getCourseNameComboBox().getSelectedItem();
        String output = this.catalog.printCourseList(courseName);
        this.catalogView.getViewCatalogWindow().getCatalogInfoArea().setText(output);
    }

    /**
     * Getter that retrieves the course catalog
     * @return the course catalog
     */
    public CourseCatalog getCatalog() {
        return this.catalog;
    }

    /**
     * Getter that retrieves the catalog view
     * @return the catalog view
     */
    public CatalogView getCatalogView() {
        return catalogView;
    }
}