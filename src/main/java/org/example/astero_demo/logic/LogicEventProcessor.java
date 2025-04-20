package org.example.astero_demo.logic;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.*;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;

import java.util.Arrays;
import java.util.List;

import static org.example.astero_demo.model.entity.ShapeType.valueOf;
import static org.example.astero_demo.util.ParamUtils.getParamInfo;

@Slf4j
public class LogicEventProcessor implements EventProcessor {

    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;
    private final ShapeValidator validator;

    public LogicEventProcessor(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
        this.validator = validator;
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

    protected boolean isValid(final LogicEvent event) {
        if (event instanceof final ParamEvent ev) {
            return Arrays.stream(ev.getParamInfos())
                    .allMatch(info -> validator.isValid(info.getParam(), info.getNewValue()));
        }
        return true;
    }
}
