package org.example.astero_demo.adapter.model.entity;

import javax.annotation.Nullable;

/**
 * Factory for creating different shapes based on the provided parameters.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public enum ShapeFactory {
    INSTANCE;

    /**
     * Creates a {@link Shape} based on the provided parameters and type.<p>
     * If id is null, it would be generated
     */
    public Shape createShape(
            @Nullable final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {

        return switch (type) {
            case RECT -> {
                if (id != null) {
                    yield new Rectangle(id, priority, x, y, width, height, color);
                }
                else {
                    yield new Rectangle(priority, x, y, width, height, color);
                }
            }
            case ELLIPSE -> {
                if (id != null) {
                    yield new Ellipse(id, priority, x, y, width, height, color);
                }
                else {
                    yield new Ellipse(priority, x, y, width, height, color);
                }
            }
        };
    }
}
