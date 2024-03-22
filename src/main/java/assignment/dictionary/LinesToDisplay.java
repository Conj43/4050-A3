package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 20;     // Display 20 lines
    private AList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {

        lines = new AList[LINES]; //create A list of size LINES
        for (int i = 0; i < LINES; i++) {
            lines[i] = new AList<Wordlet>(); //create a new A list of type wordlet for each line
        }
        currentLine = 0; //set current position to 0

    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {

       lines[currentLine].add(w); //adds wordlet to current line

    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     *
     */
    public void nextLine() {

    currentLine++; //increase current line
        if (currentLine >= LINES) { //if we are at end of array
            for(int i = 0 ; i < LINES-1; i++){ //shift lines
                lines[i] = lines[i + 1];
            }
            lines[LINES-1] = new AList<>(); //empty bottom
            currentLine = Math.min(currentLine + 1, LINES -1); //reset so we dont go out of bounds
        }

    }

      
    public int getCurrentLine(){
        return currentLine;
    }
    
    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
