package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.Window;

/**
 * Created by jotaylor on 4/9/2016.
 */

public abstract class WindowFactory {

    protected Configuration configuration;

    public WindowFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    protected Window getBaseWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title)
    {
        DesktopColor background = configuration.getWindowBackgroundColor();
        DesktopColor foreground = configuration.getWindowForegroundColor();
        Window base = new Window(desktop, width, height, x, y, title, background, foreground);
        keyboard.register(base);
        return base;
    }

    public abstract IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title);

    public static WindowFactory getFactory(Configuration configuration) {
        Logger.log("Window Factory", "returning " + configuration.getWindowType() + " window factory" );

        switch(configuration.getWindowType()){
            case BORDER:
                return new BorderWindowFactory(configuration);
            case TITLE:
                return new TitleBarWindowFactory(configuration);
            case TITLE_BORDER:
                return new TitleBarBorderWindowFactory(configuration);
            case BORDER_TITLE_BORDER:
                return new BorderTitleBarBorderWindowFactory(configuration);
            case NORMAL:
                default:
                return new BasicWindowFactory(configuration);
        }
    }
}

