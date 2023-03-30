package ui.gui;

public class GuiHandler {

    public GuiHandler() {
        showSplashScreen();
    }

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
