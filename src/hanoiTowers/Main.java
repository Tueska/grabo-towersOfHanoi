package hanoiTowers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private GameController gameControl;
    private MenuController menuControl;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent menuRoot = loader.load(getClass().getResource("/scenes/startScreen.fxml"));
        ScenesModel.STAGE = primaryStage;
        primaryStage.setTitle("GRABO - Towers of Hanoi");
        primaryStage.setScene(new Scene(menuRoot));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
