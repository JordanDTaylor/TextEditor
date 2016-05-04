package edu.neumont.jotaylor.csc360.strategy;

import edu.neumont.csc415.Point;
import edu.neumont.jotaylor.csc360.mvc.ITextModel;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class WrappingTextMapper implements TextMapper {
    private char LINE_FEED = (char) 10;

    Point cursorLocation = new Point(0,0);

    @Override
    public char[][] getMap(int numRows, int numCols, ITextModel model) {

        char[][] text = new char[numRows][numCols];
        Iterator<Character> iterator = model.iterator();

        int column =0;
        int row = 0;

        while(iterator.hasNext()){

            ArrayList<Character> word = getNextWord(iterator);
            int spaceLeft = numCols - column;

            if(word.size() > spaceLeft){
                column = 0;
                row++;
            }

            if(column<=numCols){
                copyWordTo(word, text, row, column);
                column += word.size();

                if(column < numCols) {
                    text[row][column] = ' ';
                    column++;
                }
            }
        }
        return text;
    }

    private void copyWordTo(ArrayList<Character> word, char[][] text, int row, int column) {
        for (char c : word) {
            text[row][column] = c;
            column++;
        }
    }

    private ArrayList<Character> getNextWord(Iterator<Character> iterator){
        char next;
        ArrayList<Character> word = new ArrayList<>();

        while(iterator.hasNext()){
            next = iterator.next();

            if(next == ' ')
                break;

            word.add(next);

            if(next == LINE_FEED)
                break;
        }
        return word;
    }

    @Override
    public Point getCursorLocation() {
        return cursorLocation;
    }
}
