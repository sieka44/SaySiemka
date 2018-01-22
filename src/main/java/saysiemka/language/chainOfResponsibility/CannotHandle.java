package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.PopUp;
import saysiemka.GUI.PopUps.WrittenAnswer;

public class CannotHandle implements HandlePopUp {
    private HandlePopUp nextPopUp;

    @Override
    public void setNextHandler(HandlePopUp popUp) {
        if (nextPopUp == null) {
            nextPopUp = popUp;
        } else {
            nextPopUp.setNextHandler(popUp);
        }
    }

    @Override
    public PopUp handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() <= 0) {
            return new WrittenAnswer();
        } else {
            return nextPopUp.handleTask(rule);
        }
    }
}
