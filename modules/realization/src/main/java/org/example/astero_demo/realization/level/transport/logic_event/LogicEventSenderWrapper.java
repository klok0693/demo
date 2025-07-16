package org.example.astero_demo.realization.level.transport.logic_event;

import com.google.inject.Inject;
import org.example.astero_demo.core.logic.ShapeProcessor;
import org.example.astero_demo.core.model.metadata.dto.ShapeParams;
import org.example.astero_demo.realization.level.transport.ChannelMock;
import org.example.astero_demo.realization.level.transport.SenderWrapper;
import org.example.astero_demo.realization.level.transport.logic_event.ui.*;

public class LogicEventSenderWrapper extends SenderWrapper implements ShapeProcessor {

    @Inject
    public LogicEventSenderWrapper(final ChannelMock channelMock) {
        super(channelMock);
    }

    @Override
    public void createShape(final ShapeParams shapeParams) {
        send(new CreateNewShapeEvent(shapeParams));
    }

    @Override
    public void modifyShape(final int shapeId, final ShapeParams shapeParams) {
        send(new ModifyShapeEvent(shapeId, shapeParams));
    }

    @Override
    public void removeShape(final int id) {
        send(new RemoveShapeEvent(id));
    }

    @Override
    public void undoLastOperation() {
        send(new UndoLastOperationEvent());
    }
}
