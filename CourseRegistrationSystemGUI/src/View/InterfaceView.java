package View;

import Controller.InterfaceController;
import View.Frames.LoginFrame;
import View.Frames.MainMenuFrame;

public class InterfaceView {

    private LoginFrame loginWindow;

    private MainMenuFrame mainMenuWindow;

    public InterfaceView() {
        this.loginWindow = new LoginFrame();
        this.mainMenuWindow = new MainMenuFrame();
    }


    public LoginFrame getLoginWindow() {
        return this.loginWindow;
    }

    public MainMenuFrame getMainMenuWindow() {
        return this.mainMenuWindow;
    }


}
