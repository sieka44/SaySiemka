package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public interface HandlePopUp {
    void setNextHandler(HandlePopUp popUp);
    String handleTask(RuleMatch rule);
}
