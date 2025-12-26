package org.example.astero_demo.realization.level.async.clipboard;

import com.google.inject.Inject;
import org.example.astero_demo.core.logic.ClipboardProcessor;
import org.example.astero_demo.core.logic.LogicClipboardProcessor;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.realization.level.async.BackgroundExecutor;

public class ClipboardProcessorAsyncWrapper
        extends AsynchWrapper<ClipboardProcessor, BackgroundExecutor>
        implements ClipboardProcessor {

    @Inject
    public ClipboardProcessorAsyncWrapper(
            final LogicClipboardProcessor wrappedElement,
            final BackgroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public void copy(final Shape shape) {
        executor.execute(() -> wrappedElement.copy(shape));
    }

    @Override
    public void cut(final int id) {
        executor.execute(() -> wrappedElement.cut(id));
    }

    @Override
    public void paste(final double x, final double y) {
        executor.execute(() -> wrappedElement.paste(x, y));
    }
}
