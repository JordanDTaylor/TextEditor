package edu.neumont.jotaylor.csc360.window.factory;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.configuration.Configuration;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;
import edu.neumont.jotaylor.csc360.window.IWindow;

public class BasicWindowFactory extends WindowFactory {

    public BasicWindowFactory(Configuration configuration){
        super(configuration);
    }

    @Override
    public IWindow createWindow(Desktop desktop, IKeyboard keyboard, int width, int height, int x, int y, String title) {
        return getBaseWindow(desktop, keyboard, width, height, x, y, title);
    }
}
