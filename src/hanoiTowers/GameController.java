package hanoiTowers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    VBox towerLeft, towerCenter, towerRight;
    @FXML
    Button resetButton;
    @FXML
    GridPane gameScreen;

    Stage game;
    Scene content;

    public GameController() {
        super();
    }

    public GameController(Stage game) throws IOException {
        super();

        Parent root = FXMLLoader.load(getClass().getResource("hanoiGame.fxml"));
        this.content = new Scene(root);
        this.game = game;
    }

    public void startGame(Stage gameStage) {
        gameStage.setScene(this.content);
    }

    private void cleanupGame() {

    }

    public void towerLeftOnClick(MouseEvent mouseEvent) {
    }

    public void towerCenterOnClick(MouseEvent mouseEvent) {
    }

    public void towerRightOnClick(MouseEvent mouseEvent) {
    }
}
