package ui.gui;

//Represents the gui for the splashscreen and menu
public class GuiHandler {

    public GuiHandler() {
        showSplashScreen();
    }

    //Shows the splash screen then the menu
    private void showSplashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        splashScreen.setVisible(false);
        new MenuFrame();
    }
}
