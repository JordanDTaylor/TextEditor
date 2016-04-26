package edu.neumont.jotaylor.csc360.keyboard;

import edu.neumont.csc415.Desktop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jotaylor on 3/30/2016.
 */
public class Keyboard implements IKeyboard, Runnable {
    private Desktop desktop;
    private List<IKeyboard.Observer> observers;

    public Keyboard(Desktop desktop) {
        observers = new ArrayList<>();

        this.desktop = desktop;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true){
            if(desktop.hasKeysQueued())
                notifyObservers((char) desktop.getKeyCode());
            else
                Thread.yield();
        }
    }


    protected void notifyObservers(int keyCode) {
        for (IKeyboard.Observer observer : observers) {
            observer.keyPressed(keyCode);
        }
    }

    @Override
    public void register(IKeyboard.Observer observer){
        if(observers.contains(observer)){
            throw new RuntimeException("Observable already registered");
        }else{
            this.observers.add(observer);
        }
    }

    @Override
    public void deregister(IKeyboard.Observer observer){
        if(this.observers.contains(observer)){
            this.observers.remove(observer);
        }
    }

}
