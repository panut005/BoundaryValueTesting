package work1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../MainLayout.fxml"));
        primaryStage.setTitle("Testing");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
