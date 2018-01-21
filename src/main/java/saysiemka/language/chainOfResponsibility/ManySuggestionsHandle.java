package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class ManySuggestionsHandle extends PopUpHandler{
    @Override
    public void handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() < 10) {
            System.out.println("Cannot handle no suggestions!");
            System.out.println(rule.getSuggestedReplacements());
        }else {
            setNextHandler(new TooManySuggestionHandle());
            super.handleTask(rule);
        }
    }
}
