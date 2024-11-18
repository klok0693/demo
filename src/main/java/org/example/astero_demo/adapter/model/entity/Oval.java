package org.example.astero_demo.adapter.model.entity;

public class Oval extends Shape {

    public Oval(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(id, priority, x, y, width, height, color);
    }

    public Oval(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(priority, x, y, width, height, color);
    }

    public Oval(
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
