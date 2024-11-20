package org.example.astero_demo.realization.async.wrappers.adapter;

import com.google.inject.Inject;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarPanelAdapter;
import org.example.astero_demo.realization.async.AppExecutor;

public class ToolBarAdapterWrapper extends UpdatableAdapterAsyncWrapper<ToolBarAdapter> implements ToolBarAdapter {

    @Inject
    protected ToolBarAdapterWrapper(final AppExecutor executor, final ToolBarPanelAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void onInsertRectAction() {
        executeInBackground(wrappedElement::onInsertRectAction);
    }

    @Override
    public void onInsertCycleAction() {
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
