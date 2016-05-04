package edu.neumont.jotaylor.csc360.strategy;

import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public interface TextMapper {
    char[][] getMap(int numRows, int numCols, ITextModel model);
    Point getCursorLocation();
}
