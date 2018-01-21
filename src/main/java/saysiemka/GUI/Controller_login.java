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
    private ServerConnection serverConnection;
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
        if (serverConnection == null) serverConnection = new ServerConnection();
        String s1 = nick.getText();
        String s2 = password.getText();
        if (s1.length() > 0 && s2.length() > 0)
            serverConnection.login(s1, s2, "localhost", userInfo.getPORT());
        else {
            showAlert();
            return;
        }

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/communicator_fxml.fxml"));
        TabPane tabPane = null;
        try {
            tabPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (serverConnection.getLoggedIn() == null) {
        }

        if (serverConnection.getLoggedIn()) {
            AppWindow.setTitle("SaySiemka");
            Scene scene = new Scene(tabPane);
            AppWindow.setScene(scene);
            Controller controller = (Controller) loader.getController();
            serverConnection.setController(controller);
            controller.setServerConnection(serverConnection);
            AppWindow.show();
        } else {
            serverConnection.setLoggedIn(null);
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Something is wrong.");
        alert.setHeaderText("Invalid login or password.");
        alert.setContentText("Please, check and correct it.");

        alert.showAndWait();
    }
}