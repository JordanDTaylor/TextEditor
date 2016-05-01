package edu.neumont.jotaylor.csc360.mvc;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBuffer implements ITextModel {
    private List<Integer> right;
    private List<Integer> left;
    private List<ITextView> viewList;

    public KeyboardBuffer() {
        right = new ArrayList<>();
        left = new ArrayList<>();
        viewList = new ArrayList<ITextView>();
    }

    public void moveLeft(){
        if(!left.isEmpty()){
            right.add(left.remove(left.size()-1));
            notifyViews();
        }
    }

    public void moveRight(){
        if(!right.isEmpty()){
            left.add(right.remove(right.size() -1));
            notifyViews();
        }
    }

    private void notifyViews() {
        for (ITextView view : viewList) {
            view.update(this);
        }
    }
}
