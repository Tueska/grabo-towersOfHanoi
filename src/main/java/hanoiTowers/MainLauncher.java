package hanoiTowers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ScenesModel;

public class MainLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent menuRoot = FXMLLoader.load(getClass().getResource("/scenes/startScreen.fxml"));
        ScenesModel.STAGE = primaryStage;
        primaryStage.setTitle("GRABO - Towers of Hanoi");
        primaryStage.setScene(new Scene(menuRoot));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
