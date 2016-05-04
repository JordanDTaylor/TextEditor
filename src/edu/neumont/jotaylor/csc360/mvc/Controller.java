package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.commands.*;
import edu.neumont.jotaylor.csc360.util.Logger;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentSkipListSet;

public class Controller implements IInputObserver{

    private static final int SINGLE_QUOTE = 222;
    private static final int BACKSPACE = 8;
    private static final int DELETE = 127;
    private static final int HARD_RETURN = 10;

    private static final int UNDO = (int)'-';
    private static final int REDO = (int)'+';

    private static final int ENABLE_WORD_WRAP = (int)'<';
    private static final int DISABLE_WORD_WRAP = (int)'>';

    private static final int LEFT_ARROW = 17;
    private static final int UP_ARROW = 18;
    private static final int RIGHT_ARROW = 19;
    private static final int DOWN_ARROW = 20;

    private final ITextModel model;
    private final ITextView view;

    private Deque<Command> history;
    private Deque<Command> redoHistory;

    public Controller(ITextModel model, ITextView view) {
        this.model = model;
        this.view = view;

        view.register(this);
        model.register(view);

        history = new LinkedList<>();
        redoHistory = new LinkedList<>();
    }

    @Override
    public void keyPressed(int keyCode) {
        Logger.log(this.getClass().getSimpleName(), "keyCode: " + keyCode + " received." );

//        System.out.println(keyCode + ":" + (char)keyCode);

        Command command=null;

        switch (keyCode){
            case SINGLE_QUOTE:
                command = new AddCommand(model, '\'');
                break;

            case BACKSPACE:
                command = new BackspaceCommand(model);
                break;

            case DELETE:
                command = new DeleteCommand(model);
                break;

            case HARD_RETURN:
                command = new HardReturnCommand(model);
                break;

            case LEFT_ARROW:
                command = new MoveLeftCommand(model);
                break;

            case RIGHT_ARROW:
                command = new MoveRightCommand(model);
                break;

            case UP_ARROW:
//                model.moveUp();
//                model.add((char)0x25B4);
                break;

            case DOWN_ARROW:
//                model.moveDown();
//                model.add((char)0x25BE);
                break;

            case UNDO:
                undo();
                break;

            case REDO:
                redo();
                break;

            case ENABLE_WORD_WRAP:
                System.out.println("enable word wrap");

                view.enableWordWrap();
                model.triggerUpdate();
                break;

            case DISABLE_WORD_WRAP:
                System.out.println("disable word wrap");

                view.disableWordWrap();
                model.triggerUpdate();
                break;

            default:
                command = new AddCommand(model, (char) keyCode);
                break;
        }

        if(command!=null){
            command.execute();
            redoHistory.clear();
            history.add(command);
        }
    }


    private void undo() {
        if(!history.isEmpty()){
            do{
                Command command = history.pollLast();
                command.undo();
                redoHistory.addLast(command);
            }while(!history.isEmpty() && !history.peekLast().isSignificant());
        }
    }


    private void redo() {
        if(!redoHistory.isEmpty()) {
            do {
                Command command = redoHistory.pollLast();
                command.execute();
                history.addLast(command);
            } while (!redoHistory.isEmpty() && !redoHistory.peekLast().isSignificant());
        }
    }
}

