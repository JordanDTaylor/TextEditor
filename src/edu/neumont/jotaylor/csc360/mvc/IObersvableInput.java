package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.jotaylor.csc360.keyboard.IKeyboard;

public interface IObersvableInput{
    void register(IInputObserver observer);
    void deregister(IInputObserver observer);
}
