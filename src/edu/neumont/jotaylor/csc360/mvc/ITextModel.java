package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.csc415.Point;

public interface ITextModel extends Iterable<Character>{
    void register(ITextModelObserver observer);
    void deregister(ITextModelObserver observer);

    void add(char c);
    void backspace();
    void delete();
    void hardReturn();
    void moveLeft();
    void moveRight();

    Point getCursorLocation(int numRow, int numCol);
}

