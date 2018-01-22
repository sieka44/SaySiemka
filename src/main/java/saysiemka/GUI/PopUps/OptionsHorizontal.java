package saysiemka.GUI.PopUps;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.languagetool.rules.RuleMatch;

import java.util.Optional;

/**
 * Created by barba on 22.01.2018.
 */
public class OptionsHorizontal implements PopUp {
    private String answer;

    @Override
    public void createPopUp(RuleMatch rule) {
        answer = rule.getSuggestedReplacements().get(0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prove yourself!");
        alert.setHeaderText("Choose the best option.");
        alert.setContentText("Which of the words is a good form?");

        String[] words = new String[3];
        words[0] = answer;
        words[1] = createString1(answer);
        words[2] = createString2(answer);
        int a = (int) System.currentTimeMillis() % 3;
        ButtonType buttonTypeOne = new ButtonType(words[(a) % 3]);
        ButtonType buttonTypeTwo = new ButtonType(words[(a + 1) % 3]);
        ButtonType buttonTypeThree = new ButtonType(words[(a + 2) % 3]);
        if ((a + 1) % 3 == 0) a++;
        else if ((a + 2) % 3 == 0) a = +2;
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne && a == 0 || result.get() == buttonTypeTwo && a == 1 || result.get() == buttonTypeThree && a == 2) {
            PopUp popUp = new GoodJob();
            popUp.createPopUp(rule);
            answer = "." + answer;
        }
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    private String createString1(String base) {
        if (base.contains("ea")) {
            return base.replaceFirst("ea", "e");
        } else if (base.contains("ae")) {
            return base.replaceFirst("ae", "a");
        } else if (base.contains("ie")) {
            return base.replaceFirst("ie", "e");
        } else if (base.contains("vi")) {
            return base.replaceFirst("vi", "va");
        } else return base + "a";
    }

    private String createString2(String base) {
        if (base.contains("st")) {
            return base.replaceFirst("st", "sd");
        } else if (base.contains("ss")) {
            return base.replaceFirst("ss", "s");
        } else if (base.contains("tu")) {
            return base.replaceFirst("tu", "ta");
        } else if (base.contains("me")) {
            return base.replaceFirst("me", "ma");
        } else if (base.contains("th")) {
            return base.replaceFirst("th", "h");
        } else return base + "h";
    }
}
