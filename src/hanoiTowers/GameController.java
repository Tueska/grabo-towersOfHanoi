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

    public GameController() {
        super();
    }

    public GameController(Stage game) throws IOException {
        super();
        Parent root = FXMLLoader.load(getClass().getResource("hanoiGame.fxml"));
        game.setTitle("GRABO - Towers of Hanoi");
        game.setScene(new Scene(root));
        game.show();

        this.game = game;
        this.startGame();
    }

    private void startGame() {

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
