package org.example.astero_demo.core.port.ui;

import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.model.metadata.ShapeParam;

/**
 * Represents the view for displaying and updating properties in a Properties Panel.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
//TODO: Add onFocusLost()
public abstract class PropertiesPanelView implements PropertiesView {
    private final PropertiesAdapter propertyUpdatable;
    protected final UIState uiState;

    protected PropertiesPanelView(final PropertiesAdapter propertyUpdatable, final UIState uiState) {
        this.propertyUpdatable = propertyUpdatable;
        this.uiState = uiState;
    }

    @Override
    public void update() {
        setUpField(ShapeParam.X,        uiState.getSelectedX());
        setUpField(ShapeParam.Y,        uiState.getSelectedY());
        setUpField(ShapeParam.WIDTH,    uiState.getSelectedWidth());
        setUpField(ShapeParam.HEIGHT,   uiState.getSelectedHeight());
        setUpField(ShapeParam.PRIORITY, uiState.getSelectedLayer());
        setUpField(ShapeParam.COLOR,    uiState.getSelectedColor());
    }


    @Override
    public void switchToInsertMode() {
        clearPanel();
        setPanelDisabled(true);
    }

    @Override
    public void switchToSingleSelectionMode() {
        setPanelDisabled(false);
    }

    @Override
    public void switchToMultipleSelectionMode() {
        clearPanel();
        setPanelDisabled(true);
    }

    protected abstract void setUpField(ShapeParam param, Number value);

    protected abstract void clearPanel();

    protected abstract void setPanelDisabled(boolean isDisabled);

    protected void updateField(final ShapeParam param, final String value) {
        propertyUpdatable.updateField(param, value);
    }
}
