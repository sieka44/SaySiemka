package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public interface HandlePopUp {
    void setNextHandler(HandlePopUp popUp);
    void handleTask(RuleMatch rule);
}
