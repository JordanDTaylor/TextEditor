package edu.neumont.jotaylor.csc360.window;

import edu.neumont.csc415.*;
import edu.neumont.jotaylor.csc360.mvc.*;
import edu.neumont.jotaylor.csc360.strategy.NonWrappingTextMapper;
import edu.neumont.jotaylor.csc360.strategy.TextMapper;
import edu.neumont.jotaylor.csc360.strategy.WrappingTextMapper;
import edu.neumont.jotaylor.csc360.util.BoundingBoxUtil;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.util.Points;

import java.util.ArrayList;
import java.util.List;

public class Window implements IWindow, ITextView {

    private String title;
    private TextBox textBox;
    private Desktop desktop;
    private BoundingBox boundingBox;
    private boolean repaintDecorators;
    private boolean repaintForKeystroke;
    private DesktopColor backgroundColor;

    private TextMapper textMapper;

    private final int margin = 5;
    private final int charWidth;
    private final int charHeight;

    private List<IInputObserver> inputObservers;

    public Window(Desktop desktop, int width, int height, int xPosition, int yPosition, String title, DesktopColor backgroundColor, DesktopColor foregroundColor) {
        boundingBox = new BoundingBox(xPosition, yPosition, width, height);

        this.desktop = desktop;

        charHeight = desktop.getCharHeight();
        charWidth = desktop.getCharWidth('W');

        BoundingBox textBounds = transposeForTextBox(boundingBox);

        int numRows = textBounds.getHeight() / charHeight;
        int numCols = textBounds.getWidth() / charWidth;

        textBox = new TextBox(textBounds, foregroundColor, numRows, numCols);
        textMapper = new NonWrappingTextMapper();

        this.backgroundColor = backgroundColor;
        setForegroundColor(foregroundColor);

        this.title = title;

        inputObservers = new ArrayList<>();
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
        textBox.setBounds(transposeForTextBox(boundingBox));
    }

    private BoundingBox transposeForTextBox(BoundingBox box) {
        BoundingBox shrunken = BoundingBoxUtil.shrinkAll(box, margin);
        return BoundingBoxUtil.shiftDown(shrunken, charHeight);
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
        textBox.setTextColor(foregroundColor);
    }

    @Override
    public final DesktopColor getForegroundColor() {
        return textBox.getTextColor();
    }

    @Override
    public void setBackgroundColor(DesktopColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public final DesktopColor getBackgroundColor() {
        return backgroundColor;
    }


    ////////////////////////////////////////////////////////////////////////////
    //Observable
    //
    @Override
    public void register(IInputObserver observer) {
        if (inputObservers.contains(observer)) {
            throw new RuntimeException("Observable already registered");
        } else {
            this.inputObservers.add(observer);
            Logger.log(this.getClass().getSimpleName(), "Observer \"" + observer.getClass().getSimpleName() + "\" registered");
        }
    }

    @Override
    public void deregister(IInputObserver observer) {
        if (inputObservers.contains(observer)) {
            inputObservers.remove(observer);
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        for (IInputObserver observer : inputObservers) {
            Logger.log(this.getClass().getSimpleName(), "keyCode: " + keyCode + " being passed to " + observer.getClass().getSimpleName() + ".");
            observer.keyPressed(keyCode);
        }
    }
    //
    //end Observable
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    //Observer
    @Override
    public void onModelChange(ITextModel model) {
        textBox.updateText(model, textMapper);

        Logger.log("Window", "Model changed");
        desktop.repaint();
    }
    //
    //end Observer
    ////////////////////////////////////////////////////////////////////////////


    private Point cursorLocation = new Point(0, 0);


    ////////////////////////////////////////////////////////////////////////////
    //Strategy
    //
    @Override
    public void enableWordWrap() {
        textMapper = new WrappingTextMapper();

    }

    @Override
    public void disableWordWrap() {
        textMapper = new NonWrappingTextMapper();
    }
    //
    //end Strategy
    ////////////////////////////////////////////////////////////////////////////

    class TextBox {
        public final int numRows;
        public final int numCols;

        private char SPACE = 32;

        private BoundingBox textBounds;
        private DesktopColor textColor;

        private char[][] text;

        public TextBox(BoundingBox bounds, DesktopColor foregroundColor, int numRows, int numCols) {
            setBounds(bounds);

            textColor = foregroundColor;
            this.numCols = numCols;
            this.numRows = numRows;

            text = new char[this.numRows][this.numCols];
        }

        void repaint(DesktopGraphics desktopGraphics) {

            int baseX = textBounds.getMinX();
            int baseY = textBounds.getMinY();

            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < numCols; column++) {

                    char charToDraw = text[row][column];

                    int x = baseX + charWidth * column;
                    int y = baseY + charHeight * row;

                    Point location = new Point(x, y);
                    desktopGraphics.drawChar(charToDraw, location, textColor);
                }
            }
            int cursorX = baseX + charWidth * cursorLocation.getX();
            int cursorY = baseY + charHeight * cursorLocation.getY() + 2;

            Point cursor = new Point(cursorX, cursorY);
            desktopGraphics.fillRectangle(cursor, Points.addToPoint(cursor,charWidth, 1 ), cursorColor);
        }

        private DesktopColor cursorColor = DesktopColor.WHITE;

        public DesktopColor getTextColor() {
            return textColor;
        }

        public void setTextColor(DesktopColor textColor) {
            this.textColor = textColor;
        }

        public void setBounds(BoundingBox bounds) {
            this.textBounds = bounds;
        }

        public void updateText(ITextModel model, TextMapper textMapper) {
            text = textMapper.getMap(numRows, numCols, model);
            cursorLocation = textMapper.getCursorLocation();
        }
    }
}