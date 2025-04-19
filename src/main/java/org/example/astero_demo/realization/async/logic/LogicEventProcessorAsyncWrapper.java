package org.example.astero_demo.realization.async.logic;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.controller.LogicEventProcessor;
import org.example.astero_demo.controller.ui.UIAdapterController;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.realization.async.AsynchWrapper;
import org.example.astero_demo.realization.logging.MarkerStorage;

import java.util.concurrent.Executor;

@Slf4j
public class LogicEventProcessorAsyncWrapper extends AsynchWrapper<LogicEventProcessor> implements LogicEventProcessor {

    @Inject
    protected LogicEventProcessorAsyncWrapper(
            final BackgroundExecutor executor,
            final UIAdapterController wrappedElement) {
        super(executor, wrappedElement);
    }

    @Override
    public void process(final LogicEvent e) {
        log.debug(MarkerStorage.EVENT_MARKER, "Process event: {}", e);
        executor.execute(() -> wrappedElement.process(e));
    }
}
