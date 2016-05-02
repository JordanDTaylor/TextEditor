package edu.neumont.jotaylor.csc360.mvc;

import edu.neumont.csc415.Desktop;
import edu.neumont.jotaylor.csc360.util.Logger;

public class Controller implements IInputObserver{

    private static final int SINGLE_QUOTE = 222;
    private static final int BACKSPACE = 8;
    private static final int DELETE = 999;
    private static final int HARD_RETURN = 10;

    private static final int LEFT_ARROW = 17;
    private static final int UP_ARROW = 18;
    private static final int RIGHT_ARROW = 19;
    private static final int DOWN_ARROW = 20;

    private final ITextModel model;
//    private final ITextView view;

    public Controller(ITextModel model, ITextView view) {
        this.model = model;
//        this.view = view;
        view.register(this);
        model.register(view);
    }

    @Override
    public void keyPressed(int keyCode) {
        Logger.log(this.getClass().getSimpleName(), "keyCode: " + keyCode + " received." );
        switch (keyCode){
            case SINGLE_QUOTE:
                addToModel('\'');
                break;
            case BACKSPACE:
                model.backspace();
                break;
            case DELETE:
                model.delete();
                break;
            case HARD_RETURN:
                model.hardReturn();
                break;
            case LEFT_ARROW:
                model.moveLeft();
                break;
            case RIGHT_ARROW:
                model.moveRight();
                break;
            default:
                model.add((char) keyCode);
                break;
        }
    }

    private void addToModel(char c){
        model.add(c);
    }
}
