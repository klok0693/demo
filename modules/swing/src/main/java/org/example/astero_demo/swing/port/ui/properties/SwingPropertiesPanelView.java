package org.example.astero_demo.swing.port.ui.properties;

import org.example.astero_demo.core.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;

import java.awt.event.KeyEvent;

/**
 * JavaFX's realization of {@link PropertiesPanelView}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class SwingPropertiesPanelView extends PropertiesPanelView {
    private final PropertiesPanelUI panelUI;

    public SwingPropertiesPanelView(
            final PropertiesAdapter propertyUpdatable,
            final UIState uiState,
            final PropertiesPanelUI panelUI) {
        super(propertyUpdatable, uiState);
        this.panelUI = panelUI;
    }

    @Override
    protected void setPanelDisabled(final boolean isDisabled) {
        panelUI.setDisable(isDisabled);
    }

    @Override
    protected void clearPanel() {
        panelUI.clearPanel();
    }

    @Override
    protected void setUpField(final ShapeParam param, final Number value) {
        panelUI.setUpField(param, value);
    }

/*    public void updateColor(final ActionEvent event) {
        final Color selectedColor = colorField.getValue();
        updateField(ShapeParam.COLOR, String.valueOf(ColorUtils.convert(selectedColor)));
    }*/
}
