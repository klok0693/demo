package org.example.astero_demo.port.ui.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

public class CanvasLayer implements Drawable, Comparable<CanvasLayer> {
    protected ObservableList<Drawable> children = FXCollections.observableArrayList();
    @Getter
    private int priority;

    public CanvasLayer(final GraphicsContext gc, final int priority) {
        this.priority = priority;
        children.addListener(new WeakListChangeListener<>(change -> {
            draw(gc);
        }));
    }

    @Override
    public void draw(final GraphicsContext gc) {
        children.forEach(ch -> ch.draw(gc));
    }

    public void add(final Drawable ch) {
        children.add(ch);
    }

    public void remove(final Drawable ch) {
        children.remove(ch);
    }

    public void removeAll() {
        children.forEach(Drawable::destroyLinks);
        children = FXCollections.observableArrayList();
    }

    @Override
    public void destroyLinks() {
        removeAll();
    }

    @Override
    public int compareTo(final CanvasLayer o) {
        return Integer.compare(this.priority, o.priority);
    }
}
