package saysiemka.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import saysiemka.db.MockDb;

import java.io.IOException;

/**
 * Created by barba on 08.01.2018.
 */
public class Controller_signingUp {
    private FXMLLoader loader;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordAgain;

    @FXML
    private TextField nick;

    @FXML
    public void initialize() {
//        passwordAgain.setOnKeyPressed(event -> {
////            if (event.getCode().equals(KeyCode.ENTER)) {
////                //signUp();
////                event.consume();
////            }
//        });

//        signUpButton.setOnAction(e -> {
//            signUp();
//        });
    }

    @FXML
    private void signingUp() {
        signUp();
    }

    private void signUp() {
        if (MockDb.contains(nick.getText(), password.getText())) {
            showAlert("There is already such user.",
                    "Please, change your nick.");
        }
        else if (passwordAgain.getText().equals(password.getText())) {
            showAlert("You gave two different passwords.",
                    "Please, repeat your registration.");
        }
        else {
            addUser();

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/login.fxml"));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AppWindow.setTitle("Login");
            Scene scene = new Scene(pane);
            AppWindow.setScene(scene);
            signUpButton.getParent().getScene().getWindow().hide();
            AppWindow.show();
        }
    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Something is wrong.");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void addUser() {
        MockDb.add(nick.getText(), password.getText());
    }
}
