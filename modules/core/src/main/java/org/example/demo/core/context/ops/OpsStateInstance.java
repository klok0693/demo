package org.example.demo.core.context.ops;

import org.example.demo.core.context.ops.workspace.InnerClipboard;
import org.example.demo.model.entity.Shape;
import org.example.demo.model.metadata.dto.ShapeParams;

/**
 * Realization of the State template for operation data
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class OpsStateInstance implements OpsState {
    private final InnerClipboard innerClipboard;

    public OpsStateInstance() {
        this.innerClipboard = new InnerClipboard();
    }

    @Override
    public void put(final Shape obj) {
        innerClipboard.put(obj);
    }

    @Override
    public ShapeParams get() {
        return innerClipboard.get();
    }

    @Override
    public boolean hasCopy() {
        return innerClipboard.hasCopy();
    }

    @Override
    public void clear() {
        innerClipboard.clear();
    }
}
