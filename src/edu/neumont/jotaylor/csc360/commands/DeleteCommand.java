package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class DeleteCommand extends Command{

    char charDeleted;
    public DeleteCommand(ITextModel receiver) {
        super(receiver, true);
    }

    @Override
    public void execute() {
        charDeleted = receiver.delete();
    }

    @Override
    public void undo() {
        receiver.add(charDeleted);
        receiver.moveLeft();
    }
}
