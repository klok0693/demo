package org.example.astero_demo.port.ui.canvas;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class CanvasView extends Canvas {
    private ObservableList<Drawable> layers = FXCollections.observableArrayList();

    private final ChangeListener<Boolean> redrawListener = new WeakChangeListener<>((observableValue, aBoolean, t1) -> {
        if (aBoolean) {
            redraw();
        }
    });

    public CanvasView() {
        final CanvasLayer backgroundLayer = new CanvasLayer(getGraphicsContext2D());
        backgroundLayer.add(new CanvasBackground());
        layers.add(backgroundLayer);

        visibleProperty().addListener(redrawListener);
        focusedProperty().addListener(redrawListener);

        redraw();
    }

    public final void redraw() {
        Platform.runLater(() -> {
            layers.forEach(layer -> layer.draw(getGraphicsContext2D()));
        });
    }

    private interface Drawable {
        void draw(GraphicsContext gc);
    }

    private class CanvasLayer implements Drawable {
        private ObservableList<Drawable> children = FXCollections.observableArrayList();

        protected CanvasLayer(final GraphicsContext gc) {
            children.addListener(new WeakListChangeListener<>(change -> {
                draw(gc);
            }));
        }

        @Override
        public void draw(final GraphicsContext gc) {
            children.forEach(ch -> ch.draw(gc));
        }

        void add(Drawable ch) {
            children.add(ch);
        }

        void remove(Drawable ch) {
            children.remove(ch);
        }
    }

    class CanvasBackground implements Drawable {

        @Override
        public void draw(final GraphicsContext gc) {
            final int border = 10;
            final int width = 630;
            final int height = 380;
            final int cellWidth = 25;

            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(border, border, width, height);

            gc.setFill(Color.WHITE);
            for (int i = border; i < width; i+=cellWidth) {
                int startJ = border;
                if ((i / cellWidth) % 2 != 0) {
                    startJ = cellWidth + border;
                }
                for (int j = startJ; j < height; j+=(cellWidth << 1)) {
                    gc.fillRect(i, j, cellWidth, cellWidth);
                }
            }
        }
    }
}
