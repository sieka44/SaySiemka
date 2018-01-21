package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUp;

public class CannotHandle implements HandlePopUp {
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
        if (rule.getSuggestedReplacements().size() <= 0) {
            System.out.println("//Cannot handle - no suggestions!");
            PopUp popUp = new PopUp();
            return popUp.writeAnswer("No suggestions. Could you write it again?");
        }
        else {
            return nextPopUp.handleTask(rule);
        }
    }
}
