package hanoiTowers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DiskCountModel {
    private static DiskCountModel instance;

    public static DiskCountModel getInstance() {
        if(instance == null) instance = new DiskCountModel();
        return instance;
    }

    private IntegerProperty numberDisks;

    public DiskCountModel() {
        this.numberDisks = new SimpleIntegerProperty(3);
    }

    public IntegerProperty getNumberDisks() {
        return numberDisks;
    }

    public void setNumberDisksValue(int numberDisks) {
        if(numberDisks < 3 || numberDisks > 8) {
            return;
        }
        this.numberDisks.setValue(numberDisks);
    }

    public int getNumberDisksValue() {
        return this.numberDisks.getValue();
    }
}
