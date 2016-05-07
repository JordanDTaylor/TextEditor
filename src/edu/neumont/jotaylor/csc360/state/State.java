package edu.neumont.jotaylor.csc360.state;

import edu.neumont.jotaylor.csc360.mvc.Controller;

/**
 * Created by jorda on 5/6/2016.
 */
public interface State {
    void commandKeyPressed();
    void otherKeyPressed(int keyCode);
    void redo();
    void undo();
    void wrap();
    void enable();
    void disable();
    void mysteryKey();

}


