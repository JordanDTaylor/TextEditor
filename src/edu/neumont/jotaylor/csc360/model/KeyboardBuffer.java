package edu.neumont.jotaylor.csc360.model;

import java.util.Deque;
import java.util.LinkedList;

public class KeyboardBuffer {
    private Deque<Integer> right;
    private Deque<Integer> left;

    public KeyboardBuffer() {
        this.right = new LinkedList<>();
        this.left = new LinkedList<>();
    }
    boolean moveLeft(){
        if(!left.isEmpty()){
            right.add(left.removeFirst());
            return true;
        }
        return false;
    }

    boolean moveRight(){
        if(!right.isEmpty()){
            left.add(right.removeFirst());
            return true;
        }
        return false;
    }

}
