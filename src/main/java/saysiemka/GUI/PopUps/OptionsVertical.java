package saysiemka.GUI.PopUps;

import javafx.scene.control.ChoiceDialog;
import org.languagetool.rules.RuleMatch;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by barba on 22.01.2018.
 */
public class OptionsVertical implements PopUp {
    private String answer;

    @Override
    public void createPopUp(RuleMatch rule) {
        List<String> list = new LinkedList<>();

        for (int i = 0; i < 10
                && i < rule.getSuggestedReplacements().size(); i++) {
            list.add(rule.getSuggestedReplacements().get(i));
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", list);
        dialog.setTitle("Prove yourself!");
        dialog.setHeaderText("What should be that word?");
        dialog.setContentText("Choose the answer:");

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
