package edu.neumont.jotaylor.csc360.window.decorators;

import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.jotaylor.csc360.util.BoundingBoxUtil;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.window.BoundingBox;
import edu.neumont.jotaylor.csc360.window.IWindow;

public class BorderWindowDecorator extends AbstractWindowDecorator {

    private int borderWidth;
    private DesktopColor borderColor;

    public BorderWindowDecorator(IWindow innerWindow, int borderWidth, DesktopColor borderColor) {
        super(innerWindow);

        assert (borderColor != null);
        this.borderColor = borderColor;

        assert (borderWidth >= 0);
        this.borderWidth = borderWidth;

    }

    @Override
    protected void doPaintStuff(DesktopGraphics desktopGraphics) {
        BoundingBox bounds = getBoundingBox();

        for (int i = 0; i < borderWidth; i++) {
            drawBorder(desktopGraphics, bounds);
            bounds = BoundingBoxUtil.shrinkAll(bounds, 1);
        }
    }

    private void drawBorder(DesktopGraphics desktopGraphics, BoundingBox bounds){
        desktopGraphics.drawLine(bounds.getTopLeft(), bounds.getTopRight(), borderColor);
        desktopGraphics.drawLine(bounds.getTopRight(), bounds.getBottomRight(), borderColor);
        desktopGraphics.drawLine(bounds.getBottomRight(), bounds.getBottomLeft(), borderColor);
        desktopGraphics.drawLine( bounds.getBottomLeft(), bounds.getTopLeft(), borderColor);
    }

    @Override
    public BoundingBox getBoundingBox() {
        return BoundingBoxUtil.expandAll(innerWindow.getBoundingBox(), borderWidth);
    }

    @Override
    public void setOrigin(int x, int y) {
        int newX = x + borderWidth;
        int newY = y + borderWidth;
        Logger.log("Border_WD", "set origin called with x:"+x + ", y:"+y + "\n" +
                "\tBounds = minX:"+getBoundingBox().getMinX() + ", minY:" + getBoundingBox().getMinY() + "\n" +
                "\tsetting inner bounds = minX:" + newX + ", minY:" + newY);

        innerWindow.setOrigin(newX, newY);
        repaintFlag = true;
    }

//    public void setBorderWidth(int borderWidth){
//        this.borderWidth = borderWidth;
//    }
//
//    public int getBorderWidth() {
//        return borderWidth;
//    }
//
//    public DesktopColor getBorderColor() {
//        return borderColor;
//    }
//
//    public void setBorderColor(DesktopColor borderColor) {
//        this.borderColor = borderColor;
//    }
//

}
