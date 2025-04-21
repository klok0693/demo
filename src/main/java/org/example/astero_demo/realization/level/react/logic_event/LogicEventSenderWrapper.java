package org.example.astero_demo.realization.level.react.logic_event;

import com.google.inject.Inject;
import org.example.astero_demo.logic.ShapeProcessor;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.realization.level.react.Pipe;
import org.example.astero_demo.realization.level.react.SenderWrapper;
import org.example.astero_demo.realization.level.react.logic_event.ui.*;

public class LogicEventSenderWrapper extends SenderWrapper implements ShapeProcessor {

    @Inject
    public LogicEventSenderWrapper(final Pipe pipe) {
        super(pipe);
    }

    @Override
    public void createShape(final ParamInfo[] paramInfos) {
        send(new CreateNewShapeEvent(paramInfos));
    }

    @Override
    public void modifyShape(final int shapeId, final ParamInfo... paramInfos) {
        send(new ModifyShapeEvent(shapeId, paramInfos));
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
