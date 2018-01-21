package saysiemka.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import saysiemka.userInfo;

import java.io.IOException;

/**
 * Created by barba on 08.01.2018.
 */
public class Controller_signingUp {
    private FXMLLoader loader;
    private ServerConnection serverConnection;
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

        passwordAgain.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                signUp();
                event.consume();
            }
        });
    }


    @FXML
    private void signingUp() {
        signUp();
    }

    private void signUp() {
        if(serverConnection==null) serverConnection= new ServerConnection();

        String name = nick.getText();
        String pass = password.getText();
        String pass2 = passwordAgain.getText();
        if (!pass.equals(pass2)) {
            showAlert("You gave two different passwords.",
                    "Please, repeat your registration.");
            return;
        } else if (name.length() > 2 && pass.length() > 2) {
            serverConnection.signUp(name, pass, "localhost", userInfo.getPORT());
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/communicator_fxml.fxml"));
            TabPane tabPane = null;
            try {
                tabPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (serverConnection.getLoggedIn()==null){}

            if(serverConnection.getLoggedIn()) {
                AppWindow.setTitle("SaySiemka");
                Scene scene = new Scene(tabPane);
                AppWindow.setScene(scene);
                Controller controller = (Controller) loader.getController();
                if (controller == null) System.out.println("COS JEST NIE TAK :(((((((");
                serverConnection.setController(controller);
                controller.setServerConnection(serverConnection);
                AppWindow.show();
            }
            else {
                serverConnection.setLoggedIn(null);
                showAlert("There is already user with this nick",
                        "Please, repeat your registration.");
            }
        } else {
            showAlert("Invalid size of login or password.",
                    "Please, check and correct it.(size > 2)");
        }
    }

    private void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Something is wrong.");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

}
