package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

/**
 * Represents an event for creating a new shape with specified parameters.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
public class CreateNewShapeEvent extends ParamEvent {
    private static final String DEFAULT_SHAPE_LAYER = "2";

    public CreateNewShapeEvent(
            final double x,
            final double y,
            final double width,
            final double height,
            final ShapeType type) {
        this(null, x, y, width, height, null, type);
    }

    public CreateNewShapeEvent(
            final Integer priority,
            final double x,
            final double y,
            final double width,
            final double height,
            final Integer color,
            final ShapeType type) {
        super(createInfo(priority, x, y, width, height, color, type));
    }

    private static ParamInfo[] createInfo(
            final Integer priority,
            final double x,
            final double y,
            final double width,
            final double height,
            final Integer color,
            final ShapeType type) {

        final ParamInfo[] info = new ParamInfo[10];
        info[0] = create(ShapeParam.PRIORITY, DEFAULT_SHAPE_LAYER);
        info[1] = create(ShapeParam.X, x);
        info[2] = create(ShapeParam.Y, y);
        info[3] = create(ShapeParam.WIDTH, width);
        info[4] = create(ShapeParam.HEIGHT, height);
        info[5] = create(ShapeParam.TYPE, type.name());
        if (priority != null) {
            info[6] = create(ShapeParam.PRIORITY, priority);
        }
        if (color != null) {
            info[7] = create(ShapeParam.COLOR, color);
        }
        return info;
    }
}
