package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class AdditionalOptionsModel {
    private static AdditionalOptionsModel instance;

    public static AdditionalOptionsModel getInstance() {
        if(instance == null) instance = new AdditionalOptionsModel();
        return instance;
    }

    private final BooleanProperty hardmode;
    private final BooleanProperty timed;

    public AdditionalOptionsModel() {
        this.hardmode = new SimpleBooleanProperty(false);
        this.timed = new SimpleBooleanProperty(false);
    }

    public boolean isHardmode() {
        return this.hardmode.getValue();
    }

    public boolean isTimed() {
        return this.timed.getValue();
    }

    public BooleanProperty getHardmode() {
        return this.hardmode;
    }

    public BooleanProperty getTimed() {
        return this.timed;
    }
}
