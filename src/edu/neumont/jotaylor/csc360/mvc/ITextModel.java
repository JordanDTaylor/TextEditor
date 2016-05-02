package edu.neumont.jotaylor.csc360.mvc;

public interface ITextModel{
    void register(ITextModelObserver observer);
    void deregister(ITextModelObserver observer);

    void add(char c);
    void backspace();
    void delete();
    void hardReturn();
    void moveLeft();
    void moveRight();

    char[][] fitText(int numRows, int numCols);
}

