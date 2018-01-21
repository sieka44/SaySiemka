package saysiemka.language.chainOfResponsibility;

import javafx.stage.Stage;
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
}
