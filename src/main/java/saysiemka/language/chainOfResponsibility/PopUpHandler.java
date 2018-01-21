package saysiemka.language.chainOfResponsibility;

import org.languagetool.rules.RuleMatch;

public class PopUpHandler implements HandlePopUp {
    HandlePopUp nextPopUp;
    @Override
    public void setNextHandler(HandlePopUp popUp) {
        nextPopUp = popUp;
    }

    @Override
    public void handleTask(RuleMatch rule) {
        if(nextPopUp!=null)nextPopUp.handleTask(rule);
    }

    public void setNextPopUp(HandlePopUp nextPopUp) {
        this.nextPopUp = nextPopUp;
    }
}
