package View;

import View.Frames.*;

/**
 * View of the catalog windows.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class CatalogView {

    /**
     * The search catalog window
     */
    private SearchCatalogFrame searchCatalogWindow;

    /**
     * The view course catalog window
     */
    private ViewCatalogFrame viewCatalogWindow;

    /**
     * Constructor to initialize the catalog view
     */
    public CatalogView() {
        this.searchCatalogWindow = new SearchCatalogFrame();
        this.viewCatalogWindow = new ViewCatalogFrame();
    }

    /**
     * Getter to retrieve the search catalog window
     * @return the search catalog window
     */
    public SearchCatalogFrame getSearchCatalogWindow() {
        return this.searchCatalogWindow;
    }

    /**
     * Getter to retrieve the view course catalog window
     * @return the view course catalog window
     */
    public ViewCatalogFrame getViewCatalogWindow() {
        return this.viewCatalogWindow;
    }
}