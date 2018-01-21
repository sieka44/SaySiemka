package saysiemka.GUI;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by barba on 21.01.2018.
 */
public class PopUp {
    /**
     *
     * @param question - both question and numbered answers
     * @param goodAnswer - "One", "Two" or "Three"
     */
    public void chooseOptionHorizontal(String question, String goodAnswer) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prove yourself!");
        alert.setHeaderText("Choose the best option.");
        alert.setContentText(question);

        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     *
     * @param question
     * @param goodAnswer
     */
    public void writeAnswer(String question, String goodAnswer) {
        TextInputDialog dialog = new TextInputDialog("Your answer");
        dialog.setTitle("Prove yourself");
        dialog.setHeaderText(question);
        dialog.setContentText("Please write the answer:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            //TODO What we should do with the answer?
        });
    }

    public void goodJob() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Good job");
        alert.setHeaderText(null);
        alert.setContentText("Good job - no mistakes!");

        alert.showAndWait();
    }

    public void chooseOptionVertical(String question, List<String> choices, String goodAnswer) {

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Prove yourself!");
        dialog.setHeaderText(question);
        dialog.setContentText("Choose the answer:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(answer -> {
            //TODO What we should do with the answer?
        });
    }
}
