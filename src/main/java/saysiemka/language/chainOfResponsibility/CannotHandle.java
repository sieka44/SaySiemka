package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class CannotHandle extends PopUpHandler {
    @Override
    public void handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() <= 0) {
            System.out.println("Cannot handle - no suggestions!");
        }else {
            setNextHandler(new QuizHandle());
            super.handleTask(rule);
        }

    }
}
