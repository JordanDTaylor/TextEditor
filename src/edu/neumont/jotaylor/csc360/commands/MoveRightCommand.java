package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class MoveRightCommand extends Command{

    public MoveRightCommand(ITextModel receiver) {
        super(receiver, false);
    }

    @Override
    public void execute() {
        receiver.moveRight();
    }

    @Override
    public void undo() {
        receiver.moveLeft();
    }
}
