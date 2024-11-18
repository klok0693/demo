package org.example.astero_demo.controller;

import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.*;

import static java.lang.String.valueOf;
import static org.example.astero_demo.adapter.model.entity.ShapeType.valueOf;
import static org.example.astero_demo.util.ParamUtils.getParamInfo;

public abstract class AbstractController implements LogicEventProcessor {
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    public AbstractController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void process(final LogicEvent e) {
        if (!isValid(e)) {
            // TODO: log
            return;
        }

        Command command = null;
        if (e instanceof final CreateNewShapeEvent ev) {
            final ParamInfo[] infos = ev.getParamInfos();
            command = commandFactory.createNewShapeCommand(
                    getParamInfo(infos, ShapeParam.PRIORITY),
                    getParamInfo(infos, ShapeParam.X),
                    getParamInfo(infos, ShapeParam.Y),
                    getParamInfo(infos, ShapeParam.WIDTH),
                    getParamInfo(infos, ShapeParam.HEIGHT),
                    getParamInfo(infos, ShapeParam.COLOR),
                    valueOf(getParamInfo(infos, ShapeParam.TYPE)));
        }
        else if (e instanceof final ModifyShapeEvent ev) {
            command = commandFactory.createModifyShapeCommand(
                    ev.getShapeId(), ev.getParamInfos());
        }
        else if (e instanceof final RemoveShapeEvent ev) {
            command = commandFactory.createRemoveShapeCommand(ev.getShapeId());
        }

        if (command != null) {
            commandProcessor.processCommand(command);
        }

        if (e instanceof final UndoLastOperationEvent ev) {
            commandProcessor.undoLastCommand();
        }
    }

    protected abstract boolean isValid(final LogicEvent event);
}
