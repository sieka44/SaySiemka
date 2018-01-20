package saysiemka.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import saysiemka.userInfo;

import java.io.IOException;

/**
 * Created by barba on 03.01.2018.
 */
public class Controller_login {
    private FXMLLoader loader;

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

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/signingUp.fxml"));
            VBox vBox = null;
            try {
                vBox = loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            AppWindow.setTitle("Login");
            Scene scene = new Scene(vBox);
            AppWindow.setScene(scene);
            AppWindow.show();
        });
    }

    private void signIn() {
        System.out.println("signing in ...");
        userInfo.setLoginAndPass(nick.getText(),password.getText());

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/communicator_fxml.fxml"));
        TabPane tabPane = null;
        try {
            tabPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (userInfo.isLogedIn()==null){
        }

        if(userInfo.isLogedIn()) {
            AppWindow.setTitle("SaySiemka");
            Scene scene = new Scene(tabPane);
            AppWindow.setScene(scene);
            signInButton.getParent().getScene().getWindow().hide();
            AppWindow.show();
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