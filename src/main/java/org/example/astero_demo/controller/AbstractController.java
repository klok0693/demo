package org.example.astero_demo.controller;

import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.*;

import static java.lang.String.valueOf;

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
            command = commandFactory.createNewShapeCommand(
                    valueOf(ev.getPriority()),
                    valueOf(ev.getX()),
                    valueOf(ev.getY()),
                    valueOf(ev.getWidth()),
                    valueOf(ev.getHeight()),
                    ev.getType());
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
