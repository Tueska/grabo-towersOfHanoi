package hanoiTowers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage menu;
    private Parent root;
    private GameController gameControl;

    public MenuController(){
        super();
    }

    public MenuController(Stage menu, GameController game) throws IOException {
        super();
        this.gameControl = game;
        this.root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        menu.setTitle("GRABO - Towers of Hanoi");
        menu.setScene(new Scene(root));
        menu.show();
        this.menu = menu;
    }

    public void decrementMouseButtonClicked(MouseEvent mouseEvent) {
    }

    public void incrementDiskButtonClick(MouseEvent mouseEvent) {
    }

    public void exitGameButton(MouseEvent mouseEvent) {
    }

    public void startGameButton(MouseEvent mouseEvent) throws IOException {
        gameControl.startGame(this.menu);
    }

}
