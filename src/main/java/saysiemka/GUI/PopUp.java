package saysiemka.GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

/**
 * Created by barba on 21.01.2018.
 */
public class PopUp {
    /**
     * @param question   - both question and numbered answers
     * @param goodAnswer - "One", "Two" or "Three"
     */
    public void chooseOptionHorizontal(String question, String goodAnswer) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prove yourself!");
        alert.setHeaderText("Choose the best option.");
        alert.setContentText(question);

        String[] words = new String[3];
        words[0] = goodAnswer;
        words[1] = createString1(goodAnswer);
        words[2] = createString2(goodAnswer);
        int a = (int)System.currentTimeMillis()%3;
        ButtonType buttonTypeOne = new ButtonType(words[(a++)%3]);
        ButtonType buttonTypeTwo = new ButtonType(words[(a++)%3]);
        ButtonType buttonTypeThree = new ButtonType(words[(a)%3]);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private String createString1(String base) {
        if (base.contains("ea")) {
            return base.replaceFirst("ea", "e");
        } else if (base.contains("ae")) {
            return base.replaceFirst("ae", "a");
        } else if (base.contains("ie")){
            return base.replaceFirst("ie","e");
        } else if (base.contains("vi")){
            return  base.replaceFirst("vi","va");
        } else return base+"a";
    }

    private String createString2(String base){
        if (base.contains("st")) {
            return base.replaceFirst("st", "sd");
        } else if (base.contains("ss")) {
            return base.replaceFirst("ss", "s");
        } else if (base.contains("tu")){
            return base.replaceFirst("tu","ta");
        } else if (base.contains("me")){
            return  base.replaceFirst("me","ma");
        } else if (base.contains("th")){
            return  base.replaceFirst("th","h");
        }else return base+"h";
    }

    /**
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
