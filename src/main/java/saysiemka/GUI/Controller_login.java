package saysiemka.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        signInButton.setOnAction(e -> {
            System.out.println("signing in ...");
            //TODO login conditions

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/communicator_fxml.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Welcome");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            signInButton.getParent().getScene().getWindow().hide();
            stage.show();
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
            signInButton.getParent().getScene().getWindow().hide();
            stage.show();
        });
    }
}