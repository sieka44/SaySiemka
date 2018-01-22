package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.PopUp;
import saysiemka.GUI.PopUps.WrittenAnswer;

public class TooManySuggestionHandle implements HandlePopUp {
    private HandlePopUp nextPopUp;

    @Override
    public void setNextHandler(HandlePopUp popUp) {
        if (nextPopUp == null) {
            nextPopUp = popUp;
        }
        else {
            nextPopUp.setNextHandler(popUp);
        }
    }

    @Override
    public PopUp handleTask(RuleMatch rule) {
        return new WrittenAnswer();
    }
}
