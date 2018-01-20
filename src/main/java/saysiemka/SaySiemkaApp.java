package saysiemka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import saysiemka.GUI.AppWindow;

public class SaySiemkaApp extends Application {
    private Stage stage;
    private FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppWindow.setStage(primaryStage);
        stage = primaryStage;
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Pane pane = loader.load();
        stage.setTitle("Login");
        Scene scene = new Scene(pane);
        AppWindow.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}