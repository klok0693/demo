package org.example.astero_demo.realization.async.wrappers.adapter;

import org.example.astero_demo.adapter.ui.UpdatableAdapter;
import org.example.astero_demo.realization.async.AppExecutor;
import org.example.astero_demo.realization.async.wrappers.AsynchWrapper;

/**
 * Wraps the execution of the update method in an asynchronous manner.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class UpdatableAdapterAsyncWrapper<T extends UpdatableAdapter> extends AsynchWrapper<T> implements UpdatableAdapter {

    protected UpdatableAdapterAsyncWrapper(final AppExecutor executor, final T wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void update() {
        executeInFXThread(wrappedElement::update);
    }
}
