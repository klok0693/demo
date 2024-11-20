package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

public class LayersPanelAdapterWrapper extends UpdatableAdapterAsyncWrapper<LayersAdapter> implements LayersAdapter {
    //public TreeView<String> layersTree;

    @Inject
    protected LayersPanelAdapterWrapper(final AppExecutor executor, final LayersPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public boolean hasInjectedField(final String fieldName) {
        return super.hasInjectedField(fieldName) || "layersRoot".equals(fieldName);
    }

    @Override
    public void selectShape(final String id) {
        wrappedElement.selectShape(id);
    }
}
