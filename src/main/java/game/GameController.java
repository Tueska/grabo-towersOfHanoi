package game;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private LongProperty timerProperty;

    public void initialize() {
        // Binding Timer for displaying time in seconds
        this.timerProperty = new SimpleLongProperty();
        this.timeCountLabel.textProperty().bind(Bindings.concat(timerProperty.divide(1000), "s"));
        this.startGame();
    }

    private void startGame() {
        // Check for Hardmode
        if(AdditionalOptionsModel.getInstance().isHardmode()) {
            // Enable that it's only possible to win the game using the minimal
            // amount of moves required to solve the puzzle
            this.maxMovesCounterLabel.setVisible(true);
            this.maxMovesLabel.setVisible(true);
            this.maxMoves = (int)Math.pow(2, DiskCountModel.getInstance().getNumberDisksValue()) - 1;
            this.maxMovesCounterLabel.setText(Integer.toString(maxMoves));
        }
        // Check for Timed mode
        if(AdditionalOptionsModel.getInstance().isTimed()) {
            this.timeCountLabel.setVisible(true);
            this.timeLabel.setVisible(true);
            // Creating Task so that the Timer can display the delta time
            Task<Void> timerTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    long startTime = System.currentTimeMillis();
                    while (gameRunning) {
                        if (isCancelled()) {
                            break;
                        }
                        // .runLater seems to be required in order to run properly.
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                timerProperty.setValue(System.currentTimeMillis() - startTime);
                            }
                        });
                        Thread.sleep(1000);
                    }
                    return null;
                }
            };
            // Creating new Thread with the above written Task, setting Daemon for proper exiting
            Thread timerThread = new Thread(timerTask);
            timerThread.setDaemon(true);
            timerThread.start();
        }
        // Filling the Game with proper amount of disks select in the Main Menu
        int numberDisks = DiskCountModel.getInstance().getNumberDisksValue();
        for(int i = 0; i < numberDisks; i++) {
            Rectangle rec = new Rectangle(30 + (30 * i), 25);
            // Styling of the new Rectangle
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
        // Hardmode Check -> Ends the game when too many moves are made.
        if(this.moves >= this.maxMoves && AdditionalOptionsModel.getInstance().isHardmode()) {
            this.setInfotext("Too many moves!");
            this.gameRunning = false;
            return;
        }
        // If no Disk is selected
        if(this.selected == null) {
            if(tower.getChildren().isEmpty()) {
                return;
            }
            // Let Disk float, set transparent and outline it with different colour
            this.selected = (Rectangle)tower.getChildren().get(0);
            VBox.setMargin(this.selected, new Insets(0, 0, 15, 0));
            this.selected.setStroke(Color.WHITE);
            this.selected.setOpacity(0.75);
        } else {
            // Disk is selected and Tower is not empty
            if(!tower.getChildren().isEmpty()) {
                Rectangle tempRec = (Rectangle)tower.getChildren().get(0);
                // If Disk at top of tower is smaller than the selected disk the move is cancelled.
                if(tempRec.getWidth() <= this.selected.getWidth()) {
                    VBox.setMargin(this.selected, new Insets(0, 0, 0, 0));
                    this.selected.setStroke(Color.BLACK);
                    this.selected.setOpacity(1);
                    this.selected = null;
                    return;
                }
            }
            // Add Selected disk at the top of the tower
            tower.getChildren().add(0, this.selected);
            VBox.setMargin(this.selected, new Insets(0, 0, 0, 0));
            this.selected.setStroke(Color.BLACK);
            this.selected.setOpacity(1);
            this.selected = null;
            this.incrementMoveCounter();
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
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/startScreen.fxml"))));
    }

    public void resetButtonOnClick(MouseEvent mouseEvent) throws IOException {
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/hanoiGame.fxml"))));
    }

}
