package edu.neumont.jotaylor.csc360;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.window.BorderWindowDecorator;
import edu.neumont.jotaylor.csc360.window.IWindow;
import edu.neumont.jotaylor.csc360.window.TitleBarWindowDecorator;
import edu.neumont.jotaylor.csc360.window.Window;

import java.awt.*;

public class Controller {
    KeyListener keyListener;

    public Controller() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int desktopWidth = screenSize.width - 50;
        int desktopHeight = screenSize.height - 50;

        Desktop desktop = new Desktop(desktopWidth, desktopHeight);
        keyListener = new KeyListener(desktop);

        int xDivisions = 12;
        int yDivisions = 10;

        int xDiv = (desktopWidth / xDivisions);
        int yDiv = (desktopHeight / yDivisions);

        int width = (xDivisions / 2 - 2) * xDiv;
        int height = (yDivisions / 2 - 2) * yDiv;

//        IWindow basicWindow = makeBaseWindow(desktop, width, height, xDiv, yDiv, "NU Word");
//        desktop.registerPaintable(basicWindow);

//        IWindow titledWindow = makeTitledWindow(desktop, width, height, xDiv * (xDivisions / 2 + 1), yDiv, "Titled Window");
//        desktop.registerPaintable(titledWindow);
//
//        IWindow titledBordedWindow = makeTitledBorderedWindow(desktop, width, height, xDiv, yDiv * (yDivisions / 2 + 1), "Titled Bordered Window");
//        desktop.registerPaintable(titledBordedWindow);
//
        IWindow borderedTitledBordedWindow = makeBorderedTitledBorderedWindow(desktop, width, height, xDiv * (xDivisions / 2 + 1), yDiv * (yDivisions / 2 + 1), "Bordered Titled Bordered Window");
        desktop.registerPaintable(borderedTitledBordedWindow);

        desktop.repaint();
    }

    private Window makeBaseWindow(Desktop desktop, int width, int height, int x, int y, String title){
        Window window = new Window(desktop, width, height, x, y, title);
        keyListener.register(window);
        return window;
    }

    private IWindow makeTitledWindow(Desktop desktop, int width, int height, int x, int y, String title) {
        Window base = makeBaseWindow(desktop, width, height, x, y, title);
        return new TitleBarWindowDecorator(base);
    }

    private IWindow makeTitledBorderedWindow(Desktop desktop, int width, int height, int x, int y, String title) {
        Window base = makeBaseWindow(desktop, width, height, x, y, title);
        return new TitleBarWindowDecorator(new BorderWindowDecorator(base));
    }

    private IWindow makeBorderedTitledBorderedWindow(Desktop desktop, int width, int height, int x , int y, String title) {
        Window base = makeBaseWindow(desktop, width, height, x, y, title);
        return new BorderWindowDecorator(new TitleBarWindowDecorator(new BorderWindowDecorator(base)));
    }
}

