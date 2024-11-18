package org.example.astero_demo.initialization;

import com.google.inject.Inject;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.util.HashMap;

public class NodeBuilderFactory implements BuilderFactory {
    private final CanvasView canvasView;

    private final BuilderFactory defaultBuilderFactory = new JavaFXBuilderFactory();

    @Inject
    public NodeBuilderFactory(final CanvasView view) {
        this.canvasView = view;
    }

    @Override
    public Builder<?> getBuilder(final Class<?> aClass) {
        if (Canvas.class.isAssignableFrom(aClass)) {
            return new CanvasBuilder(canvasView);
        }
        return defaultBuilderFactory.getBuilder(aClass);
    }

    static class CanvasBuilder extends HashMap<String, String> implements Builder<Canvas> {
        private final CanvasView canvasView;

        CanvasBuilder(final CanvasView canvasView) {
            this.canvasView = canvasView;
        }

        @Override
        public Canvas build() {
            canvasView.setWidth(Double.parseDouble(get("width")));
            canvasView.setHeight(Double.parseDouble(get("height")));
            return canvasView;
        }
    }
}
