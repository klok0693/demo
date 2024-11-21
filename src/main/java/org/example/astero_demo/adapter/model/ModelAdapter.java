package org.example.astero_demo.adapter.model;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeFactory;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.model.state.MutableModelState;

public class ModelAdapter {
    private final ShapeFactory factory;
    private final MutableModelState modelState;

    public ModelAdapter(final ShapeFactory factory, final MutableModelState modelState) {
        this.factory = factory;
        this.modelState = modelState;
    }

    public String getShapeParam(final int id, final ShapeParam param) {
        final Shape shape = modelState.getShape(id);
        if (shape == null) {
            return null;
        }
        return switch (param) {
            case X -> shape.getX();
            case Y -> shape.getY();
            case WIDTH -> shape.getWidth();
            case HEIGHT -> shape.getHeight();
            case PRIORITY -> shape.getPriority();
            case COLOR -> shape.getColor();
            default -> null;
        };
    }

    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        final Shape shape = modelState.getShape(id);
        if (shape == null) {
            return;
        }
        switch (param) {
            case X: shape.setX(newValue); break;
            case Y: shape.setY(newValue); break;
            case WIDTH: shape.setWidth(newValue); break;
            case HEIGHT: shape.setHeight(newValue); break;
            case PRIORITY: shape.setPriority(newValue); break;
            case COLOR: shape.setColor(newValue);
        }
    }

    public int saveShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {

        return this.saveShape(null, priority, x, y, width, height, color, type);
    }

    public int saveShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {
        final Shape shape = factory.createShape(id, priority, x, y, width, height, color, type);
        modelState.saveShape(shape);
        return shape.getId();
    }

    public Shape removeShape(final int id) {
        final Shape removed = modelState.getShape(id);
        modelState.removeShape(id);
        return removed;
    }
}
