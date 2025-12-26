package org.example.astero_demo.realization.level.async.logic;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.model.metadata.dto.ShapeParams;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.realization.level.async.BackgroundExecutor;

/**
 * Wrap calls to {@link ShapeProcessor}, to execute them in a separate thread
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class EventProcessorAsyncWrapper
        extends AsynchWrapper<ShapeProcessor, BackgroundExecutor>
        implements ShapeProcessor {

    @Inject
    public EventProcessorAsyncWrapper(
            final LogicShapeProcessor wrappedElement,
            final BackgroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public void createShape(final ShapeParams shapeParams) {
        executor.execute(() -> wrappedElement.createShape(shapeParams));
    }

    @Override
    public void modifyShape(final int shapeId, final ShapeParams shapeParams) {
        executor.execute(() -> wrappedElement.modifyShape(shapeId, shapeParams));
    }

    @Override
    public void removeShape(final int id) {
        executor.execute(() -> wrappedElement.removeShape(id));
    }

    @Override
    public void undoLastOperation() {
        executor.execute(wrappedElement::undoLastOperation);
    }
}
