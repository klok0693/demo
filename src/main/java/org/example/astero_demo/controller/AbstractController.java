package org.example.astero_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.*;

import java.util.List;

import static org.example.astero_demo.adapter.model.entity.ShapeType.valueOf;
import static org.example.astero_demo.util.ParamUtils.getParamInfo;

/**
 * An abstract class representing a controller that delegates LogicEvents to the CommandProcessor
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public abstract class AbstractController implements LogicEventProcessor {
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    protected AbstractController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void process(final LogicEvent e) {
        if (!isValid(e)) {
            log.warn("Provided params are not valid! {}", e);
            return;
        }

        Command command = null;
        if (e instanceof final CreateNewShapeEvent ev) {
            final List<ParamInfo> infos = List.of(ev.getParamInfos());
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

    protected abstract boolean isValid(LogicEvent event);
}
