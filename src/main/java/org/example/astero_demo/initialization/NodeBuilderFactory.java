package org.example.astero_demo.initialization;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.example.astero_demo.port.ui.canvas.CanvasView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NodeBuilderFactory implements BuilderFactory {

    private final BuilderFactory defaultBuilderFactory = new JavaFXBuilderFactory();

    @Override
    public Builder<?> getBuilder(final Class<?> aClass) {
        if (Canvas.class.isAssignableFrom(aClass)) {
            return new CanvasBuilder();
        }
        return defaultBuilderFactory.getBuilder(aClass);
    }

    static class CanvasBuilder extends HashMap<String, String> implements Builder<Canvas> {

        @Override
        public Canvas build() {
            final Canvas canvas = new CanvasView();
            canvas.setWidth(Double.parseDouble(get("width")));
            canvas.setHeight(Double.parseDouble(get("height")));
            return canvas;
        }
    }
}
