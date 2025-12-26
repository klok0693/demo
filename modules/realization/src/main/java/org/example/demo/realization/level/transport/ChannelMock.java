package org.example.demo.realization.level.transport;

import java.util.List;

/**
 * Mock, that represent transport event bus
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ChannelMock implements Channel {
    private final List<ReceiverWrapper> receivers;

    public ChannelMock(final List<ReceiverWrapper> receivers) {
        this.receivers = receivers;
    }

    @Override
    public void addToChannel(final ApplicationEvent event) {
        receivers.stream()
                .filter(receiver -> receiver.canReceive(event.getClass())) // TODO: move canReceive() inside receiver?
                .forEach(receiver -> receiver.receive(event));
    }
}
