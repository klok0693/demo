package org.example.astero_demo.core.port.ui.canvas;

import lombok.Getter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Container for a {@link Drawable}. CanvasLayers can be nested<p>
 * within each other, creating a tree-like structure
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class CanvasLayer<E, T extends Drawable<E>> implements Drawable<E>, Comparable<CanvasLayer<E,T>> {
    @Getter
    private final int priority;
    protected List<T> children;

    protected CanvasLayer(final int priority) {
        this.priority = priority;
        this.children = new LinkedList<>();
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
        children.removeIf(ch -> true);
        children = new LinkedList<>();
    }

    @Override
    public void draw(final E gc) {
        children.stream().sorted().forEach(ch -> ch.draw(gc));
    }

    @Override
    public int compareTo(final CanvasLayer o) {
        return Integer.compare(this.priority, o.priority);
    }
}
