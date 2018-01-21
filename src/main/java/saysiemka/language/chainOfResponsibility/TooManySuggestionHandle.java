package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class TooManySuggestionHandle extends PopUpHandler {
    @Override
    public void handleTask(RuleMatch rule) {
        System.out.println("Too many suggestions could u specify!");
        System.out.println(rule.getSuggestedReplacements());
    }
}
