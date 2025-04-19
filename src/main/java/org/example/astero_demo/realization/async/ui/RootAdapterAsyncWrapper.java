package org.example.astero_demo.realization.async.ui;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ui.ControllerAdapter;
import org.example.astero_demo.realization.async.AsynchWrapper;
import org.example.astero_demo.realization.logging.MarkerStorage;

@Slf4j
public class RootAdapterAsyncWrapper extends AsynchWrapper<ControllerAdapter> implements ControllerAdapter {

    @Inject
    protected RootAdapterAsyncWrapper(
            final FXExecutor executor,
            final RootAdapter wrappedElement) {
        super(executor, wrappedElement);
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
