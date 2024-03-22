package assignment.dictionary;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Ouda
 */

public class DictionaryController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private VBox view;
    @FXML
    Button checkSpellingBtn;

    @FXML
    public void checkSpellingBtnClicked() {
        new Thread(new MisSpellActionThread(this)).start();
    }

    /**
     * Draw a representation of the lines display.
     *
     */
    public void UpdateView(LinesToDisplay lines) {
        System.out.println("called");
            String family = "Helvetica";
            double size = 20;
            view.getChildren().clear();
        System.out.println("current line = " + lines.getCurrentLine());
            for (int i = 0; i < lines.getCurrentLine(); i++) {
                TextFlow newLine = new TextFlow();
                newLine.getChildren().add(new Text(""));
                Iterator<Wordlet> iter = lines.getLines()[i].getIterator();
                System.out.println("before loop");
                while (iter.hasNext()) {
                    System.out.println("in loop");
                    Wordlet word = iter.next();
                    Text checkedWord = new Text(word.getWord());
                    checkedWord.setFont(Font.font(family, size));
                    if (word.isSpelledCorrectly()) {
                        checkedWord.setFill(Color.BLUE);
                    } else {
                        checkedWord.setFill(Color.RED);
                    }
                    newLine.getChildren().add(checkedWord);
                }
                view.getChildren().add(newLine);
            }

    }

    public void SetMsg(String message){
        msg.setText(message);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
