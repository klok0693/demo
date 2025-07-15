package org.example.astero_demo.realization.level.async.ui;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.core.adapter.ui.RootAdapter;
import org.example.astero_demo.core.controller.ui.ControllerAdapter;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.util.logging.MarkerStorage;

import java.util.concurrent.Executor;

/**
 * Wrap calls from {@link ControllerAdapter} using {@link ForegroundExecutor},<p>
 * merging them into non-freezable thread
 */
@Slf4j
public class RootAdapterAsyncWrapper extends AsynchWrapper<ControllerAdapter> implements ControllerAdapter {

    @Inject
    public RootAdapterAsyncWrapper(
            final RootAdapter wrappedElement,
            final ForegroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public void onCreateUpdate(int id) {
        log.debug(MarkerStorage.UI_MARKER, "Update ui on creation of shape {}", id);
        executor.execute(() -> wrappedElement.onCreateUpdate(id));
    }

    @Override
    public void onModifyUpdate(int id) {
        log.debug(MarkerStorage.UI_MARKER, "Update ui on modification of shape {}", id);
        executor.execute(() -> wrappedElement.onModifyUpdate(id));
    }

    @Override
    public void onRemoveUpdate() {
        log.debug(MarkerStorage.UI_MARKER, "Update ui on shape removing");
        executor.execute(wrappedElement::onRemoveUpdate);
    }
}
