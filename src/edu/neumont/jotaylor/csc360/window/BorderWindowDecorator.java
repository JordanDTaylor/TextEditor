package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.Points;

/**
 * Created by jotaylor on 4/9/2016.
 */
public class BorderWindowDecorator extends AbstractWindowDecorator {

    private final int borderThickness;
    private DesktopColor borderColor;
    private static final DesktopColor DEFAULT_BORDER_COLOR = DesktopColor.DARK_GRAY;
    private static final int DEFAULT_BORDER_WIDTH = 10;

    public BorderWindowDecorator(IWindow innerWindow){
        this(innerWindow, DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_COLOR);
    }

    public BorderWindowDecorator(IWindow innerWindow, DesktopColor borderColor){
        this(innerWindow, DEFAULT_BORDER_WIDTH, borderColor);
    }

    public BorderWindowDecorator(IWindow innerWindow, int borderWidth){
        this(innerWindow, borderWidth, DEFAULT_BORDER_COLOR);
    }

    public BorderWindowDecorator(IWindow innerWindow, int borderThickness, DesktopColor borderColor) {
        super(innerWindow);
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
    }


    @Override
    protected void doPaintStuff(DesktopGraphics desktopGraphics) {
        Point topLeft = getTopLeft();
        Point bottomRight = getBottomRight();
        Point bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
        Point topRight = new Point(bottomRight.getX(), topLeft.getY());

        for (int i = 0; i < borderThickness; i++) {
            desktopGraphics.drawLine(topLeft, topRight, borderColor);
            desktopGraphics.drawLine(topRight, bottomRight, borderColor);
            desktopGraphics.drawLine(bottomRight, bottomLeft, borderColor);
            desktopGraphics.drawLine( bottomLeft, topLeft, borderColor);

            topLeft = Points.addToPoint(topLeft, 1, 1);
            topRight = Points.addToPoint(topRight, -1, 1);
            bottomRight = Points.addToPoint(bottomRight, -1, -1);
            bottomLeft = Points.addToPoint(bottomLeft, 1, -1);
        }
    }

    @Override
    public Point getTopLeft() {
        return Points.addToPoint(innerWindow.getTopLeft(), -borderThickness, -borderThickness);
    }

    @Override
    public Point getBottomRight() {
        return Points.addToPoint(innerWindow.getBottomRight(), borderThickness, borderThickness);
    }

    @Override
    public int getHeight() {
        return innerWindow.getHeight() + borderThickness * 2;
    }

    @Override
    public int getWidth() {
        return innerWindow.getWidth() + borderThickness * 2;
    }
}
