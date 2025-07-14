package org.example.astero_demo.core.port.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.util.ColorUtils;

/**
 * Represents the view for displaying and updating properties in a Properties Panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
//TODO: Add onFocusLost()
public abstract class PropertiesPanelView implements PropertiesView {
    protected final PropertiesAdapter propertyUpdatable;
    protected final UIState uiState;

    public PropertiesPanelView(final PropertiesAdapter propertyUpdatable, final UIState uiState) {
        this.propertyUpdatable = propertyUpdatable;
        this.uiState = uiState;
    }

    @Override
    public void switchToInsertMode() {
        clearPanel();
    }

    @Override
    public void switchToMultipleSelectionMode() {
        clearPanel();
    }

    protected abstract void clearPanel();
}
