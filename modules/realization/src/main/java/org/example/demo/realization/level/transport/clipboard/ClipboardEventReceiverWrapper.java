package org.example.demo.realization.level.transport.clipboard;

import com.google.inject.Inject;
import org.example.demo.core.logic.ClipboardProcessor;
import org.example.demo.realization.level.transport.ReceiverWrapper;
import org.example.demo.realization.level.transport.clipboard.event.ClipboardEvent;
import org.example.demo.realization.level.transport.clipboard.event.CopyEvent;
import org.example.demo.realization.level.transport.clipboard.event.CutEvent;
import org.example.demo.realization.level.transport.clipboard.event.PasteEvent;

import java.util.List;

public class ClipboardEventReceiverWrapper extends ReceiverWrapper<ClipboardProcessor, ClipboardEvent> {

    @Inject
    public ClipboardEventReceiverWrapper(final ClipboardProcessor wrappedElement) {
        super(wrappedElement, List.of(
                CopyEvent.class,
                CutEvent.class,
                PasteEvent.class
        ));
    }

    @Override
    public void receive(final ClipboardEvent event) {
        switch (event) {
            case final CopyEvent ev -> wrappedElement.copy(ev.getCopy());
            case final CutEvent ev -> wrappedElement.cut(ev.getId());
            case final PasteEvent ev -> wrappedElement.paste(ev.getX(), ev.getY());
        }
    }
}
