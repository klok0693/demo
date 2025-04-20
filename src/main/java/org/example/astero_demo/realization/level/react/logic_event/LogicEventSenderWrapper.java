package org.example.astero_demo.realization.level.react.logic_event;

import com.google.inject.Inject;
import org.example.astero_demo.logic.EventProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.realization.level.react.Pipe;
import org.example.astero_demo.realization.level.react.SenderWrapper;

public class LogicEventSenderWrapper extends SenderWrapper implements EventProcessor {

    @Inject
    public LogicEventSenderWrapper(final Pipe pipe) {
        super(pipe);
    }

    @Override
    public void process(final LogicEvent e) {
        send(e);
    }
}
