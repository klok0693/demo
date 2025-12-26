package org.example.demo.fx.initialization.ui;

import com.google.inject.Inject;
import javafx.util.Callback;
import org.example.demo.core.adapter.ui.canvas.CanvasView;
import org.example.demo.core.port.ui.LayersPanelView;
import org.example.demo.core.port.ui.PropertiesPanelView;
import org.example.demo.core.port.ui.RootView;
import org.example.demo.core.port.ui.ToolBarPanelView;
import org.example.demo.fx.port.ui.canvas.FxShapeCanvasView;

/**
 * To be able to use DI in our controllers, we need to override<p>
 * the controller factory to retrieve entities from the dependency container
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private final LayersPanelView layersPanelView;
    private final PropertiesPanelView propertiesPanelView;
    private final CanvasView canvasView;
    private final ToolBarPanelView toolBarView;
    private final RootView rootView;

    @Inject
    public CustomControllerFactory(
            final LayersPanelView layersPanelView,
            final PropertiesPanelView propertiesPanelView,
            final CanvasView canvasView,
            final ToolBarPanelView toolBarView,
            final RootView rootView) {
        this.layersPanelView = layersPanelView;
        this.propertiesPanelView = propertiesPanelView;
        this.canvasView = canvasView;
        this.toolBarView = toolBarView;
        this.rootView = rootView;
    }

    @Override
    public Object call(final Class<?> aClass) {
        if (aClass.isAssignableFrom(FxShapeCanvasView.class)) {
            return canvasView;
        }
        if (aClass.isAssignableFrom(ToolBarPanelView.class)) {
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
