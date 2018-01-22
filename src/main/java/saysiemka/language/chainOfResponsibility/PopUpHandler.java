package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUps.PopUp;

public class PopUpHandler {
    HandlePopUp handler = new CannotHandle();
    boolean points = false;

    public PopUpHandler() {
        handler.setNextHandler(new QuizHandle());
        handler.setNextHandler(new ManySuggestionsHandle());
        handler.setNextHandler(new TooManySuggestionHandle());
    }

    public String findPopUp(RuleMatch rule) {
        System.out.println("finding pop up");
        System.out.println("It may be: " + rule.getSuggestedReplacements());

        PopUp popUp = handler.handleTask(rule);
        popUp.createPopUp(rule);
        return popUp.getAnswer();
    }
}
