package edu.neumont.csc415;

import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.Point;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DesktopGraphics {
    private Graphics2D g2d;

    public DesktopGraphics(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void drawChar(char c, Point location, DesktopColor color) {
        this.g2d.setColor(color.getColor());
        this.g2d.drawString("" + c, location.getX(), location.getY());
    }

    public void drawLine(Point start, Point end, DesktopColor color) {
        this.g2d.setColor(color.getColor());
        this.g2d.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public void fillRectangle(Point topLeft, Point bottomRight, DesktopColor color) {
        this.g2d.setColor(color.getColor());
        Rectangle rectangle = new Rectangle(topLeft.getX(), topLeft.getY(), Math.abs(topLeft.getX() - bottomRight.getX()), Math.abs(topLeft.getY() - bottomRight.getY()));
        this.g2d.fill(rectangle);
    }
}
