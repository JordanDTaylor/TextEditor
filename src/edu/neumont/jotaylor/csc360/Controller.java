package edu.neumont.jotaylor.csc360;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.keyboard.KeyboardFactory;
import edu.neumont.jotaylor.csc360.configuration.KeyboardType;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.factory.WindowFactory;

import java.awt.*;
import java.io.IOException;

public class Controller {
    WindowFactory theWindowMaker;
    Configuration configuration;
    public Controller() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        try {
            configuration = Configuration.read(Configuration.DEFAULT_PATH);
        } catch (IOException e) {
            Logger.log(logType, "Configuration couldn't be loaded, using defaults.'");
        }

        Logger.log(logType, "Configuration is \n" + configuration.toString());

        int desktopWidth = screenSize.width - 75;
        int desktopHeight = screenSize.height - 75;

        Desktop desktop = new Desktop(desktopWidth / 2, desktopHeight);

        IKeyboard keyboard;

        KeyboardType keyboardType = configuration.getKeyboardType();
        Logger.log(logType, "Initializing keyboard type " + keyboardType);
        keyboard = KeyboardFactory.getKeyboard(keyboardType, desktop);

        Logger.log(logType, "Setting window factory of type " + configuration.getWindowType());
        theWindowMaker = WindowFactory.getFactory(configuration);

        int width = configuration.getWindowWidth();
        int height = configuration.getWindowHeight();
        int startingPositionX = 9 + 50;
        int startingPositionY = 39 +  50;

        Logger.log(logType, "Creating " + configuration.getWindowType() + " window.");
        IWindow window = theWindowMaker.createWindow(desktop, keyboard, width, height, startingPositionX, startingPositionY, "Text Editor");

        desktop.registerPaintable(window);
        Logger.log(logType, "Repainting the desktop.");

        desktop.repaint();
    }
    String logType = "Controller";

}

