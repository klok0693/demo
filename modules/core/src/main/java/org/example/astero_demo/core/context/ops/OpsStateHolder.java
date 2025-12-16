package org.example.astero_demo.core.context.ops;

import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * An application can manage multiple operation states simultaneously.<p>
 * This class serves as a container for all states and implements the same<p>
 * interface {@link OpsState}, enabling it to be passed to components in place<p>
 * of an individual state. To switch states, the holder's current state is<p>
 * updated, and the adapters are refreshed. This approach ensures seamless<p>
 * state transitions without the adapters being directly affected.<p>
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public enum OpsStateHolder implements OpsState {
    INSTANCE(new OpsStateInstance());

    private final OpsState nestedState;

    OpsStateHolder(final OpsState nestedState) {
        this.nestedState = nestedState;
    }

    @Override
    public void put(final Shape obj) {
        nestedState.put(obj);
    }

    @Override
    public ShapeParams get() {
        return nestedState.get();
    }

    @Override
    public boolean hasCopy() {
        return nestedState.hasCopy();
    }

    @Override
    public void clear() {
        nestedState.clear();
    }
}
