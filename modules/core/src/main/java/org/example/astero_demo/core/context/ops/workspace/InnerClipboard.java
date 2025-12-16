package org.example.astero_demo.core.context.ops.workspace;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.core.adapter.clipboard.Clipboard;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static org.example.astero_demo.model.metadata.ParamInfo.create;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class InnerClipboard implements Clipboard<Shape, ShapeParams> {
    private final List<ParamInfo> storedParams = new LinkedList<>();

    @Override
    public void put(final Shape shape) {
        clear();
        if (shape == null) {
            return;
        }

        storedParams.add(create(ShapeParam.X, shape.getX()));
        storedParams.add(create(ShapeParam.Y, shape.getY()));
        storedParams.add(create(ShapeParam.WIDTH, shape.getWidth()));
        storedParams.add(create(ShapeParam.HEIGHT, shape.getHeight()));
        storedParams.add(create(ShapeParam.COLOR, shape.getColor()));
        storedParams.add(create(ShapeParam.PRIORITY, shape.getPriority()));
        storedParams.add(create(ShapeParam.TYPE, valueOf(shape.getType())));
    }

    @Nullable
    @Override
    public ShapeParams get() {
        if (storedParams.isEmpty()) {
            return null;
        }

        final ShapeParams params = new ShapeParams(ShapeParam.PRIORITY,
                ParamInfo.create(ShapeParam.PRIORITY, parseInt(getCopyParam(ShapeParam.PRIORITY))));

        params.setNewValue(ShapeParam.WIDTH, getCopyParam(ShapeParam.WIDTH));
        params.setNewValue(ShapeParam.HEIGHT, getCopyParam(ShapeParam.HEIGHT));
        params.setNewValue(ShapeParam.COLOR, getCopyParam(ShapeParam.COLOR));
        params.setNewValue(ShapeParam.TYPE, getCopyParam(ShapeParam.TYPE));

        return params;
    }

    private String getCopyParam(final ShapeParam param) {
        if (storedParams.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return storedParams.stream()
                .filter(paramInfo -> paramInfo.getParam() == param)
                .map(ParamInfo::getNewValue)
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public boolean hasCopy() {
        return !storedParams.isEmpty();
    }

    @Override
    public void clear() {
        storedParams.clear();
    }
}
