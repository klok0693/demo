package org.example.astero_demo.realization.level.react;

import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.realization.level.WrapperLink;

import java.util.List;

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
