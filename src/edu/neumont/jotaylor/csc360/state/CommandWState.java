package edu.neumont.jotaylor.csc360.state;

import edu.neumont.jotaylor.csc360.mvc.Controller;
import edu.neumont.jotaylor.csc360.util.KeyConstants;

public class CommandWState implements State{
    private final Controller controller;
    public CommandWState(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void commandKeyPressed() {
        controller.setCommandState();
    }

    @Override
    public void mysteryKey() {
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.MYSTERY_KEY);
    }

    @Override
    public void redo() {
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.REDO);

    }

    @Override
    public void undo() {
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.UNDO);
    }

    @Override
    public void wrap() {//Already in this state, so just process the key normally.
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.WRAP);
    }

    @Override
    public void enable() {
        controller.setNoCommandState();
        controller.enableWrap();
    }

    @Override
    public void disable() {
        controller.setNoCommandState();
        controller.disableWrap();
    }

    @Override
    public void otherKeyPressed(int keyCode) {
        controller.setNoCommandState();
        controller.processNormalKey(keyCode);
    }
}
