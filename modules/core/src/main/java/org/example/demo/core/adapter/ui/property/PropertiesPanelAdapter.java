package org.example.demo.core.adapter.ui.property;

import org.example.demo.core.adapter.ui.state.mode.UIMode;
import org.example.demo.core.logic.ShapeProcessor;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.core.adapter.ui.LeafAdapter;
import org.example.demo.core.adapter.ui.ParentAdapter;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.model.metadata.dto.ShapeParams;

import static org.example.demo.model.metadata.ParamInfo.create;

/**
 * Leaf adapter for interacting with a Properties Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class PropertiesPanelAdapter extends LeafAdapter implements PropertiesAdapter {
    private final PropertiesView propertyView;

    public PropertiesPanelAdapter(
            final ShapeProcessor controller,
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
    public void updateField(final ShapeParam param, final String value) {
        processor.modifyShape(uiState.getSelectedShapeId(), new ShapeParams(param, create(param, value)));
    }
}
