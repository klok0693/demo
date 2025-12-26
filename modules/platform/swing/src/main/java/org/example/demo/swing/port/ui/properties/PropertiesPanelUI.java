package org.example.demo.swing.port.ui.properties;

import org.example.demo.model.metadata.ShapeParam;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public interface PropertiesPanelUI {

    void setDisable(boolean isDisable);

    void clearPanel();

    void setUpField(ShapeParam param, Number value);
}
