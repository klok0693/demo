package org.example.astero_demo.fx.initialization.ui;

import com.google.inject.Inject;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.fx.initialization.ui.builder.CanvasBuilder;
import org.example.astero_demo.fx.initialization.ui.builder.LayersPanelBuilder;
import org.example.astero_demo.fx.initialization.ui.builder.LayersTreeBuilder;

/**
 * To be able to use DI in our ui elements, we need to override<p>
 * the {@link BuilderFactory} to retrieve entities from the dependency container
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class NodeBuilderFactory implements BuilderFactory {
    private final CanvasBuilder canvasBuilder;
    private final LayersTreeBuilder layersTreeBuilder;
    private final LayersPanelBuilder layersPanelBuilder;

    private final BuilderFactory defaultBuilderFactory = new JavaFXBuilderFactory();

    @Inject
    public NodeBuilderFactory(
            final CanvasBuilder canvasBuilder,
            final LayersTreeBuilder layersTreeBuilder,
            final LayersPanelBuilder layersPanelBuilder) {
        this.canvasBuilder = canvasBuilder;
        this.layersTreeBuilder = layersTreeBuilder;
        this.layersPanelBuilder = layersPanelBuilder;
    }

    @Override
    public Builder<?> getBuilder(final Class<?> aClass) {
        if (Canvas.class.isAssignableFrom(aClass)) {
            return canvasBuilder;
        }
        if (LayersTree.class.isAssignableFrom(aClass)) {
            return layersTreeBuilder;
        }
        return defaultBuilderFactory.getBuilder(aClass);
    }
}
