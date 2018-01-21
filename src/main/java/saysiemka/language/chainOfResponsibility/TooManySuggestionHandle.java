package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUp;

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
    public String handleTask(RuleMatch rule) {
        System.out.println("//Too many suggestions. Could u specify?");
        System.out.println(rule.getSuggestedReplacements());
        PopUp popUp = new PopUp();
        return popUp.writeAnswer("Too many sugestions. Could you specify?");
    }
}
