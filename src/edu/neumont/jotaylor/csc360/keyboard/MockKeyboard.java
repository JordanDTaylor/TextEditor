package edu.neumont.jotaylor.csc360.keyboard;

import edu.neumont.jotaylor.csc360.mvc.IInputObserver;
import edu.neumont.jotaylor.csc360.mvc.IObersvableInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MockKeyboard implements IKeyboard, Runnable {
    private List<IInputObserver> observers;

    public MockKeyboard() {
        this.observers = new ArrayList<>();
        new Thread(this).start();
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
        observers.remove(observer);
    }

    @Override
    public void run() {
        while(observers.isEmpty()) Thread.yield();

        try {
            sleep(100);
            for (char c : readKeyFile().toCharArray()) {
                note(c);
//                sleep((long)(Math.random()*100) + 10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void note(int keyCode){
        for (IInputObserver o:observers) {
            o.keyPressed(keyCode);
        }
    }
    private String readKeyFile(){
        String line;
        String out = "";
        try(BufferedReader reader = new BufferedReader(new FileReader("keyboard.txt"))) {
            while((line = reader.readLine()) != null){
                out+=line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
