package org.example.demo.realization.level.transport;

import org.example.demo.realization.level.WrapperLink;

import java.util.List;

/**
 * Transform event signal to a direct method call
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ReceiverWrapper<P, E extends ApplicationEvent> extends WrapperLink<P> {
    private final List<Class<? extends ApplicationEvent>> handledEvents;

    protected ReceiverWrapper(final P wrappedElement, final List<Class<? extends ApplicationEvent>> handledEvents) {
        super(wrappedElement);
        this.handledEvents = handledEvents;
    }

    public boolean canReceive(final Class<? extends ApplicationEvent> eClass) {
        return handledEvents.stream().anyMatch(eClass::isAssignableFrom);
    }

    public abstract void receive(E event);
}
