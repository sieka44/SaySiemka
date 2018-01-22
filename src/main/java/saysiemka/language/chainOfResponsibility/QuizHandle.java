package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.OptionsHorizontal;
import saysiemka.GUI.PopUps.PopUp;

public class QuizHandle implements HandlePopUp {
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
        if (rule.getSuggestedReplacements().size() == 1) {
            return new OptionsHorizontal();
        } else {
            return nextPopUp.handleTask(rule);
        }
    }
}
