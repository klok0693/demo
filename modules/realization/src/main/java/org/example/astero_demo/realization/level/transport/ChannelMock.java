package org.example.astero_demo.realization.level.transport;

import org.example.astero_demo.realization.level.transport.logic_event.ui.LogicEvent;
import org.example.astero_demo.realization.level.transport.logic_event.LogicEventReceiverWrapper;

import java.util.List;

/**
 * Mock, that represent transport event bus
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ChannelMock {
    private final List<LogicEventReceiverWrapper> receivers;

    public ChannelMock(final List<LogicEventReceiverWrapper> receivers) {
        this.receivers = receivers;
    }

    public void addToChannel(final LogicEvent event) {
        receivers.stream()
                .filter(receiver -> receiver.canReceive(event.getClass())) // TODO: move canReceive() inside receiver?
                .forEach(receiver -> receiver.receive(event));
    }
}
