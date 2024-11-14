package org.example.astero_demo.adapter.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class Shape {
    @Getter
    private final Integer id;
    @Getter @Setter
    private Integer priority;
    @Getter @Setter
    private Double x, y, width, height;
    @Getter @Setter
    private Integer color;

    protected Shape(final int priority, final double x, final double y) {
        this(priority, x, y, 100, 100);
    }

    protected Shape(final int priority, final double x, final double y, final double width, final double height) {
        id = (int) (Math.random() * 10_000);
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract ShapeType getType();
}
