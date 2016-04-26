package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.Points;

/**
 * Created by jotaylor on 4/8/2016.
 */
public class TitleBarWindowDecorator extends AbstractWindowDecorator{

    private DesktopColor backgroundColor;
    private DesktopColor textColor;

    public static final int TOP_MARGIN = 5;
    public static final int LEFT_MARGIN = 5;
    public static final int DEFAULT_HEIGHT = 30;

    private static final DesktopColor DEFAULT_BACKGROUND_COLOR = DesktopColor.BLUE;
    private static final DesktopColor DEFAULT_TEXT_COLOR = DesktopColor.MAGENTA;

    public TitleBarWindowDecorator(IWindow innerWindow) {
        this(innerWindow, DEFAULT_BACKGROUND_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_HEIGHT);
    }

    public TitleBarWindowDecorator(IWindow innerWindow, int height) {
        this(innerWindow, DEFAULT_BACKGROUND_COLOR, DEFAULT_TEXT_COLOR, height);
    }

    public TitleBarWindowDecorator(IWindow innerWindow, DesktopColor backgroundColor, DesktopColor textColor, int height) {
        super(innerWindow);
        this.height = height;
//        this.width = innerWindow.getWidth();
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    protected void doPaintStuff(DesktopGraphics desktopGraphics) {
        Point topLeft = getTopLeft();
        Point bottomRight = Points.addToPoint(topLeft, getWidth(), height);

        desktopGraphics.fillRectangle(topLeft, bottomRight, backgroundColor);

        String title = getTitle();
//        if(title==null) title = "";

        int charHeight = desktop.getCharHeight();

        int cursorY = topLeft.getY() + (height - charHeight)/2 + charHeight - TOP_MARGIN ;

        Cursor cursor = new Cursor(topLeft.getX() + LEFT_MARGIN, cursorY );

        for(char c : title.toCharArray()){
            int charWidth = desktop.getCharWidth(c);
            Point cursorPosition = cursor.getPosition();
            if(cursorPosition.getX() + charWidth > getWidth())
                break;
            desktopGraphics.drawChar(c, cursor.getPosition(), textColor);
            cursor.setPosition(cursorPosition.getX() + charWidth, cursorPosition.getY());
        }

    }

    @Override
    public Point getTopLeft() {
        Point innerTopLeft = innerWindow.getTopLeft();
        return Points.addToPoint(innerTopLeft, 0, -height);
    }

    @Override
    public Point getBottomRight() {
        return innerWindow.getBottomRight();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return innerWindow.getWidth();
    }
}
