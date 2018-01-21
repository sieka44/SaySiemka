package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class PopUpHandler {
    HandlePopUp popUp = new CannotHandle();

    public PopUpHandler() {
        popUp.setNextHandler(new QuizHandle());
        popUp.setNextHandler(new ManySuggestionsHandle());
        popUp.setNextHandler(new TooManySuggestionHandle());
    }

    public void findPopUp(RuleMatch rule) {
        System.out.println("finding pop up");
        System.out.println("It may be: " + rule.getSuggestedReplacements());
        popUp.handleTask(rule);
    }
}
