package org.example.astero_demo.adapter.ui.property;

import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import static org.example.astero_demo.model.metadata.ParamInfo.create;

/**
 * Leaf adapter for interacting with a Properties Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class PropertiesPanelAdapter extends LeafAdapter implements PropertiesAdapter {
    private final PropertiesView propertyView;

    public PropertiesPanelAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final PropertiesView propertyView,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.propertyView = propertyView;
    }

    @Override
    public void update() {
        propertyView.update();
    }

    @Override
    public void switchMode(final UIMode mode) {
        propertyView.switchMode(mode);
    }

    @Override
    public void updateX(final String x) {
        updateField(ShapeParam.X, x);
    }

    @Override
    public void updateY(final String y) {
        updateField(ShapeParam.Y, y);
    }

    @Override
    public void updateWidth(final String width) {
        updateField(ShapeParam.WIDTH, width);
    }

    @Override
    public void updateHeight(final String height) {
        updateField(ShapeParam.HEIGHT, height);
    }

    @Override
    public void updateLayer(final String layer) {
        updateField(ShapeParam.PRIORITY, layer);
    }

    @Override
    public void updateColor(final String color) {
        updateField(ShapeParam.COLOR, color);
    }

    private void updateField(final ShapeParam param, final String value) {
        controller.process(new ModifyShapeEvent(uiState.getSelectedShapeId(), create(param, value)));
    }
}
