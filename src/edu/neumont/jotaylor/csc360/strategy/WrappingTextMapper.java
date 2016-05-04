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
        char[][] characters = new char[numRows][numCols];


        Iterator<Character> iterator = model.iterator();
        int numCells = numCols * numRows;
        int letterCount;

        ArrayList<Character> nextLine;
        for (int row = 0; row < numRows; row++) {
            int column = 0;

            do {
                ArrayList<Character> word = getNextWord(iterator);
                if(word.size() > numCols - column && column) {
                    row++;
                    column = 0;

                }


                nextLine = new ArrayList<>(numCols);
                letterCount = 0;

            }while(iterator.hasNext() && letterCount < numCols)


            char[] nextRow = new char[numCols];

            for (column = 0; column < numCols; column++) {
                    else lastSpace = 0;

                    if (next == LINE_FEED && column < numCols) {
                        row++;
                        column = -1;
                    } else {
                        if(lastSpace >= 0 && lastSpace < numCols -1){

                        }
                        text[row][column] = next;

                    }
                }
            }
        }
        return text;
    }

    private ArrayList<Character> getNextWord(Iterator<Character> iterator){
        Character next;
        ArrayList<Character> word = new ArrayList<>();
        while(iterator.hasNext()){
            next = iterator.next();
            word.add(next);
            if(!Character.isLetterOrDigit(next))
                break;
        }
        return word;
    }

    @Override
    public Point getCursorLocation() {
        return cursorLocation;
    }
}
