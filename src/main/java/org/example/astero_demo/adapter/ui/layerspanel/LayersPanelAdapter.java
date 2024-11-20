package org.example.astero_demo.adapter.ui.layerspanel;

import org.example.astero_demo.adapter.ui.LeafAdapter;
import org.example.astero_demo.adapter.ui.UpdatableView;
import org.example.astero_demo.adapter.ui.event.SelectElementById;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

public class LayersPanelAdapter extends LeafAdapter implements LayersAdapter {

    public UpdatableView layersRoot;

    public LayersPanelAdapter(
            final ViewController controller,
            final UIState uiState,
            final UpdatableView layersRoot) {
        super(controller, uiState);
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
