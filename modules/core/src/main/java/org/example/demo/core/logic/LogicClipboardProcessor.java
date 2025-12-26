package org.example.demo.core.logic;

import org.example.demo.core.adapter.clipboard.ClipboardAdapter;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.model.entity.Shape;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.model.metadata.dto.ShapeParams;

import static java.lang.String.valueOf;

public class LogicClipboardProcessor implements ClipboardProcessor {
    private final ClipboardAdapter adapter;
    private final ShapeProcessor shapeProcessor;

    private final ModelState modelState;

    public LogicClipboardProcessor(
            final ClipboardAdapter adapter,
            final ShapeProcessor shapeProcessor,
            final ModelState modelState) {
        this.adapter = adapter;
        this.shapeProcessor = shapeProcessor;
        this.modelState = modelState;
    }

    @Override
    public void copy(final Shape shape) {
        adapter.put(shape);
    }

    @Override
    public void cut(final int id) {
        adapter.put(modelState.getShape(id));
        shapeProcessor.removeShape(id);
    }

    @Override
    public void paste(double x, double y) {
        if (!adapter.hasCopy()) {
            return;
        }
        final ShapeParams params = adapter.getShapeParams();
        params.setNewValue(ShapeParam.X, valueOf(x));
        params.setNewValue(ShapeParam.Y, valueOf(y));
        shapeProcessor.createShape(params);
    }
}
