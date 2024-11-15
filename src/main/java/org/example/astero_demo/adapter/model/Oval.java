package org.example.astero_demo.adapter.model;

public class Oval extends Shape {

    protected Oval(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        super(id, priority, x, y, width, height);
    }

    protected Oval(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        super(priority, x, y, width, height);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.OVAL;
    }
}
