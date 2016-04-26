package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.jotaylor.csc360.util.BoundingBoxUtil;
import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;

public class Window implements IWindow, IKeyboard.Observer {
//    private static final int DEFAULT_HEIGHT = 400;
//    private static final int DEFAULT_WIDTH = 400;
//    public static final int BOTTOM_MARGIN = 7;

    private final int margin = 5;
    private Desktop desktop;

    private BoundingBox boundingBox;

    private TextBox textBox;
    private DesktopColor backgroundColor;

    private boolean repaintForKeystroke;

    private String title;
    private boolean repaintDecorators;

//    public Window(Desktop desktop, int width, int height, int xPosition, int yPosition, DesktopColor backgroundColor, DesktopColor foregroundColor) {
//        this(desktop, width, height, xPosition, yPosition, "", backgroundColor, foregroundColor);
//    }

    public Window(Desktop desktop, int width, int height, int xPosition, int yPosition, String title, DesktopColor backgroundColor, DesktopColor foregroundColor) {

        boundingBox = new BoundingBox(xPosition, yPosition, width, height);

        BoundingBox textBounds = BoundingBoxUtil.shrinkAll(boundingBox, margin);
        textBox = new TextBox(desktop, textBounds, foregroundColor);
        this.backgroundColor = backgroundColor;
        setForegroundColor(foregroundColor);

        this.title = title;
        this.desktop = desktop;
    }

    @Override
    public void paint(DesktopGraphics desktopGraphics) {

        desktopGraphics.fillRectangle(boundingBox.getTopLeft(), boundingBox.getBottomRight(), backgroundColor);
        textBox.repaint(desktopGraphics);

        repaintDecorators = !repaintForKeystroke;
        repaintForKeystroke = false;
    }

    @Override
    public Desktop getDesktop() {
        return desktop;
    }

    @Override
    public boolean decoratorsShouldRepaint() {
        return repaintDecorators;
    }

    @Override
    public void setOrigin(int x, int y) {
        int xOffset = x - boundingBox.getMinX();
        int yOffset = y - boundingBox.getMinY();
        boundingBox = BoundingBoxUtil.shift(boundingBox, xOffset, yOffset);
        repaintDecorators = true;
        textBox.setBounds(BoundingBoxUtil.shrinkAll(boundingBox, margin));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setForegroundColor(DesktopColor foregroundColor) {
//        repaintForKeystroke = true;
        textBox.setTextColor(foregroundColor);
    }

    @Override
    public final DesktopColor getForegroundColor() {
        return textBox.getTextColor();
    }

    @Override
    public void setBackgroundColor(DesktopColor backgroundColor) {
//        repaintForKeystroke = true;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public final DesktopColor getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void keyPressed(int keyCode) {
        textBox.addKeyCode(keyCode);
        repaintForKeystroke = true;
        desktop.repaint();
    }
}
