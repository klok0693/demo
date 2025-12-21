package org.example.astero_demo.fx.initialization.ui;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.example.astero_demo.core.port.ui.elements.LayersTree;
import org.example.astero_demo.fx.initialization.ui.builder.*;

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

    private final Injector injector;

    private final BuilderFactory defaultBuilderFactory = new JavaFXBuilderFactory();

    @Inject
    public NodeBuilderFactory(
            final Injector injector,
            final CanvasBuilder canvasBuilder,
            final LayersTreeBuilder layersTreeBuilder,
            final LayersPanelBuilder layersPanelBuilder) {
        this.injector = injector;
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
        // !Important: Some ui classes required additional setup
        // after creation, that performed in dedicated builders.
        // Because di container may also contain such classes,
        // this block must follow the builder's blocks,
        // but not being earlier
        if (injector.getExistingBinding(Key.get(aClass)) != null) {
            return new DIBuilder<>(injector, aClass);
        }
        return defaultBuilderFactory.getBuilder(aClass);
    }
}
