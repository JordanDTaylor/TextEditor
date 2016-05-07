package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.jotaylor.csc360.commands.*;
import edu.neumont.jotaylor.csc360.state.CommandState;
import edu.neumont.jotaylor.csc360.state.CommandWState;
import edu.neumont.jotaylor.csc360.state.NoCommandState;
import edu.neumont.jotaylor.csc360.state.State;
import edu.neumont.jotaylor.csc360.util.Logger;
import edu.neumont.jotaylor.csc360.util.RandomNumberGenerator;
import static edu.neumont.jotaylor.csc360.util.KeyConstants.*;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Controller implements IInputObserver{


    private State noCommandState;
    private State commandState;
    private State commandWState;

    private State state;

    private final ITextModel model;
    private final ITextView view;

    private final Deque<Command> history;
    private final Deque<Command> redoHistory;
    private final Deque<Command> commandQueue;

    public Controller(ITextModel model, ITextView view) {
        this.model = model;
        this.view = view;

        view.register(this);
        model.register(view);

        history = new LinkedList<>();
        redoHistory = new LinkedList<>();
        commandQueue = new LinkedList<>();

        noCommandState = new NoCommandState(this);
        commandState = new CommandState(this);
        commandWState = new CommandWState(this);

        state = noCommandState;
    }

    @Override
    public void keyPressed(int keyCode) {
        Logger.log(this.getClass().getSimpleName(), "keyCode: " + keyCode + " received." );

        System.out.println(keyCode + ":" + (char)keyCode);

        switch (keyCode){

            case COMMAND_KEY:
                state.commandKeyPressed();
                break;

            case WRAP:
                state.wrap();
                break;

            case ENABLE:
                state.enable();
                break;

            case DISABLE:
                state.disable();
                break;

            case UNDO:
                state.undo();
                break;

            case REDO:
                state.redo();
                break;

            case MYSTERY_KEY:
                state.mysteryKey();
                break;

            default:
                state.otherKeyPressed(keyCode);
                break;
        }
        if(!commandQueue.isEmpty())
            executeCommands();
    }

    public void processNormalKey(int keyCode){
        switch (keyCode){

            case SINGLE_QUOTE:
                queueCommand(new AddCommand(model, '\''));
                break;

            case BACKSPACE:
                queueCommand(new BackspaceCommand(model));
                break;

            case DELETE:
                queueCommand(new DeleteCommand(model));
                break;

            case HARD_RETURN:
                queueCommand(new HardReturnCommand(model));
                break;

            case LEFT_ARROW:
                queueCommand( new MoveLeftCommand(model));
                break;

            case RIGHT_ARROW:
                queueCommand( new MoveRightCommand(model));
                break;

            default:
                queueCommand(new AddCommand(model, (char) keyCode));
                break;
        }
    }


    private void queueCommand(Command command) {
        commandQueue.addLast(command);
    }

    private void executeCommands(){
        redoHistory.clear();

        while(!commandQueue.isEmpty()){
            Command command = commandQueue.pollFirst();

            assert(command!=null);
            command.execute();
            history.addLast(command);
        }
        assert (commandQueue.isEmpty());
    }

    public void mysteryKeyCommand() {
        RandomNumberGenerator random = RandomNumberGenerator.getInstance();
        int randomKeyCode = random.nextInt(mappedUnicodeCodePoints)+33;
        commandQueue.addLast(new AddCommand(model, (char)randomKeyCode));
    }

    public void disableWrap() {
        view.disableWordWrap();
        model.triggerUpdate();
    }

    public void enableWrap() {
        view.enableWordWrap();
        model.triggerUpdate();
    }

    private final int mappedUnicodeCodePoints = 126 -33;

    public void undo() {
        if(!history.isEmpty()){
            do{
                Command command = history.pollLast();
                command.undo();
                redoHistory.addLast(command);
            }while(!history.isEmpty() && !history.peekLast().isSignificant());
        }
    }

    public void redo() {
        if(!redoHistory.isEmpty()) {
            do {
                Command command = redoHistory.pollLast();
                command.execute();
                history.addLast(command);
            } while (!redoHistory.isEmpty() && !redoHistory.peekLast().isSignificant());
        }
    }

    public void setNoCommandState() {
        state = noCommandState;
    }

    public void setCommandState() {
        state = commandState;
    }

    public void setCommandWState() {
        state = commandWState;
    }

}

