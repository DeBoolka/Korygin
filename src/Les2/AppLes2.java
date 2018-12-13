package Les2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLes2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Задание 2");
        primaryStage.setScene(new Scene(root, 420, 188));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
