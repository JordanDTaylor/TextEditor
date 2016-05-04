package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public class HardReturnCommand extends Command{

    public HardReturnCommand(ITextModel receiver) {
        super(receiver, true);
    }

    @Override
    public void execute() {
        receiver.hardReturn();
    }

    @Override
    public void undo() {
        receiver.backspace();
    }
}
