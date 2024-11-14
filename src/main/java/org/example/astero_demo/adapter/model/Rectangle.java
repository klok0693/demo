package org.example.astero_demo.adapter.model;

public class Rectangle extends Shape {

    protected Rectangle(final String priority, final String x, final String y) {
        super(priority, x, y);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECT;
    }
}
