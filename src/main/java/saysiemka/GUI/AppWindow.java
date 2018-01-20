package saysiemka.GUI;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by barba on 20.01.2018.
 */
public class AppWindow {
    private static Stage stage;
    private static Scene scene;

    public static void setScene(Scene _scene) {
        scene = _scene;
        stage.setScene(scene);
    }

    public static void setStage(Stage _stage) {
        stage = _stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void show() {
        stage.show();
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }
}
