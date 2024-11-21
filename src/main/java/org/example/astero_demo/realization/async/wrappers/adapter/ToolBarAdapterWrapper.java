package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

import static org.example.astero_demo.realization.logging.MarkerStorage.USER_INPUT_MARKER;

/**
 * Wrapper class that adapts interactions with a toolbar panel
 * by delegating to a wrapped {@link ToolBarPanelAdapter} instance.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class ToolBarAdapterWrapper extends UpdatableAdapterAsyncWrapper<ToolBarAdapter> implements ToolBarAdapter {

    @Inject
    protected ToolBarAdapterWrapper(final AppExecutor executor, final ToolBarPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void onInsertRectAction() {
        log.debug(USER_INPUT_MARKER, "Insert new Rect");
        executeInBackground(wrappedElement::onInsertRectAction);
    }

    @Override
    public void onInsertCycleAction() {
        log.debug(USER_INPUT_MARKER, "Insert new Cycle");
        executeInBackground(wrappedElement::onInsertCycleAction);
    }

    @Override
    public void onDeleteAction() {
        executeInBackground(wrappedElement::onDeleteAction);
    }

    @Override
    public void onUndoAction() {
        executeInBackground(wrappedElement::onUndoAction);
    }
}
