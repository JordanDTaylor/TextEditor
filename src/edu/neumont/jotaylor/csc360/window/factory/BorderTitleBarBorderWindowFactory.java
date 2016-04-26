package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.window.decorators.BorderWindowDecorator;
import edu.neumont.jotaylor.csc360.window.IWindow;

public class BorderTitleBarBorderWindowFactory extends WindowFactory {
WindowFactory baseFactory;
    public BorderTitleBarBorderWindowFactory(Configuration configuration) {
        super(configuration);
        baseFactory = new TitleBarBorderWindowFactory(configuration);
    }

    @Override
    public IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title) {
        IWindow titleBorderWindow = baseFactory.createWindow(desktop, keyboard, width, height, x, y, title);
        int borderWidth = configuration.getBorderWidth();
        DesktopColor borderColor = configuration.getBorderColor();
        BorderWindowDecorator bwd =  new BorderWindowDecorator(titleBorderWindow, borderWidth, borderColor);
        bwd.setOrigin(x,y);
        return bwd;

    }
}
