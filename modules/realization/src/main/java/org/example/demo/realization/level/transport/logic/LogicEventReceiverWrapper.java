package org.example.demo.realization.level.transport.logic;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.core.logic.ShapeProcessor;
import org.example.demo.realization.level.transport.ReceiverWrapper;
import org.example.demo.realization.level.transport.logic.event.*;

import java.util.List;

@Slf4j
public class LogicEventReceiverWrapper extends ReceiverWrapper<ShapeProcessor, LogicEvent> {

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
        switch (event) {
            case final CreateNewShapeEvent ev -> wrappedElement.createShape(ev.getShapeParams());
            case final ModifyShapeEvent ev -> wrappedElement.modifyShape(ev.getShapeId(), ev.getShapeParams());
            case final RemoveShapeEvent ev -> wrappedElement.removeShape(ev.getShapeId());
            case final UndoLastOperationEvent ev -> wrappedElement.undoLastOperation();
        }
    }
}
