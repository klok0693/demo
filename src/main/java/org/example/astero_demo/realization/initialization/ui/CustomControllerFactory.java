package org.example.astero_demo.realization.initialization.ui;

import com.google.inject.Inject;
import javafx.util.Callback;
import org.example.astero_demo.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.port.ui.LayersPanelView;
import org.example.astero_demo.port.ui.PropertyPanelView;
import org.example.astero_demo.port.ui.RootView;
import org.example.astero_demo.port.ui.ToolBarView;
import org.example.astero_demo.port.ui.canvas.ShapeCanvasView;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private final LayersPanelView layersPanelView;
    private final PropertyPanelView propertyPanelView;
    private final CanvasView canvasView;
    private final ToolBarView toolBarView;
    private final RootView rootView;

    @Inject
    public CustomControllerFactory(
            final LayersPanelView layersPanelView,
            final PropertyPanelView propertyPanelView,
            final CanvasView canvasView,
            final ToolBarView toolBarView,
            final RootView rootView) {
        this.layersPanelView = layersPanelView;
        this.propertyPanelView = propertyPanelView;
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
        if (aClass.isAssignableFrom(PropertyPanelView.class)) {
            return propertyPanelView;
        }
        if (aClass.isAssignableFrom(LayersPanelView.class)) {
            return layersPanelView;
        }
        return null;
    }
}
