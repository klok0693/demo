package org.example.astero_demo.realization.level.transport.logic_event;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.realization.level.transport.ReceiverWrapper;
import org.example.astero_demo.realization.level.transport.logic_event.ui.*;

import java.util.List;

@Slf4j
public class LogicEventReceiverWrapper extends ReceiverWrapper<ShapeProcessor> {

    @Inject
    public LogicEventReceiverWrapper(final ShapeProcessor wrappedElement) {
        super(wrappedElement, List.of(
                CreateNewShapeEvent.class,
                ModifyShapeEvent.class,
                RemoveShapeEvent.class,
                UndoLastOperationEvent.class));
    }

    @Override
    public void receive(final LogicEvent event) {
        if (event instanceof final CreateNewShapeEvent ev) {
            wrappedElement.createShape(ev.getShapeParams());
        }
        else if (event instanceof final ModifyShapeEvent ev) {
            wrappedElement.modifyShape(ev.getShapeId(), ev.getShapeParams());
        }
        else if (event instanceof final RemoveShapeEvent ev) {
            wrappedElement.removeShape(ev.getShapeId());
        }
        else if (event instanceof final UndoLastOperationEvent ev) {
            wrappedElement.undoLastOperation();
        }
    }
}
