package org.example.astero_demo.adapter.model;

public class ModelAdapter {
    private final ShapeFactory factory;
    private final StateHolder holder;

    public ModelAdapter(ShapeFactory factory, StateHolder holder) {
        this.factory = factory;
        this.holder = holder;
    }

    public <T extends Number> T getShapeParam(final int id, final ShapeParam param) {
        final Shape shape = holder.getShape(id);
        if (shape == null) {
            return null;
        }
        return switch (param) {
            case X -> (T) shape.getX();
            case Y -> (T) shape.getY();
            case WIDTH -> (T) shape.getWidth();
            case HEIGHT -> (T) shape.getHeight();
            case LAYER -> (T) shape.getPriority();
            default -> null;
        };
    }

    public void modifyShapeParam(final int id, final ShapeParam param, final Number newValue) {
        final Shape shape = holder.getShape(id);
        if (shape == null) {
            return;
        }
        switch (param) {
            case X: shape.setX(newValue.doubleValue()); break;
            case Y: shape.setY(newValue.doubleValue()); break;
            case WIDTH: shape.setWidth(newValue.doubleValue()); break;
            case HEIGHT: shape.setHeight(newValue.doubleValue()); break;
            case LAYER: shape.setPriority(newValue.intValue()); break;
        }
    }

    public int saveShape(final int priority, final double x, final double y, final ShapeType type) {
        final Shape shape = factory.createShape(priority, x, y, type);
        holder.saveShape(shape);
        return shape.getId();
    }

    public Shape removeShape(final int id) {
        final Shape removed = holder.getShape(id);
        holder.removeShape(id);
        return removed;
    }
}
