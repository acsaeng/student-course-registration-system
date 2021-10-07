package Controller;

import Model.Course;
import Model.CourseCatalog;
import View.CatalogView;
import View.InterfaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogController implements ActionListener {

    private CourseCatalog catalog;

    private InterfaceView interfaceView;

    private CatalogView catalogView;

    public CatalogController(InterfaceView interfaceView, CatalogView catalogView) {
        this.catalog = new CourseCatalog();

        this.interfaceView = interfaceView;

        this.catalogView = catalogView;
        this.catalogView.getSearchCatalogWindow().addActionListeners(this);
        this.catalogView.getViewCatalogWindow().addActionListeners(this);
    }

    /**
     * Responds to an action event in the frame
     *
     * @param evt action event of an element in the frame
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.actionPerformedSearchCatalog(evt);
        this.actionPerformedViewCatalog(evt);
    }

    public void actionPerformedSearchCatalog(ActionEvent evt) {
        if (evt.getSource() == this.catalogView.getSearchCatalogWindow().getSearchButton()) {
            // If the search button is pressed, display the course information
            this.searchForCourse();
        } else if (evt.getSource() == this.catalogView.getSearchCatalogWindow().getMainMenuButton()) {
            // If main menu button is pressed, return to the main menu
            this.catalogView.getSearchCatalogWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void actionPerformedViewCatalog(ActionEvent evt) {
        if (evt.getSource() == this.catalogView.getViewCatalogWindow().getCourseNameComboBox()) {
            this.displayCourseList();
        } else if (evt.getSource() == this.catalogView.getViewCatalogWindow().getMainMenuButton()) {
            this.catalogView.getViewCatalogWindow().deactivate();
            this.interfaceView.getMainMenuWindow().activate();
        }
    }

    public void searchForCourse() {
        String courseCodeInput = this.catalogView.getSearchCatalogWindow().getCourseNameTextField().getText().toUpperCase() +
                " " + this.catalogView.getSearchCatalogWindow().getCourseNumTextField().getText().toUpperCase();

        if (Course.validateCourseCode(courseCodeInput)) {
            for (Course course: catalog.getCourseList()) {
                if(course.toString().equals(courseCodeInput)) {
                    this.catalogView.getSearchCatalogWindow().getCourseInfoArea().setText(course.printCourseInfo());
                    return;
                }
            }

            this.catalogView.getSearchCatalogWindow().showNullCourseDialog();
        } else {
            this.catalogView.getSearchCatalogWindow().showInvalidInputErrorDialog();
        }
    }

    public void displayCourseList() {
        String courseName = "" + this.catalogView.getViewCatalogWindow().getCourseNameComboBox().getSelectedItem();
        String output = this.catalog.printCourseList(courseName);
        this.catalogView.getViewCatalogWindow().getCatalogInfoArea().setText(output);
    }

    public CourseCatalog getCatalog() {
        return catalog;
    }
}
