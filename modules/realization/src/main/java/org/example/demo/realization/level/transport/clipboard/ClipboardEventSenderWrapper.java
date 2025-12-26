package org.example.demo.realization.level.transport.clipboard;

import com.google.inject.Inject;
import org.example.demo.core.logic.ClipboardProcessor;
import org.example.demo.model.entity.Shape;
import org.example.demo.realization.level.transport.Channel;
import org.example.demo.realization.level.transport.SenderWrapper;
import org.example.demo.realization.level.transport.clipboard.event.ClipboardEvent;
import org.example.demo.realization.level.transport.clipboard.event.CopyEvent;
import org.example.demo.realization.level.transport.clipboard.event.CutEvent;
import org.example.demo.realization.level.transport.clipboard.event.PasteEvent;

public class ClipboardEventSenderWrapper extends SenderWrapper<ClipboardEvent> implements ClipboardProcessor {

    @Inject
    public ClipboardEventSenderWrapper(final Channel channelMock) {
        super(channelMock);
    }

    @Override
    public void copy(final Shape shape) {
        send(new CopyEvent(shape));
    }

    @Override
    public void cut(final int id) {
        send(new CutEvent(id));
    }

    @Override
    public void paste(final double x, final double y) {
        send(new PasteEvent(x, y));
    }
}
