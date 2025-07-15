package org.example.astero_demo.realization.level.transport;

import org.example.astero_demo.realization.level.transport.logic_event.ui.LogicEvent;
import org.example.astero_demo.realization.level.WrapperLink;

import java.util.List;

/**
 * Transform event signal to a direct method call
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ReceiverWrapper<E> extends WrapperLink<E> {
    private final List<Class<? extends LogicEvent>> handledEvents;

    protected ReceiverWrapper(final E wrappedElement, final List<Class<? extends LogicEvent>> handledEvents) {
        super(wrappedElement);
        this.handledEvents = handledEvents;
    }

    public boolean canReceive(final Class<? extends LogicEvent> eClass) {
        return handledEvents.stream().anyMatch(eClass::isAssignableFrom);
    }

    public abstract void receive(LogicEvent event);
}
