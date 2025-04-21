package org.example.astero_demo.realization.level.react.logic_event;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.logic.EventProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.realization.level.react.Pipe;
import org.example.astero_demo.realization.level.react.ReceiverWrapper;

import java.util.List;

@Slf4j
public class LogicEventReceiverWrapper extends ReceiverWrapper<EventProcessor> implements EventProcessor {

    @Inject
    public LogicEventReceiverWrapper(final EventProcessor wrappedElement) {
        super(wrappedElement, List.of(
                CreateNewShapeEvent.class,
                ModifyShapeEvent.class,
                RemoveShapeEvent.class));
    }

    @Override
    public void process(final LogicEvent e) {
        wrappedElement.process(e);
    }

    @Override
    public void receive(final LogicEvent event) {
        if (event instanceof final CreateNewShapeEvent ev) {
            wrappedElement.createShape(ev.getParamInfos());
        }
        else if (event instanceof final ModifyShapeEvent ev) {
            wrappedElement.modifyShape(ev.getShapeId(), ev.getParamInfos());
        }
        else if (event instanceof final RemoveShapeEvent ev) {
            wrappedElement.removeShape(ev.getShapeId());
        }
    }

    @Override
    public void createShape(final ParamInfo[] paramInfos) {
        throw new RuntimeException("WTF?");
    }

    @Override
    public void modifyShape(final int shapeId, final ParamInfo... paramInfos) {
        throw new RuntimeException("WTF?");
    }

    @Override
    public void removeShape(int id) {
        throw new RuntimeException("WTF?");
    }
}
