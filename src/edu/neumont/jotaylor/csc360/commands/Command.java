package edu.neumont.jotaylor.csc360.commands;

import edu.neumont.jotaylor.csc360.mvc.ITextModel;

public abstract class Command{
    public abstract void execute();
    public abstract void undo();

    private boolean isSignificant;

    public boolean isSignificant(){
        return isSignificant;
    }

    protected ITextModel receiver;

    public Command(ITextModel receiver, boolean isSignificant) {
        this.receiver = receiver;
        this.isSignificant = isSignificant;
    }
}

