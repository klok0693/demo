package org.example.astero_demo.realization.level.react.logic_event;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.logic.EventProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.realization.level.react.Pipe;
import org.example.astero_demo.realization.level.react.ReceiverWrapper;

import java.util.List;

@Slf4j
public class LogicEventReceiverWrapper extends ReceiverWrapper<EventProcessor> implements EventProcessor {

    @Inject
    public LogicEventReceiverWrapper(final EventProcessor wrappedElement) {
        super(wrappedElement, List.of(CreateNewShapeEvent.class));
    }

    @Override
    public void process(final LogicEvent e) {
        wrappedElement.process(e);
    }

    @Override
    public void receive(final LogicEvent event) {
        log.debug("Received event: {}", event);
    }
}
