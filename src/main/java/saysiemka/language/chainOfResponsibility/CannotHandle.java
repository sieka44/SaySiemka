package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

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
    public void handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() <= 0) {
            System.out.println("//Cannot handle - no suggestions!");
        }
        else {
            nextPopUp.handleTask(rule);
        }
    }
}
