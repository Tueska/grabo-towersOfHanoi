package hanoiTowers;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class GameController {
    @FXML
    private VBox towerLeft, towerCenter, towerRight;
    @FXML
    private Button resetButton;
    @FXML
    private Label moveCounter;

    private int moves = 0;
    private Rectangle selected = null;

    public void initialize() {
        startGame();
    }

    private void startGame() {
        int numberDisks = DiskCountModel.getInstance().getNumberDisksValue();
        for(int i = 0; i < numberDisks; i++) {
            Rectangle rec = new Rectangle(30 + (20 * i), 30);
            towerLeft.getChildren().add(i, rec);
        }
    }

    private void incrementMoveCounter() {
        this.moves++;
        moveCounter.textProperty().set(Integer.toString(this.moves));
    }

    private void handleClickLogic(VBox tower) {
        if(this.selected == null) {
            if(tower.getChildren().isEmpty()) {
                return;
            }
            this.selected = (Rectangle)tower.getChildren().remove(0);
        } else {
            if(!tower.getChildren().isEmpty()) {
                Rectangle tempRec = (Rectangle)tower.getChildren().get(0);
                if(tempRec.getWidth() < this.selected.getWidth()) {
                    return;
                }
            }
            tower.getChildren().add(0, this.selected);
            incrementMoveCounter();
            this.selected = null;
        }
    }

    private void checkWon() {

    }

    public void towerLeftOnClick(MouseEvent mouseEvent) {
        handleClickLogic(towerLeft);
    }

    public void towerCenterOnClick(MouseEvent mouseEvent) {
        handleClickLogic(towerCenter);
    }

    public void towerRightOnClick(MouseEvent mouseEvent) {
        handleClickLogic(towerRight);
    }

    public void menuGameButton(MouseEvent mouseEvent) throws IOException {
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/scenes/startScreen.fxml"))));
    }
}
