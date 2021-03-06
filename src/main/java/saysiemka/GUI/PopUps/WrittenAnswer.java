package saysiemka.GUI.PopUps;

import javafx.scene.control.TextInputDialog;
import org.languagetool.rules.RuleMatch;

import java.util.Optional;

/**
 * Created by barba on 22.01.2018.
 */
public class WrittenAnswer implements PopUp {
    private String answer;

    @Override
    public void createPopUp(RuleMatch rule) {
        TextInputDialog dialog = new TextInputDialog("Your answer");
        dialog.setTitle("Prove yourself");
        dialog.setHeaderText("We don't know what do you mean.");
        dialog.setContentText("Please write it again:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            answer = result.get();
        }
    }

    @Override
    public String getAnswer() {
        return answer;
    }
}
