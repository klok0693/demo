package org.example.astero_demo.realization.initialization.ui;

import com.google.inject.Inject;
import javafx.util.Callback;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.PropertiesPanelView;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.ToolBarView;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private final LayersPanelView layersPanelView;
    private final PropertiesPanelView propertiesPanelView;
    private final CanvasView canvasView;
    private final ToolBarView toolBarView;
    private final RootView rootView;

    @Inject
    public CustomControllerFactory(
            final LayersPanelView layersPanelView,
            final PropertiesPanelView propertiesPanelView,
            final CanvasView canvasView,
            final ToolBarView toolBarView,
            final RootView rootView) {
        this.layersPanelView = layersPanelView;
        this.propertiesPanelView = propertiesPanelView;
        this.canvasView = canvasView;
        this.toolBarView = toolBarView;
        this.rootView = rootView;
    }

    @Override
    public Object call(Class<?> aClass) {
        if (aClass.isAssignableFrom(ShapeCanvasView.class)) {
            return canvasView;
        }
        if (aClass.isAssignableFrom(ToolBarView.class)) {
            return toolBarView;
        }
        if (aClass.isAssignableFrom(RootView.class)) {
            return rootView;
        }
        if (aClass.isAssignableFrom(PropertiesPanelView.class)) {
            return propertiesPanelView;
        }
        if (aClass.isAssignableFrom(LayersPanelView.class)) {
            return layersPanelView;
        }
        return null;
    }
}
