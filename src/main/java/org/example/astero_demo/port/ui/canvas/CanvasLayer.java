package org.example.astero_demo.port.ui.canvas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Container for a {@link Drawable}. CanvasLayers can be nested<p>
 * within each other, creating a tree-like structure
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CanvasLayer<T extends Drawable> implements Drawable, Comparable<CanvasLayer<T>> {
    protected ObservableList<T> children = FXCollections.observableArrayList();
    @Getter
    private int priority;

    public CanvasLayer(final int priority) {
        this.priority = priority;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        children.stream().sorted().forEach(ch -> ch.draw(gc));
    }

    public final Stream<T> getChildren() {
        return children.stream();
    }

    public final void add(final T ch) {
        children.add(ch);
    }

    public final void addAll(final Collection<T> newChildren) {
        children.addAll(newChildren);
    }

    public final void remove(final T ch) {
        children.remove(ch);
    }

    public void removeAll() {
        children = FXCollections.observableArrayList();
    }

    @Override
    public int compareTo(final CanvasLayer o) {
        return Integer.compare(this.priority, o.priority);
    }
}
