package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.util.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TextBuffer implements ITextModel {
    private char LINE_FEED = 10;

    private List<Character> right;
    private List<Character> left;

    public TextBuffer() {
        right = new ArrayList<>();
        left = new ArrayList<>();
        observers = new ArrayList<>();
    }

    @Override
    public Point getCursorLocation(int numRows, int numCols) {
        ListIterator<Character> leftItr = left.listIterator();

        Point cursorLocaiton = new Point(0,0);
        try {
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < numCols; column++) {
                    char next;

                    if (leftItr.hasNext())
                        next = leftItr.next();
                    else {
                        return new Point(column, row);
                    }
                    if (next == LINE_FEED && column < numCols) {
                        row++;
                        column = -1;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return cursorLocaiton;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Commands
    //
    @Override
    public void add(char c) {
        logCommand("add " + c);
        left.add(c);
        notifyOfChange();
    }

    @Override
    public void moveLeft(){
        logCommand("Move Left");

        if(!left.isEmpty()){
            right.add(right.size(), left.remove(left.size()-1));
            notifyOfChange();
        }
    }

    @Override
    public void moveRight(){
        logCommand("Move Right");

        if(!right.isEmpty()){
            left.add(right.remove(right.size() -1));
            notifyOfChange();
        }
    }

    @Override
    public char delete(){
        logCommand("Delete");
        char removed = 0;

        if(!right.isEmpty()){
            int tail = right.size() - 1;
            removed = right.remove(tail);
            notifyOfChange();
        }

        return removed;
    }

    @Override
    public void hardReturn() {
        logCommand("Hard Return");

        left.add(LINE_FEED);
        notifyOfChange();
    }

    @Override
    public char backspace(){
        logCommand("Backspace");
        char removed = 0;

        if(!left.isEmpty()){
            int tail = left.size() - 1;
            removed = left.remove(tail);
            notifyOfChange();
        }

        return removed;
    }
    // end Commands
    /////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    //Observable
    //
    private List<ITextModelObserver> observers;

    @Override
    public void register(ITextModelObserver observer) {

        if(observers.contains(observer)){
            throw new RuntimeException("Observable already registered");

        }else{
            this.observers.add(observer);
            Logger.log(this.getClass().getSimpleName(), "Observer \""+ observer.getClass().getSimpleName() + "\" registered" );
        }
    }

    @Override
    public void deregister(ITextModelObserver observer) {
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    private void notifyOfChange() {
        Logger.log(this.getClass().getSimpleName(), "notifying " + observers.size() + " observers that the model updated.");
        for (ITextModelObserver observer : observers) {
            observer.onModelChange(this);
        }
    }
    // end observable
    /////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////
    //Iterable
    //
    @Override
    public Iterator<Character> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Character>{

        private int position = 0;

        @Override
        public boolean hasNext() {
            int size = left.size() + right.size();
            return position < size;
        }

        @Override
        public Character next() {
            char next;
            if (position < left.size()) {
                next = left.get(position);

            } else {
                int rightPosition = right.size() - (position - left.size()) - 1;
                next = right.get(rightPosition);
            }

            position++;
            return next;
        }
    }
    //
    //end iterable
    /////////////////////////////////////

    private void logCommand(String command){
        Logger.log(getClass().getSimpleName(), command + " command received");
    }

}
