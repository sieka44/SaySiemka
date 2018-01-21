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
            String goodAnswer = rule.getSuggestedReplacements().get(0);
            PopUp popUp = new PopUp();
            popUp.chooseOptionHorizontal("Which of the words is a good form?", goodAnswer);
        } else {
            nextPopUp.handleTask(rule);
        }
    }
}
