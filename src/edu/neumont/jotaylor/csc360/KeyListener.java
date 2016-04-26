package edu.neumont.jotaylor.csc360;

import edu.neumont.csc415.Desktop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jotaylor on 3/30/2016.
 */
public class KeyListener implements Runnable {
    private Desktop desktop;
    private List<KeyListener.Observer> observers;

    public KeyListener(Desktop desktop) {
        observers = new ArrayList<>();

        this.desktop = desktop;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while(true){
            if(desktop.hasKeysQueued()){
                notifyObservers((char) desktop.getKeyCode());
            }
        }
    }


    protected void notifyObservers(int keyCode) {
        for (KeyListener.Observer observer : observers) {
            observer.keyPressed(keyCode);
        }
    }

    public void register(KeyListener.Observer observer){
        if(observers.contains(observer)){
            throw new RuntimeException("Observable already registered");
        }else{
            this.observers.add(observer);
        }
    }

    public void deregister(KeyListener.Observer observer){
        if(this.observers.contains(observer)){
            this.observers.remove(observer);
        }
    }

    public interface Observer{
        void keyPressed(int keyCode);
    }
}
