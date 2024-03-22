package assignment.dictionary;

import java.io.*;
import java.util.*;
import javafx.application.Platform;

/**
 * A Thread that contains the application we are going to animate
 *
 */

public class MisSpellActionThread implements Runnable {

    DictionaryController controller;
    private final String textFileName;
    private final String dictionaryFileName;

    private LinesToDisplay myLines;
    private DictionaryInterface<String, String> myDictionary;
    private boolean dictionaryLoaded;

    /**
     * Constructor for objects of class MisspellActionThread
     *
     * @param controller
     */
    public MisSpellActionThread(DictionaryController controller) {
        super();

        this.controller = controller;
        textFileName = "src/main/resources/assignment/dictionary/check.txt";
        dictionaryFileName = "src/main/resources/assignment/dictionary/sampleDictionary.txt";

        myDictionary = new HashedMapAdaptor<String, String>();
        myLines = new LinesToDisplay();
        dictionaryLoaded = false;

    }
//hi
    @Override
    public void run() {

        loadDictionary(dictionaryFileName, myDictionary);


        Platform.runLater(() -> {
            if (dictionaryLoaded) {
               controller.SetMsg("The Dictionary has been loaded"); 
            } else {
               controller.SetMsg("No Dictionary is loaded"); 
            }
        });
        
        checkWords(textFileName, myDictionary);

    }

    /**
     * Load the words into the dictionary.
     *
     * @param theFileName The name of the file holding the words to put in the
     * dictionary.
     * @param theDictionary The dictionary to load.
     */
    public void loadDictionary(String theFileName, DictionaryInterface<String, String> theDictionary) {
        Scanner input;
        try {
// ADD CODE HERE
// >>>>>>>>>>> ADDED CODE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        input = new Scanner(new File(theFileName)); //create scanner to read dictionary file
        while(input.hasNextLine()){
            String word = input.nextLine(); //loop and read each line as a word
            theDictionary.add(word, word); //add to hash table dictionary
        }
        dictionaryLoaded = true;
        input.close(); //close scanner
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         


        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get the words to check, check them, then put Wordlets into myLines. When
     * a single line has been read do an animation step to wait for the user.
     *
     */
    public void checkWords(String theFileName, DictionaryInterface<String, String> theDictionary) {
            //we have punctuation as part of words, and if word is in dictionary, punctuation is blue
        // if not, punctuation attached to word is red
        //we did this because it was not specified how to be implemented
            Scanner input;
            try {

                input = new Scanner(new File(theFileName)); //scanner for reading the file

                while (input.hasNextLine()) //goes through the file
                {
                    String line = input.nextLine();

                    Scanner lineScanner = new Scanner(line); //scanner for reading the lines and dividing them
                    int wordcount = 0;
                    lineScanner.useDelimiter("\\s+"); //divides the line into words  goes by whitespaces
                    while(lineScanner.hasNext()){
                        lineScanner.next();
                        wordcount++;
                    }
                    String[] words = new String[wordcount];
                    lineScanner  = new Scanner(line);
                    lineScanner.useDelimiter("\\s+");
                    int lineIndex = 0;
                    while (lineScanner.hasNext()) {

                        words[lineIndex] = lineScanner.next(); // puts the words into the words array
                        lineIndex++;
                    }

                    lineScanner  = new Scanner(line);
                    lineScanner.useDelimiter("\\s+");
                    for (String word : words) {
                        boolean spelling = checkWord(word, theDictionary); //checks the spelling of the words
                        Wordlet currWord = new Wordlet(word, spelling);
                        myLines.addWordlet(currWord); //adds the wordlet to mylines
                    }
                    showLines(myLines); //display line
                    myLines.nextLine();
                    lineScanner.close();


                }

            } catch (IOException e) {
                System.out.println("There was an error in reading or opening the file: " + theFileName);
                System.out.println(e.getMessage());
            }

    }

    /**
     * Check the spelling of a single word.
     *
     */
    public boolean checkWord(String word, DictionaryInterface<String, String> theDictionary) {
        boolean result = false;


        String wordWithoutPunctuation = word.replaceAll("\\p{Punct}", ""); //exclude punctuation


        result = theDictionary.contains(wordWithoutPunctuation.toLowerCase()); //check word without punctuation

        return result;

    }

    private void showLines(LinesToDisplay lines) {
        try {
            Thread.sleep(500);
            Platform.runLater(() -> {
                if (myLines != null) {
                    controller.UpdateView(lines);
                }
            });
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

} // end class MisspellActionThread

