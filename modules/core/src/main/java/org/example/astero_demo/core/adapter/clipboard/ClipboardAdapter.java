package org.example.astero_demo.core.adapter.clipboard;

import org.example.astero_demo.core.context.ops.workspace.InnerClipboard;
import org.example.astero_demo.core.port.os.OSClipboard;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

import static java.lang.String.valueOf;
import static org.example.astero_demo.model.metadata.ParamInfo.create;

public class ClipboardAdapter {
    private final OSClipboard osClipboard;
    private final InnerClipboard innerClipboard;

    public ClipboardAdapter(
            final OSClipboard osClipboard,
            final InnerClipboard innerClipboard) {
        this.osClipboard = osClipboard;
        this.innerClipboard = innerClipboard;
    }

    public <T> void put(final T obj) {
        if (obj instanceof final String s) {
            innerClipboard.clear();
            osClipboard.put(s);
        } else if (obj instanceof final Shape sh) {
            osClipboard.clear();
            innerClipboard.put(sh);
        }
    }

    public ShapeParams getShapeParams() {
        return innerClipboard.get();
    }

    public String getString() {
        return osClipboard.get();
    }

    public boolean hasCopy() {
        return innerClipboard.hasCopy() || osClipboard.hasCopy();
    }
}
