package org.example.demo.core.logic;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.core.logic.command.Command;
import org.example.demo.core.logic.command.CommandFactory;
import org.example.demo.core.logic.command.CommandProcessor;
import org.example.demo.model.metadata.dto.ShapeParams;

import java.util.function.Supplier;

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
    public void createShape(final ShapeParams shapeParams) {
        performShapeOperation(shapeParams, () -> commandFactory.createNewShapeCommand(shapeParams));
    }

    @Override
    public void modifyShape(final int shapeId, final ShapeParams shapeParams) {
        performShapeOperation(shapeParams, () -> commandFactory.createModifyShapeCommand(shapeId, shapeParams));
    }

    @Override
    public void removeShape(final int shapeId) {
        commandProcessor.processCommand(commandFactory.createRemoveShapeCommand(shapeId));
    }

    private void performShapeOperation(final ShapeParams params, final Supplier<? extends Command> commandSupplier) {
        if (!isValid(params)) {
            log.warn("Provided params are not valid!");
            return;
        }
        commandProcessor.processCommand(commandSupplier.get());
    }

    @Override
    public void undoLastOperation() {
        commandProcessor.undoLastCommand();
    }

    protected boolean isValid(final ShapeParams shapeParams) {
        return shapeParams.stream().allMatch(info -> validator.isValid(info.getParam(), info.getNewValue()));
    }
}
