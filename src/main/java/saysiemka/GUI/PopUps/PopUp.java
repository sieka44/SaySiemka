package saysiemka.GUI.PopUps;

import org.languagetool.rules.RuleMatch;

/**
 * Created by barba on 21.01.2018.
 */
public interface PopUp {
    void createPopUp(RuleMatch rule);

    String getAnswer();
}
