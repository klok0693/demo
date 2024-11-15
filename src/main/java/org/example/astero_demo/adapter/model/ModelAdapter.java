package org.example.astero_demo.adapter.model;

public class ModelAdapter {
    private final ShapeFactory factory;
    private final StateHolder holder;

    public ModelAdapter(ShapeFactory factory, StateHolder holder) {
        this.factory = factory;
        this.holder = holder;
    }

    public String getShapeParam(final int id, final ShapeParam param) {
        final Shape shape = holder.getShape(id);
        if (shape == null) {
            return null;
        }
        return switch (param) {
            case X -> shape.getX();
            case Y -> shape.getY();
            case WIDTH -> shape.getWidth();
            case HEIGHT -> shape.getHeight();
            case LAYER -> shape.getPriority();
            case COLOR -> shape.getColor();
            default -> null;
        };
    }

    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        final Shape shape = holder.getShape(id);
        if (shape == null) {
            return;
        }
        switch (param) {
            case X: shape.setX(newValue); break;
            case Y: shape.setY(newValue); break;
            case WIDTH: shape.setWidth(newValue); break;
            case HEIGHT: shape.setHeight(newValue); break;
            case LAYER: shape.setPriority(newValue); break;
            case COLOR: shape.setColor(newValue);
        }
    }

    public int saveShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {
        final Shape shape = factory.createShape(priority, x, y, width, height, type);
        holder.saveShape(shape);
        return shape.getId();
    }

    public Shape removeShape(final int id) {
        final Shape removed = holder.getShape(id);
        holder.removeShape(id);
        return removed;
    }
}
