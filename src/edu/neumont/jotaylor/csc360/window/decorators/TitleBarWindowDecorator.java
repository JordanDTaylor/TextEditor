package edu.neumont.jotaylor.csc360.window.decorators;

import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.util.BoundingBoxUtil;
import edu.neumont.jotaylor.csc360.util.Cursor;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.util.Points;
import edu.neumont.jotaylor.csc360.window.BoundingBox;
import edu.neumont.jotaylor.csc360.window.IWindow;

/**
 * Created by jotaylor on 4/8/2016.
 */
public class TitleBarWindowDecorator extends AbstractWindowDecorator{

    private DesktopColor titleBackgroundColor;
    private DesktopColor titleTextColor;

    public static final int MARGIN = 4;
//    public static final int DEFAULT_HEIGHT = 30;
    public static final int MIN_TITLE_BAR_HEIGHT = 15;

    private static final DesktopColor DEFAULT_BACKGROUND_COLOR = DesktopColor.DARK_GRAY;
    private static final DesktopColor DEFAULT_TEXT_COLOR = DesktopColor.LIGHT_GRAY;
    private int titleBarHeight;

//    public TitleBarWindowDecorator(IWindow innerWindow) {
//        this(innerWindow, DEFAULT_BACKGROUND_COLOR, DEFAULT_TEXT_COLOR, DEFAULT_HEIGHT);
//    }

    public TitleBarWindowDecorator(IWindow innerWindow, DesktopColor backgroundColor, DesktopColor titleTextColor, int titleBarHeight) {
        super(innerWindow);
        this.titleBarHeight = titleBarHeight;
        this.titleBackgroundColor = backgroundColor;
        this.titleTextColor = titleTextColor;
    }

    @Override
    protected void doPaintStuff(DesktopGraphics desktopGraphics) {

        BoundingBox bounds = getTitleBarBounds();
        desktopGraphics.fillRectangle(bounds.getTopLeft(), Points.addToPoint(bounds.getBottomRight(), 1,0), titleBackgroundColor);

        BoundingBox textBounds = getTextBounds();
        Cursor cursor = new Cursor(textBounds.getMinX(), textBounds.getMaxY());

        char[] title = getTitle().toCharArray();

        for(char c : title){
            int charWidth = desktop.getCharWidth(c);
            Point cursorPosition = cursor.getPosition();

            if(cursorPosition.getX() + charWidth > textBounds.getMaxX())
                break;

            desktopGraphics.drawChar(c, cursor.getPosition(), titleTextColor);
            cursor.setPosition(cursorPosition.getX() + charWidth, cursorPosition.getY());
        }

    }

    public DesktopColor getTitleBackgroundColor() {
        return titleBackgroundColor;
    }

    public void setTitleBackgroundColor(DesktopColor titleBackgroundColor) {
        this.titleBackgroundColor = titleBackgroundColor != null
                                            ? titleBackgroundColor
                                            : DEFAULT_BACKGROUND_COLOR;
    }

    public DesktopColor getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(DesktopColor titleTextColor) {
        this.titleTextColor = titleTextColor != null ? titleTextColor : DEFAULT_TEXT_COLOR;
    }

    public int getTitleBarHeight() {
        return titleBarHeight;
    }

    public void setTitleBarHeight(int titleBarHeight) {
        this.titleBarHeight = titleBarHeight < MIN_TITLE_BAR_HEIGHT
                                      ? MIN_TITLE_BAR_HEIGHT
                                      : titleBarHeight;
    }

    private BoundingBox getTextBounds(){
        BoundingBox tbb = getTitleBarBounds();
        int charHeight = desktop.getCharHeight();
//        int delta = 0;
        int minY = tbb.getMinY();
        return new BoundingBox(tbb.getMinX() + MARGIN, minY, tbb.getWidth() - MARGIN * 2, charHeight );
    }

    private BoundingBox getTitleBarBounds(){
        BoundingBox iwb = innerWindow.getBoundingBox();
        return new BoundingBox(iwb.getMinX(), iwb.getMinY() - titleBarHeight, iwb.getWidth(), titleBarHeight);
    }

    @Override
    public void setOrigin(int x, int y) {
        int newY = y + titleBarHeight;

        Logger.log("Title_WD", "set origin called with x:"+x + ", y:"+y + "\n" +
                "\tBounds = minX:"+getBoundingBox().getMinX() + ", minY:" + getBoundingBox().getMinY() + "\n" +
                "\tsetting inner bounds = minX:" + x + ", minY:" + newY);

        innerWindow.setOrigin(x, newY);
        repaintFlag = true;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return BoundingBoxUtil.expandUp(innerWindow.getBoundingBox(), titleBarHeight);
    }
}
