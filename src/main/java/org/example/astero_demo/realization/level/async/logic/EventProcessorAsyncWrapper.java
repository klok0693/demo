package org.example.astero_demo.realization.level.async.logic;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.logic.EventProcessor;
import org.example.astero_demo.logic.LogicEventProcessor;
import org.example.astero_demo.realization.level.react.logic_event.ui.LogicEvent;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.realization.level.async.AsynchWrapper;
import org.example.astero_demo.realization.logging.MarkerStorage;

@Slf4j
public class EventProcessorAsyncWrapper extends AsynchWrapper<EventProcessor> implements EventProcessor {

    @Inject
    public EventProcessorAsyncWrapper(
            final LogicEventProcessor wrappedElement,
            final BackgroundExecutor executor) {
        super(wrappedElement, executor);
    }

    @Override
    public void createShape(final ParamInfo... paramInfos) {
        executor.execute(() -> wrappedElement.createShape(paramInfos));
    }

    @Override
    public void modifyShape(final int shapeId, final ParamInfo[] paramInfos) {
        executor.execute(() -> wrappedElement.modifyShape(shapeId, paramInfos));
    }

    @Override
    public void removeShape(final int id) {
        executor.execute(() -> wrappedElement.removeShape(id));
    }

    @Override
    public void undoLastOperation() {
        executor.execute(wrappedElement::undoLastOperation);
    }

    @Override
    public void process(final LogicEvent e) {
        log.debug(MarkerStorage.EVENT_MARKER, "Process event: {}", e);
        executor.execute(() -> wrappedElement.process(e));
    }
}
