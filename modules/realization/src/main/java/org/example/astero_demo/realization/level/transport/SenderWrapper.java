package org.example.astero_demo.realization.level.transport;

import org.example.astero_demo.realization.level.transport.logic.event.LogicEvent;

/**
 * Transform direct method call to event signal
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class SenderWrapper<E extends ApplicationEvent> {
    private final Channel channelMock;

    protected SenderWrapper(final Channel channelMock) {
        this.channelMock = channelMock;
    }

    protected void send(final E event) {
        channelMock.addToChannel(event);
    }
}
