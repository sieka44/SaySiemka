package saysiemka.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import saysiemka.userInfo;

import java.io.IOException;

/**
 * Created by barba on 03.01.2018.
 */
public class Controller_login {

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nick;

    @FXML
    private TextField password;

    @FXML
    public void initialize() {
        nick.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                signIn();
                event.consume();
            }
        });

        password.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                signIn();
                event.consume();
            }
        });

        signInButton.setOnAction(e -> {
            signIn();
        });

        signUpButton.setOnAction(e -> {
            System.out.println("signing up ...");

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/signingUp.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Signing up");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            signUpButton.getParent().getScene().getWindow().hide();
            stage.show();
        });
    }

    private void signIn() {
        System.out.println("signing in ...");
        userInfo.setLoginAndPass(nick.getText(),password.getText());
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/communicator_fxml.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        while (userInfo.isLogedIn()==null){
        }

        if(userInfo.isLogedIn()) {
            Stage stage = new Stage();
            stage.setTitle("SaySiemka");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            signInButton.getParent().getScene().getWindow().hide();
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something is wrong.");
            alert.setHeaderText("Invalid login or password.");
            alert.setContentText("Please, check and correct it.");

            alert.showAndWait();
        }
    }
}