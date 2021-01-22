package hanoiTowers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;

public class MenuController {

    @FXML
    Button decrementDiskButton, incrementDiskButton, exitButton, startButton;
    @FXML
    Slider diskSlider;
    @FXML
    Label diskLabel;

    public void initialize() {
        this.diskSlider.valueProperty().bindBidirectional(DiskCountModel.getInstance().getNumberDisks());
        this.diskLabel.textProperty().bindBidirectional(DiskCountModel.getInstance().getNumberDisks(), new NumberStringConverter());
    }

    public void decrementMouseButtonClicked(MouseEvent mouseEvent) {
        int value = DiskCountModel.getInstance().getNumberDisksValue() - 1;
        DiskCountModel.getInstance().setNumberDisksValue(value);
    }

    public void incrementDiskButtonClick(MouseEvent mouseEvent) {
        int value = DiskCountModel.getInstance().getNumberDisksValue() + 1;
        DiskCountModel.getInstance().setNumberDisksValue(value);
    }

    public void exitGameButton(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void startGameButton(MouseEvent mouseEvent) throws IOException {
        ScenesModel.STAGE.setScene(new Scene(new FXMLLoader().load(getClass().getResource("/scenes/hanoiGame.fxml"))));
    }
}
