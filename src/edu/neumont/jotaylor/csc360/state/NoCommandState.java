package edu.neumont.jotaylor.csc360.state;

import edu.neumont.jotaylor.csc360.mvc.Controller;
import edu.neumont.jotaylor.csc360.util.KeyConstants;

public class NoCommandState implements State{
    private final Controller controller;
    public NoCommandState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void commandKeyPressed() {
        controller.setCommandState();
    }

    @Override
    public void otherKeyPressed(int keyCode) {
        controller.processNormalKey(keyCode);
    }

    @Override
    public void mysteryKey() {
        controller.processNormalKey(KeyConstants.MYSTERY_KEY);
    }

    @Override
    public void redo() {
        controller.processNormalKey(KeyConstants.REDO);
    }

    @Override
    public void undo() {
        controller.processNormalKey(KeyConstants.UNDO);
    }

    @Override
    public void wrap() {
        controller.processNormalKey(KeyConstants.WRAP);
    }

    @Override
    public void enable() {
        controller.processNormalKey(KeyConstants.ENABLE);
    }

    @Override
    public void disable() {
        controller.processNormalKey(KeyConstants.DISABLE);
    }
}
