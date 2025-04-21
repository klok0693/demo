package org.example.astero_demo.realization.level.transport;

import org.example.astero_demo.realization.level.transport.logic_event.ui.LogicEvent;

public abstract class SenderWrapper {
    private final ChannelMock channelMock;

    protected SenderWrapper(final ChannelMock channelMock) {
        this.channelMock = channelMock;
    }

    protected void send(final LogicEvent event) {
        channelMock.addToChannel(event);
    }
}
