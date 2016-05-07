package edu.neumont.jotaylor.csc360.state;

import edu.neumont.jotaylor.csc360.mvc.Controller;
import edu.neumont.jotaylor.csc360.util.KeyConstants;

public class CommandState implements State{

    private final Controller controller;
    public CommandState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void commandKeyPressed() {
        //do nothing, we are already in the command state.
    }

    @Override
    public void mysteryKey() {
        controller.setNoCommandState();
        controller.mysteryKeyCommand();
    }

    @Override
    public void redo() {
        controller.setNoCommandState();
        controller.redo();
    }

    @Override
    public void undo() {
        controller.setNoCommandState();
        controller.undo();
    }

    @Override
    public void wrap() {
        controller.setCommandWState();
    }

    @Override
    public void enable() {
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.ENABLE);
    }

    @Override
    public void disable() {
        controller.setNoCommandState();
        controller.processNormalKey(KeyConstants.DISABLE);
    }

    @Override
    public void otherKeyPressed(int keyCode) {
        controller.setNoCommandState();
        controller.processNormalKey(keyCode);
    }
}
