package View;

import View.Frames.LoginFrame;
import View.Frames.MainMenuFrame;

/**
 * View of the interface windows.
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since October 2nd, 2021
 */
public class InterfaceView {

    /**
     * The login window
     */
    private LoginFrame loginWindow;

    /**
     * The main menu
     */
    private MainMenuFrame mainMenuWindow;

    /**
     * Constructor to initialize the interface view
     */
    public InterfaceView() {
        this.loginWindow = new LoginFrame();
        this.mainMenuWindow = new MainMenuFrame();
    }

    /**
     * Getter to retrieve the login window
     * @return the login window
     */
    public LoginFrame getLoginWindow() {
        return this.loginWindow;
    }

    /**
     * Getter to retrieve the main menu window
     * @return the main menu window
     */
    public MainMenuFrame getMainMenuWindow() {
        return this.mainMenuWindow;
    }
}