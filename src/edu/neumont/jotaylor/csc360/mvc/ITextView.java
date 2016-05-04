package edu.neumont.jotaylor.csc360.mvc;

public interface ITextView extends IInputObserver, IObersvableInput, ITextModelObserver{
    void enableWordWrap();

    void disableWordWrap();
}
