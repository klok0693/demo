package org.example.astero_demo.swing.port.ui.properties;

import org.example.astero_demo.model.metadata.ShapeParam;

public interface PropertiesPanelUI {

    void setDisable(boolean isDisable);

    void clearPanel();

    void setUpField(ShapeParam param, Number value);
}
