package org.example.astero_demo.realization.async.ui.wrappers;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.keyboard.EditorOperationAdapter;
import org.example.astero_demo.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.realization.async.AppExecutor;
import org.example.astero_demo.realization.async.wrappers.AsynchWrapper;

import static org.example.astero_demo.realization.logging.MarkerStorage.USER_INPUT_MARKER;

/**
 * Wrapper class that adapts interactions with an editor's operations
 * by delegating to a wrapped {@link EditorOperationAdapter} instance.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class OperationAdapterWrapper extends AsynchWrapper<OperationAdapter> implements OperationAdapter {

    @Inject
    protected OperationAdapterWrapper(final AppExecutor executor, final EditorOperationAdapter wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void handleDelete() {
        log.debug(USER_INPUT_MARKER, "Delete selected shape");
        executeInBackground(wrappedElement::handleDelete);
    }

    @Override
    public void handleUndo() {
        log.debug(USER_INPUT_MARKER, "Undo last operation");
        executeInBackground(wrappedElement::handleUndo);
    }

    @Override
    public void handleCopy() {
        log.debug(USER_INPUT_MARKER, "Copy selected shape");
        wrappedElement.handleCopy();
    }

    @Override
    public void handlePaste() {
        log.debug(USER_INPUT_MARKER, "Paste shape");
        executeInBackground(wrappedElement::handlePaste);
    }
}
