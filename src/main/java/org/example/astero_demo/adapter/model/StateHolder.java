package org.example.astero_demo.adapter.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public enum StateHolder {
    INSTANCE;

    private final HashMap<Integer, Shape> shapes = new HashMap<>(32);

    public void saveShape(final Shape shape) {
        shapes.put(shape.getId(), shape);
    }

    public void removeShape(final int id) {
        shapes.remove(id);
    }

    public Collection<Shape> getShapes() {
        return shapes.values();
    }
}
