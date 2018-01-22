package saysiemka.GUI.PopUps;

import javafx.scene.control.Alert;
import org.languagetool.rules.RuleMatch;

/**
 * Created by barba on 22.01.2018.
 */
public class GoodJob implements PopUp{
    @Override
    public void createPopUp(RuleMatch rule) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Good job");
        alert.setHeaderText(null);
        alert.setContentText("Good job - no mistakes!");

        alert.showAndWait();
    }

    @Override
    public String getAnswer() {
        return null;
    }
}
