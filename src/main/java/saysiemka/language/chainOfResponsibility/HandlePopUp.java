package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.PopUp;

public interface HandlePopUp {
    void setNextHandler(HandlePopUp popUp);

    PopUp handleTask(RuleMatch rule);
}
