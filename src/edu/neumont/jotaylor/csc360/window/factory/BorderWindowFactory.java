package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.window.decorators.BorderWindowDecorator;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.Window;

public class BorderWindowFactory extends WindowFactory {
    private final int borderWidth;
    private final DesktopColor borderColor;

    public BorderWindowFactory(Configuration configuration){
        super(configuration);
        this.borderWidth = configuration.getBorderWidth();
        this.borderColor = configuration.getBorderColor();
    }


    @Override
    public IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title) {
        Window base = getBaseWindow(desktop, keyboard, width, height, x, y, title);
        BorderWindowDecorator bwd = new BorderWindowDecorator(base, borderWidth, borderColor);
        bwd.setOrigin(x,y);
        return bwd;
    }
}
