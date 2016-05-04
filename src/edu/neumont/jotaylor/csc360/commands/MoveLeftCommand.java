package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class MoveLeftCommand extends Command{

    public MoveLeftCommand(ITextModel receiver) {
        super(receiver, false);
    }

    @Override
    public void execute() {
        receiver.moveLeft();
    }

    @Override
    public void undo() {
        receiver.moveRight();
    }
}
