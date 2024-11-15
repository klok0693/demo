package org.example.astero_demo.logic.event.ui;

import lombok.Getter;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.ShapeType;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.ParamInfo.create;

@Getter
public class CreateNewShapeEvent extends ParamEvent {
/*    private final int priority;
    private final double x, y, width, height;
    private final ShapeType type;*/

    public CreateNewShapeEvent(
            final double x,
            final double y,
            final double width,
            final double height,
            final ShapeType type) {

        this(null, x, y, width, height, null, type);

/*        this.priority = 2;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;*/
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

        info[0] = create(ShapeParam.PRIORITY, "2");
        info[1] = create(ShapeParam.X, valueOf(x));
        info[2] = create(ShapeParam.Y, valueOf(y));
        info[3] = create(ShapeParam.WIDTH, valueOf(width));
        info[4] = create(ShapeParam.HEIGHT, valueOf(height));
        info[5] = create(ShapeParam.TYPE, type.name());

        if (priority != null) {
            info[6] = create(ShapeParam.PRIORITY, valueOf(priority));
        }

        if (color != null) {
            info[7] = create(ShapeParam.COLOR, valueOf(color));
        }

        return info;
    }
}
