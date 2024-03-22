package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 10;     // Display 10 lines
    private AList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {
        //ADD CODE FOR THE CONSTRUCTOR
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>        
        lines = new AList[LINES]; //create A list of size LINES
        for (int i = 0; i < LINES; i++) {
            lines[i] = new AList<Wordlet>(); //create a new A list of type wordlet for each line
        }
        currentLine = 0; //set current position to 0
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        //ADD CODE HERE TO ADD A WORDLET TO THE CURRENT LINE

//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>        
       lines[currentLine].add(w); //adds wordlet to current line
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     *
     */
    public void nextLine() {
        //ADD CODE TO HANDLE THE NEXT LINE
//>>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>        
    currentLine++; //increase current line
        if (currentLine >= LINES) { //if we are at end of array
            for(int i = 0 ; i < LINES-1; i++){
                lines[i] = lines[i + 1];
            }
            lines[LINES-1] = new AList<>();
            currentLine = Math.min(currentLine + 1, LINES -1);
        }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }

      
    public int getCurrentLine(){
        return currentLine;
    }
    
    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
