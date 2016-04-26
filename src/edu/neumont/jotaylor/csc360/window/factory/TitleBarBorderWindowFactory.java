package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.decorators.TitleBarWindowDecorator;

public class TitleBarBorderWindowFactory extends WindowFactory {
    WindowFactory baseFactory;
    public TitleBarBorderWindowFactory(Configuration configuration) {
        super(configuration);
        baseFactory = new BorderWindowFactory(configuration);
    }

    @Override
    public IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title) {
        IWindow borderWindow = baseFactory.createWindow(desktop, keyboard, width, height, x, y, title);

        DesktopColor titleTextColor = configuration.getTitleForegroundColor();
        DesktopColor backgroundColor = configuration.getTitleBackgroundColor();
        int titleBarHeight = configuration.getTitleHeight();

        TitleBarWindowDecorator tbwd = new TitleBarWindowDecorator(borderWindow, backgroundColor, titleTextColor, titleBarHeight);
        tbwd.setOrigin(x,y);
        return tbwd;
    }
}
