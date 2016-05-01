package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.Desktop;
import edu.neumont.csc415.DesktopColor;
import edu.neumont.csc415.DesktopGraphics;
import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.mvc.ITextModel;
import edu.neumont.jotaylor.csc360.mvc.ITextView;
import edu.neumont.jotaylor.csc360.util.Cursor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TextBox implements ITextView {
    private static final int KEY_CODE_SINGLE_QUOTE = 222;
    private static final int KEY_CODE_BACKSPACE = 8;
    private static final int KEY_CODE_NEW_LINE = 10;

    private Deque<Integer> keyCodes;

    private BoundingBox bounds;
    private Desktop desktop;
    private Cursor cursor;
    private DesktopColor textColor;

    public TextBox(Desktop desktop, BoundingBox bounds, DesktopColor foregroundColor) {
        this.desktop = desktop;
        setBounds(bounds);
        textColor = foregroundColor;
        keyCodes = new ConcurrentLinkedDeque<>();
    }

    public void addKeyCode(int keyCode){
        if(keyCode == KEY_CODE_BACKSPACE && keyCodes.size() > 0)
            keyCodes.removeLast();
        else
            keyCodes.addLast(keyCode);
    }

    void repaint(DesktopGraphics desktopGraphics ){
        cursor.home();

        for (int keyCode : keyCodes){
            char c = (char) keyCode;

            if(keyCode == KEY_CODE_NEW_LINE)
                lineFeed();

            else if(keyCode == KEY_CODE_SINGLE_QUOTE)
                c = '\'';

            if(fitsOnThisLine(c))
                desktopGraphics.drawChar(c, cursor.getPosition(), textColor);
            else {
                advanceCursor(c);
                desktopGraphics.drawChar(c, cursor.getPosition(), textColor);
            }
            advanceCursor(c);
        }
    }

    private boolean fitsOnThisLine(char c) {
        int currentX = cursor.getPosition().getX();
        int charWidth = + desktop.getCharWidth(c);
        int maxX = bounds.getMaxX();
        return currentX + charWidth < maxX;
    }

    public DesktopColor getTextColor() {
        return textColor;
    }

    public void setTextColor(DesktopColor textColor) {
        this.textColor = textColor;
    }

    private void advanceCursor(char lastChar){
        int charWidth = desktop.getCharWidth(lastChar);

        int x = cursor.getPosition().getX() + charWidth;
        int y = cursor.getPosition().getY();

        int yLimit = bounds.getMaxY() - desktop.getCharHeight();

        if(!fitsOnThisLine(lastChar)){ //reached the right edge of the window
            if(y >= yLimit){ //reached the bottom of the decorator
                cursor.home();
            }else {
                lineFeed();
            }
        }else{
            cursor.setPosition(new Point(x, y));
        }
    }


    private void lineFeed(){
        int yLimit = bounds.getMaxY() - desktop.getCharHeight();

        if(cursor.getPosition().getY() >= yLimit)
            cursor.home();

        else
            cursor.lineFeed(desktop.getCharHeight());
    }

    public BoundingBox getBounds() {
        return bounds;
    }

    public void setBounds(BoundingBox bounds) {
        this.bounds = bounds;
        cursor = new Cursor(bounds.getMinX(), bounds.getMinY() + desktop.getCharHeight());
    }

    @Override
    public void update(ITextModel model) {

    }
}
