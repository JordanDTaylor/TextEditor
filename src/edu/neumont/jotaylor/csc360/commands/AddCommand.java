package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class AddCommand extends Command{

    char charToAdd;
    public AddCommand(ITextModel receiver, char toAdd) {
        super(receiver, true);
        charToAdd = toAdd;
    }

    @Override
    public void execute() {
        receiver.add(charToAdd);
    }

    @Override
    public void undo() {
        receiver.backspace();
    }
}
