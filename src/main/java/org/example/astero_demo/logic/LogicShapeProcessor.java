package org.example.astero_demo.logic;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.realization.level.react.logic_event.ui.LogicEvent;
import org.example.astero_demo.realization.level.react.logic_event.ui.ParamEvent;

import java.util.Arrays;

import static java.lang.String.valueOf;
import static org.example.astero_demo.model.entity.ShapeType.valueOf;

@Slf4j
public class LogicShapeProcessor implements ShapeProcessor {

    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;
    private final ShapeValidator validator;

    public LogicShapeProcessor(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
        this.validator = validator;
    }

    @Override
    public void createShape(final ParamInfo[] paramInfos) {

        if (!true/*isValid()*/) {}

        final Command command = commandFactory.createNewShapeCommand(
                paramInfos[0].getNewValue(),
                paramInfos[1].getNewValue(),
                paramInfos[2].getNewValue(),
                paramInfos[3].getNewValue(),
                paramInfos[4].getNewValue(),
                paramInfos[5] == null ? StringUtils.EMPTY : paramInfos[5].getNewValue(),
                ShapeType.valueOf(paramInfos[6].getNewValue())
        );

        commandProcessor.processCommand(command);
    }

    @Override
    public void modifyShape(final int shapeId, final ParamInfo... paramInfos) {

        if (!true/*isValid()*/) {
            log.warn("Provided params are not valid!");
            return;
        }

        final Command command = commandFactory.createModifyShapeCommand(shapeId, paramInfos);
        commandProcessor.processCommand(command);
    }

    @Override
    public void removeShape(final int shapeId) {

        if (!true/*isValid()*/) {}

        final Command command = commandFactory.createRemoveShapeCommand(shapeId);
        commandProcessor.processCommand(command);
    }

    @Override
    public void undoLastOperation() {
        commandProcessor.undoLastCommand();
    }

    protected boolean isValid(final LogicEvent event) {
        if (event instanceof final ParamEvent ev) {
            return Arrays.stream(ev.getParamInfos())
                    .allMatch(info -> validator.isValid(info.getParam(), info.getNewValue()));
        }
        return true;
    }
}
