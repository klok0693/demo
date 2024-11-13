package org.example.astero_demo.port.ui.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

public class CanvasLayer<T extends Drawable> implements Drawable, Comparable<CanvasLayer<T>> {
    protected ObservableList<T> children = FXCollections.observableArrayList();
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

    public final Stream<T> getChildren() {
        return children.stream();
    }

    public final void add(final T ch) {
        children.add(ch);
    }

    public final void remove(final T ch) {
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
