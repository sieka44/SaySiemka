package saysiemka.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import saysiemka.language.LanguageController;
import saysiemka.language.PolishLanguageController;
import saysiemka.userInfo;

import java.util.Arrays;
import java.util.List;

public class Controller{
    @FXML
    private TextField chatMessageFiled;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private ListView<String> userList;

    ServerConnection serverConnection;
    LanguageController languageController;

    public Controller() {
        super();
        //login(userInfo.getLogin(),userInfo.getPassword(),"localhost",userInfo.getPORT());
        languageController = new PolishLanguageController();
    }


    @FXML
    protected void sendMessage() {
        String message = chatMessageFiled.getText();
        this.chatMessageFiled.setText("");
        List list = languageController.checkGrammar(message);

        serverConnection.send(message,true);
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
    }

    public void addText(String text){
        chatTextArea.appendText(text + "\n");
    }
    protected void userUpdate(String[] users){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userList.getItems().clear();
                userList.getItems().addAll(users);
            }
        });
    }
}