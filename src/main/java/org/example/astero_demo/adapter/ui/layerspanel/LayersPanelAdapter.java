package org.example.astero_demo.adapter.ui.layerspanel;

import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.event.SelectElementById;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.logic.EventProcessor;

/**
 * Leaf adapter for interacting with a Layers Panel.
 */
public class LayersPanelAdapter extends LeafAdapter implements LayersAdapter {
    private final LayersView layersRoot;

    public LayersPanelAdapter(
            final EventProcessor controller,
            final UIState uiState,
            final LayersView layersRoot,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.layersRoot = layersRoot;
    }

    @Override
    public void update() {
        layersRoot.update();
    }

    @Override
    public void switchMode(final UIMode mode) {
        layersRoot.switchMode(mode);
    }

    @Override
    public void selectShape(final String id) {
        parent.processEvent(new SelectElementById(Integer.parseInt(id)));
    }
}
