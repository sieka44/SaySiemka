package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUp;

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
    public void handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() == 1) {
            System.out.println("//Quiz");
            System.out.println(rule.getSuggestedReplacements());
            PopUp popUp = new PopUp();
            //TODO
            popUp.chooseOptionHorizontal("My question", "Two");
        } else {
            nextPopUp.handleTask(rule);
        }
    }
}
