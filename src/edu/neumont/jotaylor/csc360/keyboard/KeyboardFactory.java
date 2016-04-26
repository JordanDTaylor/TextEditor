package edu.neumont.jotaylor.csc360.keyboard;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.configuration.KeyboardType;

public class KeyboardFactory {
    public static IKeyboard getKeyboard(KeyboardType keyboardType, Desktop desktop) {
        IKeyboard keyboard = null;
        if(keyboardType == KeyboardType.MOC)
            keyboard = new MockKeyboard();
        else if(keyboardType == KeyboardType.REAL)
            keyboard = new Keyboard(desktop);
        return keyboard;
    }
}
