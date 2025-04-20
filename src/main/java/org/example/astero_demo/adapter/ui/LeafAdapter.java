package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.ui.event.UIEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.logic.EventProcessor;

/**
 * Base class for all leaf adapters - adapters, that produced, but cannot process {@link UIEvent},<p>
 * delegating it to the {@link ParentAdapter}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class LeafAdapter extends UIAdapter<UIState> implements UpdatableAdapter {
    protected final ParentAdapter parent;

    protected LeafAdapter(
            final EventProcessor controller,
            final UIState uiState,
            final ParentAdapter parentAdapter) {
        super(controller, uiState);
        parent = parentAdapter;
    }

    protected void sendEvent(final UIEvent event) {
        parent.processEvent(event);
    }
}
