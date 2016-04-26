package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.decorators.TitleBarWindowDecorator;
import edu.neumont.jotaylor.csc360.window.Window;

public class TitleBarWindowFactory extends WindowFactory {
    Configuration configuration;
    public TitleBarWindowFactory(Configuration configuration) {
        super(configuration);
    }

    @Override
    public IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title) {
        Window base = getBaseWindow(desktop, keyboard, width, height, x, y, title);
        DesktopColor titleTextColor = configuration.getTitleForegroundColor();
        DesktopColor titleBackgroundColor = configuration.getTitleBackgroundColor();
        int titleBarHeight = configuration.getTitleHeight();

        return new TitleBarWindowDecorator(base,titleBackgroundColor, titleTextColor, titleBarHeight);
    }
}
