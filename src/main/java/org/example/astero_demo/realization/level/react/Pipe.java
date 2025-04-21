package org.example.astero_demo.realization.level.react;

import org.example.astero_demo.realization.level.react.logic_event.ui.LogicEvent;
import org.example.astero_demo.realization.level.react.logic_event.LogicEventReceiverWrapper;

import java.util.List;

public class Pipe {
    private final List<LogicEventReceiverWrapper> receivers;

    public Pipe(final List<LogicEventReceiverWrapper> receivers) {
        this.receivers = receivers;
    }

    public void addToPipe(final LogicEvent event) {
        receivers.stream()
                .filter(receiver -> receiver.canReceive(event.getClass()))
                .forEach(receiver -> receiver.receive(event));
    }
}
