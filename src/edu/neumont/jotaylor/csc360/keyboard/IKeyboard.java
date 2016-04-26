package edu.neumont.jotaylor.csc360.keyboard;

public interface IKeyboard {
    void register(Observer observer);

    void deregister(Observer observer);

    public interface Observer{
        void keyPressed(int keyCode);
    }
}
