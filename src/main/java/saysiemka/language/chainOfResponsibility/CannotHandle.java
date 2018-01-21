package saysiemka.language.chainOfResponsibility;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.languagetool.rules.RuleMatch;
import saysiemka.GUI.AppWindow;

import static saysiemka.GUI.AppWindow.getScene;


public class CannotHandle extends PopUpHandler {
    @Override
    public void handleTask(RuleMatch rule) {
        if (rule.getSuggestedReplacements().size() <= 0) {
            System.out.println("Cannot handle - no suggestions!");
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(AppWindow.getStage());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("This is a Dialog"));
            dialogVbox.getChildren().add(new Button("OK"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }else {
            setNextHandler(new QuizHandle());
            super.handleTask(rule);
        }

    }
}
