package View;

import View.Frames.*;

public class CatalogView {

    private SearchCatalogFrame searchCatalogWindow;

    private ViewCatalogFrame viewCatalogWindow;

    public CatalogView() {
        this.searchCatalogWindow = new SearchCatalogFrame();
        this.viewCatalogWindow = new ViewCatalogFrame();
    }

    public SearchCatalogFrame getSearchCatalogWindow() {
        return this.searchCatalogWindow;
    }

    public ViewCatalogFrame getViewCatalogWindow() {
        return this.viewCatalogWindow;
    }
}
