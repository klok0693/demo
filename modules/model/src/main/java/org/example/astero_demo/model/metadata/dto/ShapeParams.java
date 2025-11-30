package org.example.astero_demo.model.metadata.dto;

import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.example.astero_demo.model.metadata.ParamInfo.create;

/**
 * DTO class, used to combine multiple {@link ParamInfo} for operations with shape
 *
 * @author Pilip Yurchanka
 * @since 1.1
 */
@ToString
public class ShapeParams {
    private static final String DEFAULT_SHAPE_LAYER = "2"; // TODO: move to shape builder
    private final Map<ShapeParam, ParamInfo> params;

    public ShapeParams(final ShapeParam key, final ParamInfo info) {
        this.params = new EnumMap<>(ShapeParam.class);
        params.put(key, info);
    }

    public ShapeParams(final double x, final double y) {
        this.params = new EnumMap<>(ShapeParam.class);
        params.put(ShapeParam.X, create(ShapeParam.X, x));
        params.put(ShapeParam.Y, create(ShapeParam.Y, y));
    }

    public ShapeParams(
            final double x,
            final double y,
            final double width,
            final double height) {
        this(null, x, y, width, height, null, null);
    }

    public ShapeParams(
            final double x,
            final double y,
            final double width,
            final double height,
            final ShapeType type) {
        this(null, x, y, width, height, null, type);
    }

    public ShapeParams(
            Integer priority,
            double x,
            double y,
            double width,
            double height,
            Integer color,
            ShapeType type) {

        this.params = new EnumMap<>(ShapeParam.class);

        final ParamInfo priorityInfo = create(ShapeParam.PRIORITY, priority == null ? DEFAULT_SHAPE_LAYER : String.valueOf(priority));
        params.put(ShapeParam.PRIORITY, priorityInfo);
        params.put(ShapeParam.X, create(ShapeParam.X, x));
        params.put(ShapeParam.Y, create(ShapeParam.Y, y));
        params.put(ShapeParam.WIDTH, create(ShapeParam.WIDTH, width));
        params.put(ShapeParam.HEIGHT, create(ShapeParam.HEIGHT, height));
        if (color != null) {
            params.put(ShapeParam.COLOR, create(ShapeParam.COLOR, color));
        }
        if (type != null) {
            params.put(ShapeParam.TYPE, create(ShapeParam.TYPE, type.name()));
        }
    }


    public String getNewValue(final ShapeParam key) {
        if (!params.containsKey(key)) {
            return StringUtils.EMPTY;
        }
        return params.get(key).getNewValue();
    }

    public void setNewValue(final ShapeParam key, final String newValue) {
        params.put(key, create(key, newValue));
    }

    public String getOldValue(final ShapeParam key) {
        if (!params.containsKey(key)) {
            return StringUtils.EMPTY;
        }
        return params.get(key).getOldValue();
    }

    public void setOldValue(final ShapeParam key, final String oldValue) {
        params.get(key).setOldValue(oldValue);
    };

    public void forEach(final BiConsumer<? super ShapeParam, ? super ParamInfo> action) {
        params.forEach(action);
    }

    public Stream<ParamInfo> stream() {
        return params.values().stream();
    }
}
