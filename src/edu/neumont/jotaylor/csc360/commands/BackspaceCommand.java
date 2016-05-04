package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class BackspaceCommand extends Command{

    char charDeleted;
    public BackspaceCommand(ITextModel receiver) {
        super(receiver, true);
    }

    @Override
    public void execute() {
        charDeleted = receiver.backspace();
    }

    @Override
    public void undo() {
        receiver.add(charDeleted);
    }
}
