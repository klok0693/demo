package org.example.astero_demo.adapter.model;

import java.util.LinkedList;
import java.util.List;

public class ModelAdapter {
    private final ShapeFactory factory;
    private final StateHolder holder;

    public ModelAdapter(ShapeFactory factory, StateHolder holder) {
        this.factory = factory;
        this.holder = holder;
    }

    public int saveShape(final int priority, final double x, final double y, final ShapeType type) {
        final Shape shape = factory.createShape(priority, x, y, type);
        holder.saveShape(shape);
        return shape.getId();
    }

    public void removeShape(final int id) {
        holder.removeShape(id);
    }
}
