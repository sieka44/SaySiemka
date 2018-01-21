package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.PopUp;

import java.util.LinkedList;
import java.util.List;

public class ManySuggestionsHandle implements HandlePopUp {
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
        if (rule.getSuggestedReplacements().size() < 10) {
            System.out.println("//Many suggestions!");
            System.out.println(rule.getSuggestedReplacements());
            PopUp popUp = new PopUp();
            List<String> list = new LinkedList<>();

            for (int i = 0; i < 10
                    && i < rule.getSuggestedReplacements().size(); i++) {
                list.add(rule.getSuggestedReplacements().get(i));
            }

            //TODO What is the good answer?
            popUp.chooseOptionVertical("My question", list, "a");
        } else {
            nextPopUp.handleTask(rule);
        }
    }
}
