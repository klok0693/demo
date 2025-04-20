package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;

import java.util.Collection;

import static org.example.astero_demo.model.metadata.ParamInfo.create;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
@UtilityClass
public class ParamUtils {
    private static final String DEFAULT_SHAPE_LAYER = "2";

    public static String getParamInfo(final Collection<ParamInfo> paramInfos, final ShapeParam param) {
        return paramInfos.stream()
                .filter(info -> info.getParam() == param)
                .map(ParamInfo::getNewValue)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    public static ParamInfo[] createParams(
            final double x,
            final double y,
            final double width,
            final double height,
            final ShapeType type) {
        return createParams(null, x, y, width, height, null, type);
    };

    public static ParamInfo[] createParams(
            Integer priority,
            double x,
            double y,
            double width,
            double height,
            Integer color,
            ShapeType type) {

        final ParamInfo[] info = new ParamInfo[10];

        info[0] = create(ShapeParam.PRIORITY, priority == null ? DEFAULT_SHAPE_LAYER : String.valueOf(priority));
        info[1] = create(ShapeParam.X, x);
        info[2] = create(ShapeParam.Y, y);
        info[3] = create(ShapeParam.WIDTH, width);
        info[4] = create(ShapeParam.HEIGHT, height);
        if (color != null) {
            info[5] = create(ShapeParam.COLOR, color);
        }
        info[6] = create(ShapeParam.TYPE, type.name());
        return info;
    }
}
