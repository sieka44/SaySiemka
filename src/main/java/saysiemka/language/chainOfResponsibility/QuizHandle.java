package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class QuizHandle extends PopUpHandler {
    @Override
    public void handleTask(RuleMatch rule) {
        if(rule.getSuggestedReplacements().size()==1){
            System.out.println("Quiz will handle!");
            System.out.println(rule.getSuggestedReplacements());
        }else {
            setNextHandler(new ManySuggestionsHandle());
            super.handleTask(rule);
        }
    }
}
