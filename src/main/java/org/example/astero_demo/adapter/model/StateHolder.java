package org.example.astero_demo.adapter.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum StateHolder {
    INSTANCE;

    private final HashMap<Integer, Shape> shapes = new HashMap<>(32);

    public Shape getShape(final int id) {
        return shapes.get(id);
    }

    public void saveShape(final Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    public void removeShape(final int id) {
        shapes.remove(id);
    }

    public Stream<Shape> getShapes() {
        return shapes.values().stream();
    }
}
