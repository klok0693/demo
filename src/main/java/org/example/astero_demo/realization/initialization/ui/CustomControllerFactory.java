package org.example.astero_demo.realization.initialization.ui;

import com.google.inject.Inject;
import javafx.util.Callback;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.canvas.ShapeCanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersPanelAdapter;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private final LayersAdapter layersAdapter;
    private final PropertyPanelAdapter propertyAdapter;
    private final CanvasAdapter canvasAdapter;
    private final ToolBarAdapter toolBarAdapter;
    private final RootAdapter rootAdapter;

    @Inject
    public CustomControllerFactory(
            final LayersAdapter layersAdapter,
            final PropertyPanelAdapter propertyAdapter,
            final CanvasAdapter canvasAdapter,
            final ToolBarAdapter toolBarAdapter,
            final RootAdapter rootAdapter) {
        this.layersAdapter = layersAdapter;
        this.propertyAdapter = propertyAdapter;
        this.canvasAdapter = canvasAdapter;
        this.toolBarAdapter = toolBarAdapter;
        this.rootAdapter = rootAdapter;
    }

    @Override
    public Object call(Class<?> aClass) {
        if (aClass.isAssignableFrom(ShapeCanvasAdapter.class)) {
            return canvasAdapter;
        }
        if (aClass.isAssignableFrom(ToolBarAdapter.class)) {
            return toolBarAdapter;
        }
        if (aClass.isAssignableFrom(RootAdapter.class)) {
            return rootAdapter;
        }
        if (aClass.isAssignableFrom(PropertyPanelAdapter.class)) {
            return propertyAdapter;
        }
        if (aClass.isAssignableFrom(LayersPanelAdapter.class)) {
            return layersAdapter;
        }
        return null;
    }
}
