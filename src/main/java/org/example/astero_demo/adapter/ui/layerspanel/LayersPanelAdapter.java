package org.example.astero_demo.adapter.ui.layerspanel;

import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.ParentAdapter;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.event.SelectElementById;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.LogicEventProcessor;

public class LayersPanelAdapter extends LeafAdapter implements LayersAdapter {

    public UpdatableView layersRoot;

    public LayersPanelAdapter(
            final LogicEventProcessor controller,
            final UIState uiState,
            final UpdatableView layersRoot,
            final ParentAdapter parentAdapter) {
        super(controller, uiState, parentAdapter);
        this.layersRoot = layersRoot;
    }

    @Override
    public void update() {
        layersRoot.update();
    }

    @Override
    public void selectShape(final String id) {
        parent.processEvent(new SelectElementById(Integer.parseInt(id)));
    }
}
