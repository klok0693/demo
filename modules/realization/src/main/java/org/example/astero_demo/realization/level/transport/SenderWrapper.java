package org.example.astero_demo.realization.level.transport;

import org.example.astero_demo.realization.level.transport.logic_event.ui.LogicEvent;

/**
 * Transform direct method call to event signal
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class SenderWrapper {
    private final ChannelMock channelMock;

    protected SenderWrapper(final ChannelMock channelMock) {
        this.channelMock = channelMock;
    }

    protected void send(final LogicEvent event) {
        channelMock.addToChannel(event);
    }
}
