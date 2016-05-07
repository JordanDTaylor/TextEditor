package edu.neumont.jotaylor.csc360.strategy;

import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.mvc.ITextModel;

import java.util.Iterator;

public class NonWrappingTextMapper implements TextMapper {
    private char LINE_FEED = (char) 10;

    Point cursorLocation = new Point(0,0);
    @Override
    public char[][] getMap(int numRows, int numCols, ITextModel model) {
        int cursorOffset = model.getCursorOffset() + 1;

        char[][] text = new char[numRows][numCols];
        Iterator<Character> iterator = model.iterator();

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numCols; column++) {

                if (iterator.hasNext()) {
                    char next = iterator.next();
                    if (next == LINE_FEED && column < numCols) {
                        row++;
                        column = -1;
                    } else {
                        text[row][column] = next;
                    }
                }
                if(--cursorOffset == 0)
                    cursorLocation = new Point(column, row);
            }
        }
        return text;
    }

    @Override
    public Point getCursorLocation() {
        return cursorLocation;
    }
}
