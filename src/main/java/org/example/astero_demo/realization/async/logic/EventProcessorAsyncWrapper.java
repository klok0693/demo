package org.example.astero_demo.realization.async.logic;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.controller.EventProcessor;
import org.example.astero_demo.controller.ui.UIAdapterController;
import org.example.astero_demo.logic.LogicEventProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.realization.async.AsynchWrapper;
import org.example.astero_demo.realization.logging.MarkerStorage;

@Slf4j
public class EventProcessorAsyncWrapper extends AsynchWrapper<EventProcessor> implements EventProcessor {

    @Inject
    protected EventProcessorAsyncWrapper(
            final BackgroundExecutor executor,
            final LogicEventProcessor wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void process(final LogicEvent e) {
        log.debug(MarkerStorage.EVENT_MARKER, "Process event: {}", e);
        executor.execute(() -> wrappedElement.process(e));
    }
}
