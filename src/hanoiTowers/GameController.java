package hanoiTowers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import models.AdditionalOptionsModel;
import models.DiskCountModel;
import models.ScenesModel;

import java.io.IOException;

public class GameController {
    @FXML
    private VBox towerLeft, towerCenter, towerRight;
    @FXML
    private Label moveCounter, maxMovesLabel, maxMovesCounterLabel, timeLabel, timeCountLabel, infoLabel;

    private int moves = 0;
    private int maxMoves;
    private Rectangle selected = null;
    private boolean gameRunning = true;

//    enum Colour {
//        BROWN(Color.BROWN), TURQUOISE(Color.TURQUOISE), GREEN(Color.GREEN), PINK(Color.PINK), GOLD(Color.GOLD),
//        INDIANRED(Color.INDIANRED), PURPLE(Color.PURPLE), ORANGE(Color.ORANGE);
//
//        Color colourCode;
//        Colour(Color color) {
//            this.colourCode = color;
//        }
//
//        public Color getColor() {
//            return this.colourCode;
//        }
//    }

    public void initialize() {
        this.startGame();
    }

    private void startGame() {
        if(AdditionalOptionsModel.getInstance().isHardmode()) {
            this.maxMovesCounterLabel.setVisible(true);
            this.maxMovesLabel.setVisible(true);
            this.maxMoves = (int)Math.pow(2, DiskCountModel.getInstance().getNumberDisksValue()) - 1;
            this.maxMovesCounterLabel.setText(Integer.toString(maxMoves));
        }
        if(AdditionalOptionsModel.getInstance().isTimed()) {
            this.timeCountLabel.setVisible(true);
            this.timeLabel.setVisible(true);
        }
        int numberDisks = DiskCountModel.getInstance().getNumberDisksValue();
        for(int i = 0; i < numberDisks; i++) {
            Rectangle rec = new Rectangle(30 + (30 * i), 25);
            rec.setFill(Color.valueOf("#7289DA"));
            rec.arcHeightProperty().set(15);
            rec.arcWidthProperty().set(15);
            rec.setStroke(Color.BLACK);
            rec.setStrokeWidth(2.5);
            this.towerLeft.getChildren().add(i, rec);
        }
    }

    private void incrementMoveCounter() {
        this.moves++;
        this.moveCounter.textProperty().set(Integer.toString(this.moves));
    }

    private void handleClickLogic(VBox tower) {
        if(!this.gameRunning) {
            return;
        }
        if(this.moves >= this.maxMoves && AdditionalOptionsModel.getInstance().isHardmode()) {
            this.setInfotext("Too many moves!");
            this.gameRunning = false;
            return;
        }
        if(this.selected == null) {
            if(tower.getChildren().isEmpty()) {
                return;
            }
            this.selected = (Rectangle)tower.getChildren().get(0);
            VBox.setMargin(this.selected, new Insets(0, 0, 15, 0));
            this.selected.setStroke(Color.WHITE);
            this.selected.setOpacity(0.75);
        } else {
            if(!tower.getChildren().isEmpty()) {
                Rectangle tempRec = (Rectangle)tower.getChildren().get(0);
                if(tempRec.getWidth() <= this.selected.getWidth()) {
                    this.selected.setOpacity(1);
                    VBox.setMargin(this.selected, new Insets(0, 0, 0, 0));
                    this.selected.setStroke(Color.BLACK);
                    this.selected = null;
                    return;
                }
            }

            tower.getChildren().add(0, this.selected);
            VBox.setMargin(this.selected, new Insets(0, 0, 0, 0));
            this.selected.setStroke(Color.BLACK);
            this.selected.setOpacity(1);
            this.incrementMoveCounter();
            this.selected = null;
        }
        checkWon();
    }

    private void setInfotext(String infoText) {
        this.infoLabel.setText(infoText);
        this.infoLabel.setVisible(true);
    }

    private void checkWon() {
        int numberDisks = DiskCountModel.getInstance().getNumberDisksValue();
        if(this.towerRight.getChildren().size() == numberDisks) {
            setInfotext("Well done! :)");
            this.gameRunning = false;
        }
    }

    public void towerLeftOnClick(MouseEvent mouseEvent) {
        this.handleClickLogic(towerLeft);
    }

    public void towerCenterOnClick(MouseEvent mouseEvent) {
        this.handleClickLogic(towerCenter);
    }

    public void towerRightOnClick(MouseEvent mouseEvent) {
        this.handleClickLogic(towerRight);
    }

    public void menuGameButton(MouseEvent mouseEvent) throws IOException {
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/scenes/startScreen.fxml"))));
    }

    public void resetButtonOnClick(MouseEvent mouseEvent) throws IOException {
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/scenes/hanoiGame.fxml"))));
    }
}
