package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.KeyListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jotaylor on 3/30/2016.
 */
public class Window implements IWindow, KeyListener.Observer {
    private static final int KEY_CODE_NEW_LINE = 10;
    private static final int KEY_CODE_SINGLE_QUOTE = 222;
    private static final int DEFAULT_HEIGHT = 400;
    private static final int DEFAULT_WIDTH = 400;
    private static final int KEY_CODE_BACKSPACE = 8;
    public static final int BOTTOM_MARGIN = 7;

    private static final int X_OFFSET = 0;
    private Desktop desktop;
    private List<Integer> keyCodes;

    private Point location;
    private int height;
    private int width;

    private DesktopColor backgroundColor = DesktopColor.WHITE;
    private DesktopColor textColor = DesktopColor.BLACK;

    private boolean repaintForKeystroke;

    private Cursor cursor;

    private String title;
    private boolean repaintDecorators;

//    public Window(Desktop desktop){
//        this(desktop, DEFAULT_WIDTH, DEFAULT_HEIGHT);
//    }

//    public Window(Desktop desktop, int width, int height){
//        this(desktop, width, height, "");
//    }

//    public Window(Desktop desktop, int width, int height, String title){
//        this(desktop, width, height, 50, 100);
//    }

//    public Window(Desktop desktop, int width, int height, int xPosition, int yPosition) {
//        this(desktop, width, height,xPosition, yPosition, "");
//    }

    public Window(Desktop desktop, int width, int height, int xPosition, int yPosition, String title) {
        this.height = height;
        this.width = width;
        this.location = new Point(xPosition, yPosition);

        this.title = title;

        this.desktop = desktop;
        this.cursor = new Cursor();

        keyCodes = new ArrayList<>();
    }

    @Override
    public void paint(DesktopGraphics desktopGraphics) {

        drawBackground(desktopGraphics);
        cursor.home();

        for (int keyCode:keyCodes){
            char c = (char) keyCode;

            if(keyCode == KEY_CODE_NEW_LINE)
                newLine();
            else if(keyCode == KEY_CODE_SINGLE_QUOTE)
                c = '\'';

            drawCharInWindow(c, desktopGraphics, cursor.getPosition());
            advanceCursor(c);
        }
        repaintDecorators = !repaintForKeystroke;
        repaintForKeystroke = false;
    }

    private void drawBackground(DesktopGraphics desktopGraphics) {
        desktopGraphics.fillRectangle(getTopLeft(), getBottomRight(), backgroundColor);
    }

    private void drawCharInWindow(Character c, DesktopGraphics desktopGraphics, Point relativeLocation){
        if(relativeLocation == cursor.getHome())
            drawBackground(desktopGraphics);

        Point position = translateCursorPosition(relativeLocation);
        desktopGraphics.drawChar(c, position, textColor);
    }

    private void advanceCursor(char lastChar){
        int charWidth = desktop.getCharWidth(lastChar);

        int x = cursor.getPosition().getX() + charWidth + X_OFFSET;
        int y = cursor.getPosition().getY();

        int xLimit = this.width - charWidth;
        int yLimit = this.height - desktop.getCharHeight() - BOTTOM_MARGIN;

        if(x > xLimit){ //reached the right edge of the window
            if(y >= yLimit){ //reached the bottom of the window
                cursor.home();
            }else {
                newLine();
            }
        }else{
            cursor.setPosition(new Point(x, y));
        }
    }

    @Override
    public Point getTopLeft(){
        return new Point(location.getX(), location.getY());
    }

    @Override
    public Point getBottomRight(){
        return new Point(location.getX() + width, location.getY() + height);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Desktop getDesktop() {
        return desktop;
    }

    @Override
    public boolean decoratorsShouldRepaint() {
        return repaintDecorators;
    }

    private void newLine(){
        int yLimit = this.height - desktop.getCharHeight() - BOTTOM_MARGIN;

        if(cursor.getPosition().getY() >= yLimit)
            cursor.home();
        else
            cursor.lineFeed(desktop.getCharHeight());
    }

    private Point translateCursorPosition(Point relativePosition){
        int x = this.location.getX() + relativePosition.getX();
        int y = this.location.getY() + relativePosition.getY() + desktop.getCharHeight();

        return new Point(x, y);
    }

    public void addKeyCode(int keyCode) {
        keyCodes.add(keyCode);
    }

    public void removeLast() {
        if(keyCodes.size() > 0)
            keyCodes.remove(keyCodes.size() - 1);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KEY_CODE_BACKSPACE)
            removeLast();
        else
            addKeyCode(keyCode);

        repaintForKeystroke = true;
        desktop.repaint();
    }
}
