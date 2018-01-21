package saysiemka.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.languagetool.rules.RuleMatch;
import saysiemka.language.BritishEnglishController;
import saysiemka.language.LanguageController;
import saysiemka.language.chainOfResponsibility.PopUpHandler;

import java.util.LinkedList;
import java.util.List;

public class Controller {
    @FXML
    private TextField chatMessageFiled;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private ListView<String> userList;

    private ServerConnection serverConnection;
    private LanguageController languageController;
    private PopUpHandler popUp;


    public Controller() {
        super();
        languageController = new BritishEnglishController();
        popUp = new PopUpHandler();
    }


    @FXML
    protected void sendMessage() {
        String message = chatMessageFiled.getText();
        this.chatMessageFiled.setText("");
        List<RuleMatch> list;
        String[] words = message.split(" ");
        PopUpHandler handler = new PopUpHandler();
        for (int i = 0; i < words.length; i++) {
            list = languageController.checkGrammar(words[i]);
            for (RuleMatch rule : list) {
                if (rule.getShortMessage() != null) {
                    words[i] = handler.findPopUp(rule);
                }
            }
        }
        message = String.join(" ", words);
        serverConnection.send(message, true);
    }

    @FXML
    public void initialize() {
        chatMessageFiled.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
                event.consume();
            }
        });
    }

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;

        chatMessageFiled.getScene().getWindow().setOnHiding(event -> Platform.runLater(() -> {
            serverConnection.disconnect();
            System.exit(0);
        }));
    }

    public void addText(String text) {
        chatTextArea.appendText(text + "\n");
    }

    protected void userUpdate(String[] users) {
        Platform.runLater(() -> {
            userList.getItems().clear();
            userList.getItems().addAll(users);
        });
    }
}