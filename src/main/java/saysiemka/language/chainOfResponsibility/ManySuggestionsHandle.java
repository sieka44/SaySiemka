package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.OptionsVertical;
import saysiemka.GUI.PopUps.PopUp;

public class ManySuggestionsHandle implements HandlePopUp {
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
        if (rule.getSuggestedReplacements().size() < 10) {
            return new OptionsVertical();
        } else {
            return nextPopUp.handleTask(rule);
        }
    }
}
