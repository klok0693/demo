package org.example.astero_demo.realization.initialization.ui;

import com.google.inject.Inject;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.example.astero_demo.port.ui.LayersPanel;
import org.example.astero_demo.port.ui.elements.LayersTree;
import org.example.astero_demo.realization.initialization.ui.builder.CanvasBuilder;
import org.example.astero_demo.realization.initialization.ui.builder.LayersPanelBuilder;
import org.example.astero_demo.realization.initialization.ui.builder.LayersTreeBuilder;

import java.util.HashMap;

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
        if (LayersPanel.class.isAssignableFrom(aClass)) {
            return layersPanelBuilder;
        }
        return defaultBuilderFactory.getBuilder(aClass);
    }
}
