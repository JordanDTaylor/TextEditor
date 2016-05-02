package edu.neumont.jotaylor.csc360.keyboard;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.mvc.IInputObserver;
import edu.neumont.jotaylor.csc360.mvc.IObersvableInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jotaylor on 3/30/2016.
 */
public class Keyboard implements IKeyboard, Runnable {
    private Desktop desktop;
    private List<IInputObserver> observers;

    public Keyboard(Desktop desktop) {
        observers = new ArrayList<>();
        this.desktop = desktop;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true){
            if(desktop.hasKeysQueued())
                keyPressed(desktop.getKeyCode());
            else
                Thread.yield();
        }
    }

    protected void keyPressed(int keyCode) {
        for (IInputObserver observer : observers) {
            observer.keyPressed(keyCode);
        }
    }

    @Override
    public void register(IInputObserver observer) {
        if(observers.contains(observer)){
            throw new RuntimeException("Observable already registered");
        }else{
            this.observers.add(observer);
        }
    }


    @Override
    public void deregister(IInputObserver observer) {
        if(this.observers.contains(observer)){
            this.observers.remove(observer);
        }
    }
}
