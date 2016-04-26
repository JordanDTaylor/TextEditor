package edu.neumont.jotaylor.csc360.keyboard;

public interface IKeyboard {
    void register(Observer observer);

    void deregister(Observer observer);

    interface Observer{
        void keyPressed(int keyCode);
    }
}
